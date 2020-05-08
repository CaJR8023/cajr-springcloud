package com.cajr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchResult;
import com.aliyun.opensearch.sdk.generated.search.Config;
import com.aliyun.opensearch.sdk.generated.search.SearchFormat;
import com.aliyun.opensearch.sdk.generated.search.SearchParams;
import com.aliyun.opensearch.sdk.generated.search.Summary;
import com.aliyun.opensearch.sdk.generated.search.general.SearchResult;
import com.aliyun.opensearch.search.SearchParamsBuilder;
import com.cajr.algorithm.TFIDF;
import com.cajr.mapper.NewsMapper;
import com.cajr.mapper.UserInitMapper;
import com.cajr.service.*;
import com.cajr.util.CommonParam;
import com.cajr.util.NewsFillDataUtil;
import com.cajr.vo.OpenSearchExecuteResult;
import com.cajr.vo.OpenSearchField;
import com.cajr.vo.OpenSearchFormat;
import com.cajr.vo.SearchPage;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsImage;
import com.cajr.vo.news.NewsSearch;
import com.cajr.vo.tag.ModuleTag;
import com.cajr.vo.tag.NewsTag;
import com.cajr.vo.tag.Tag;
import com.cajr.vo.user.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.ansj.app.keyword.Keyword;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/1/15 4:43 下午
 */
@Service
public class NewsServiceImpl implements NewsService {

    private static final Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    private NewsMapper newsMapper;

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

    @Autowired
    private NewsImageService newsImageService;

    @Resource
    private DocumentClient documentClient;

    @Resource
    OpenSearchClient openSearchClient;

