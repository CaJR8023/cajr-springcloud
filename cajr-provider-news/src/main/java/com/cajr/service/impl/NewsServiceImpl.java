package com.cajr.service.impl;

import com.cajr.algorithm.TFIDF;
import com.cajr.mapper.NewsMapper;
import com.cajr.mapper.UserInitMapper;
import com.cajr.service.ITagClientService;
import com.cajr.service.IUserClientService;
import com.cajr.service.NewsService;
import com.cajr.service.NewsTagService;
import com.cajr.util.NewsFillDataUtil;
import com.cajr.vo.news.News;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import com.cajr.vo.user.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.ansj.app.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/1/15 4:43 下午
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Value("${news.recommend.TFIDFKeywordsNum}")
    private int keyWordsNum;

    @Autowired
    private ITagClientService iTagClientService;

    @Autowired
    private NewsTagService newsTagService;

    @Autowired
    private IUserClientService iUserClientService;

    @Autowired
    private UserInitMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(News news) {
        if (this.newsMapper.checkExistBySign(news.getNewsDataSign()) > 0){
            return -1;
        }
        initNewsUser(news);
        newsMapper.insertSelective(news);
        initTag(news);
        return news.getId();
    }

    private void initNewsUser(News news) {
        if (this.userMapper.checkIsExistsByUserName(news.getSource()) > 0) {
            return;
        }
        User user = new User();
        user.setUsername(news.getSource());
        user.setTel("12345612345");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.userMapper.insertNewsUser(user);
        news.setUserId(user.getId());
    }

    private void initTag(News news) {
        List<Keyword> keywordList = TFIDF.getTfidf(news.getContent(),keyWordsNum);
        if (keywordList.isEmpty()){
            return;
        }
        for (Keyword keyword : keywordList){
            Tag tag = new Tag();
            tag.setName(keyword.getName());
            Integer tagId = (Integer) this.iTagClientService.addOneTag(tag).getData();
            if (tagId > 0){
                NewsTag newsTag = new NewsTag(news.getId(),tagId);
                ModuleTag moduleTag = new ModuleTag(news.getModuleId(), tagId);
                this.newsTagService.addOneNewsTag(newsTag);
                this.newsTagService.addOneModuleTag(moduleTag);
            }
        }
    }

    @Override
    public List<News> findSectionNews(List<Integer> newsIds) {
        if(newsIds.isEmpty()){
            return null;
        }
        List<News> newsList = this.newsMapper.selectSectionByNewsIds(newsIds);
        if (!newsList.isEmpty()){
            newsList.forEach(NewsFillDataUtil::fillNews);
        }
        return newsList;
    }

    @Override
    public List<News> findAll() {
        return this.newsMapper.selectAll();
    }

    @Override
    public List<News> findAllByModuleId(int moduleId) {
        return this.newsMapper.selectAllByModuleId(moduleId);
    }

    @Override
    public PageInfo<News> findAllByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(this.newsMapper.selectAll());
    }

    @Override
    public List<Integer> findAllNewsId() {
        return this.newsMapper.selectAllId();
    }

    @Override
    public Integer update(News news) {
        news.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.newsMapper.updateByPrimaryKey(news);
    }

    @Override
    public Integer deleteOne(Integer id) {
        return this.newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public News getOne(int id) {
        News news = this.newsMapper.selectByPrimaryKey(id);
        NewsFillDataUtil.fillNews(news);
        List<Keyword> keywordList = TFIDF.getTfidf(news.getContent(),5);
        List<String> tags = new ArrayList<>();
        if (!keywordList.isEmpty()){
            for (Keyword keyword : keywordList){
                tags.add(keyword.getName());
            }
            news.setTags(tags);
        }
        news.setUserOther(this.iUserClientService.getOneUserOther(news.getUserId()));
        return news;
    }
}
