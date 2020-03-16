package com.cajr.service.impl;

import com.cajr.algorithm.TFIDF;
import com.cajr.mapper.NewsMapper;
import com.cajr.service.ITagClientService;
import com.cajr.service.NewsService;
import com.cajr.service.NewsTagService;
import com.cajr.vo.news.Module;
import com.cajr.vo.news.News;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import org.ansj.app.keyword.Keyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(News news) {
        if (this.newsMapper.checkExistBySign(news.getNewsDataSign()) > 0){
            return -1;
        }
        newsMapper.insertSelective(news);
        initTag(news);
        return news.getId();
    }

    private void initTag(News news) {
        List<Keyword> keywordList = TFIDF.getTfidf(news.getTitle(),news.getContent(),keyWordsNum);
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
        return null;
    }

    @Override
    public List<News> findAll() {
        return this.newsMapper.selectAll();
    }
}