    @Value("${aliyun.open-search.app-news-name}")
    private String newsAppName;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer add(News news) {
        newsMapper.insertSelective(news);
        initTag(news);
        initSearchNewsData(news);
        NewsImage newsImage = new NewsImage();
        newsImage.setUrl(news.getBanner());
        newsImage.setNewsId(news.getId());
        newsImage.setStatus(1);
        newsImage.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.newsImageService.add(newsImage);
        return news.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer init(News news) {
        if (this.newsMapper.checkExistBySign(news.getNewsDataSign()) > 0){
            return -1;
        }
        initNewsUser(news);
        newsMapper.insertSelective(news);
        initTag(news);
        initSearchNewsData(news);
        return news.getId();
    }

    private void initSearchNewsData(News news) {
        if (news == null || news.getId() <= 0){
            return;
        }
        try{
            NewsSearch newsSearch = new NewsSearch(news);
            OpenSearchFormat<NewsSearch> format = new OpenSearchFormat<>();
            format.setFields(newsSearch);
            format.setCmd("ADD");

            List<OpenSearchFormat> list = Lists.newArrayList(format);

            String jsonList = JSON.toJSONString(list);
            OpenSearchResult result = documentClient.push(jsonList, newsAppName, CommonParam.NEWS_TABLE_NAME);
            if ("true".equals(result.getResult())){
                log.info("新闻搜索数据推送成功！");
            }else {
                log.info("新闻搜索数据推送失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initNewsUser(News news) {
        int userId = this.userMapper.getUserIdByUserName(news.getSource());
        if (userId > 0) {
            news.setUserId(userId);
            return;
        }
        User user = new User();
        user.setUsername(news.getSource());
        user.setTel("12345612345");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.userMapper.insertNewsUser(user);
        userId = user.getId();
        news.setUserId(userId);
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
    public List<News> findAllByUserId(int userId) {
        List<News> newsList = this.newsMapper.selectAllByUserId(userId);
        if (CollectionUtils.isEmpty(newsList)){
            return newsList;
        }
        newsList.forEach(NewsFillDataUtil::fillNews);
        return newsList;
    }

    @Override
    public List<News> findAllByModuleId(int moduleId) {
        List<News> newsList =  this.newsMapper.selectAllByModuleId(moduleId);
        if (CollectionUtils.isEmpty(newsList)){
            return newsList;
        }
        newsList.forEach(NewsFillDataUtil::fillNews);
        return newsList;
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
        initSearchNewsData(news);
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
//        List<Keyword> keywordList = TFIDF.getTfidf(news.getContent(),5);
        List<String> tags = new ArrayList<>();
//        if (!keywordList.isEmpty()){
//            for (Keyword keyword : keywordList){
//                tags.add(keyword.getName());
//            }
//            news.setTags(tags);
//        }
        List<Tag> tagList = this.iTagClientService.getNewsTag(id);
        if (!CollectionUtils.isEmpty(tagList)){
            tagList.forEach(tag -> {
                tags.add(tag.getName());
            });
        }
        news.setTags(tags);
        news.setUserOther(this.iUserClientService.getOneUserOther(news.getUserId()));
        return news;
    }

    @Override
    public News getUndistributedOne(int userId) {
        News news = this.newsMapper.selectByUserId(userId);
        if (news == null){
            return null;
        }
        NewsFillDataUtil.fillNews(news);
//        List<Keyword> keywordList = TFIDF.getTfidf(news.getContent(),5);
        List<String> tags = new ArrayList<>();
//        if (!keywordList.isEmpty()){
//            for (Keyword keyword : keywordList){
//                tags.add(keyword.getName());
//            }
//            news.setTags(tags);
//        }
        List<Tag> tagList = this.iTagClientService.getNewsTag(news.getId());
        if (!CollectionUtils.isEmpty(tagList)){
            tagList.forEach(tag -> {
                tags.add(tag.getName());
            });
        }
        news.setTags(tags);
        news.setUserOther(this.iUserClientService.getOneUserOther(news.getUserId()));
        return news;
    }

    @Override
    public SearchPage searchNews(String keyWord, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(keyWord)){
            return null;
        }

        List<News> newsList =  new ArrayList<>();

        SearchPage page = new SearchPage();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.countStartRow();

        SearcherClient searcherClient = new SearcherClient(openSearchClient);

        Config config = new Config(Lists.newArrayList(newsAppName));
        config.setSearchFormat(SearchFormat.FULLJSON);
        config.setStart(page.getStartRow());
        config.setHits(page.getPageSize());
        config.setFetchFields(CommonParam.openSearchFetchFieldFormat);

        Set<Summary> summaries = new HashSet<>();
        Summary summary = new Summary();
        summary.setSummary_field("content");
        summary.setSummary_len("50");//片段长度
        summary.setSummary_element("em"); //飘红标签
        summary.setSummary_ellipsis("...");//片段链接符
        summary.setSummary_snippet("1");//片段数量
        summaries.add(summary);

        String searchScript = "default:'" + keyWord + "'" ;
        SearchParams searchParams = new SearchParams(config);
        searchParams.setSummaries(summaries);
        searchParams.setQuery(searchScript);
        SearchParamsBuilder paramsBuilder = SearchParamsBuilder.create(searchParams);
//        paramsBuilder.addCustomParam("summary","summary_field:name,summary_ellipsis:...,summary_snipped:1,summary_len:50,summary_element_prefix:<abc>,summary_element_postfix:</abc>");

        try{
            SearchResult execute = searcherClient.execute(paramsBuilder);
            String result = execute.getResult();
            OpenSearchExecuteResult openSearchExecuteResult = JSONObject.parseObject(result, OpenSearchExecuteResult.class);
            List<OpenSearchField> items = openSearchExecuteResult.getResult().getItems();
            page.setTotal(openSearchExecuteResult.getResult().getTotal());
            items.forEach(item -> {
                News news;
                if (item.getFields() != null){
                    news = this.getOne(item.getFields().getNews_id());
                    news.setDesc(item.getFields().getContent());
                    newsList.add(news);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        page.setList(newsList);
        page.countTotalPages();
        return page;
    }

    @Override
    public int checkExistedByUserId(int userId) {
        return this.newsMapper.checkExistedByUserId(userId);
    }

    @Override
    public int distributedNews(Integer id) {
        News news = this.newsMapper.selectByPrimaryKey(id);
        if (news == null){
            return 0;
        }
        news.setStatus(1);
        news.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return this.newsMapper.updateByPrimaryKeySelective(news);
    }

}
