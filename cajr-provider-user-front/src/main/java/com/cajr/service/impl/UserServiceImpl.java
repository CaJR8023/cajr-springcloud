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
import com.cajr.mapper.UserMapper;
import com.cajr.service.IModuleService;
import com.cajr.service.UserPrefService;
import com.cajr.service.UserService;
import com.cajr.util.CustomHashMap;
import com.cajr.util.TimeUtil;
import com.cajr.vo.OpenSearchExecuteResult;
import com.cajr.vo.OpenSearchField;
import com.cajr.vo.OpenSearchFormat;
import com.cajr.vo.SearchPage;
import com.cajr.vo.news.Module;
import com.cajr.vo.user.User;
import com.cajr.vo.user.UserOther;
import com.cajr.vo.user.UserPref;
import com.cajr.vo.user.UserSearch;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author CAJR
 * @date 2019/11/26 2:06 下午
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    private IModuleService iModuleService;

    @Autowired
    private UserPrefService userPrefService;

    @Value("${news.recommend.activeDays}")
    private int activeDays;

    @Resource
    private DocumentClient documentClient;

    @Resource
    OpenSearchClient openSearchClient;

    @Value("${aliyun.open-search.app-user-name}")
    private String userAppName;

    @Override
    public Optional<User> getOneUser(int id) {
        return Optional.of(this.userMapper.selectByPrimaryKey(id));
    }

    @Override
    public UserOther getOneUserOther(int id) {
        User user = this.userMapper.selectByPrimaryKey(id);
        UserOther userOther = new UserOther();
        if (user!= null){
            userOther.setId(user.getId());
            userOther.setUsername(user.getUsername());
            if (user.getUserInfo() != null){
                userOther.setAvatar(user.getUserInfo().getAvatar());
                userOther.setSignature(user.getUserInfo().getSignature());
            }
        }
        return userOther;
    }

    @Override
    public User getUser(int id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByTel(String tel) {
        return this.userMapper.selectByTel(tel);
    }

    @Override
    public User getUserByUserName(String userName) {
        return this.userMapper.selectByUserName(userName);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,rollbackFor = Exception.class)
    public Optional<Integer> add(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStatus(1);
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));

        this.userMapper.insertSelective(user);
        //同时初始化用户喜好关键词
        UserPref userPref = new UserPref();
        userPref.setUserId(user.getId());
        userPref.setPrefList(getDefaultUserPref());
        userPref.setStatus(1);
        userPref.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        userPref.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.userPrefService.add(userPref);

        initSearchNewsData(user);
        return Optional.of(user.getId());
    }

    private void initSearchNewsData(User user) {
        if (user == null || user.getId() <= 0){
            return;
        }
        try{
            UserSearch userSearch = new UserSearch(user);
            OpenSearchFormat<UserSearch> format = new OpenSearchFormat<>();
            format.setFields(userSearch);
            format.setCmd("ADD");

            List<OpenSearchFormat> list = Lists.newArrayList(format);
            String jsonList = JSON.toJSONString(list);
            OpenSearchResult result = documentClient.push(jsonList, userAppName, "user");
            if ("true".equals(result.getResult())){
                log.info("用户搜索数据推送成功！");
            }else {
                log.info("用户搜索数据推送失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Integer addNewsInit(User user) {
        user.setTel(" ");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        this.userMapper.insertSelective(user);
        return user.getId();
    }

    private String getDefaultUserPref() {
        List<Module> modules = this.iModuleService.getAllModule();
        if (modules.isEmpty()){
            return "{}";
        }
        StringBuilder userPrefList = new StringBuilder("{");
        for (Module module : modules) {
            userPrefList.append("\"").append(module.getId()).append("\":").append("{}").append(",");
        }

        return userPrefList.substring(0,userPrefList.length()-1) + "}";
    }

    @Override
    public Optional<Integer> update(User user) {

        user.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return Optional.of(this.userMapper.updateByPrimaryKeySelective(user));
    }

    @Override
    public Optional<Integer> deleteByStatus(int id) {
        return Optional.of(this.userMapper.deleteByPrimaryKey(id));
    }

    @Override
    public Optional<Integer> checkIsExistByTel(String tel) {
        return Optional.of(this.userMapper.checkIsExistsByTel(tel));
    }

    @Override
    public Optional<Integer> checkIsExistByUserName(String userName) {
        return Optional.of(this.userMapper.checkIsExistsByUserName(userName));
    }

    @Override
    public Optional<Integer> checkIsExistById(int id) {
        return Optional.of(this.userMapper.checkIsExistsById(id));
    }

    @Override
    public List<Integer> findAllUserId() {
        return this.userMapper.findAllUserId();
    }

    @Override
    public List<User> findSection(List<Integer> userIds) {
        return null;
    }

    @Override
    public List<Integer> findAllActiveUserId() {
        List<User> users = this.userMapper.findAll();
        if (users.isEmpty()){
            return null;
        }
        List<Integer> activeUserIds = new ArrayList<>();
        users.forEach(user -> {
            if (user.getUserLog().getLatestLoginTime().after(TimeUtil.getInRecTimestamp(activeDays))){
                activeUserIds.add(user.getId());
            }
        });
        return activeUserIds;
    }

    @Override
    public PageInfo getAllByPage(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return new PageInfo<>(this.userMapper.findAll());
    }

    @Override
    public SearchPage search(String keyWord, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(keyWord)){
            return null;
        }

        List<User> users =  new ArrayList<>();

        SearchPage page = new SearchPage();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        page.countStartRow();

        SearcherClient searcherClient = new SearcherClient(openSearchClient);

        Config config = new Config(Lists.newArrayList(userAppName));
        config.setSearchFormat(SearchFormat.FULLJSON);
        config.setStart(page.getStartRow());
        config.setHits(page.getPageSize());
        config.setFetchFields(com.aliyun.opensearch.sdk.dependencies.com.google.common.collect.Lists.newArrayList("user_id","user_name"));

        String searchScript = "user_name:'" + keyWord + "'" ;
        SearchParams searchParams = new SearchParams(config);
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
                User user;
                if (item.getFields() != null){
                    user = this.getOneUser(item.getFields().getUser_id()).get();
                    users.add(user);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }

        page.setList(users);
        page.countTotalPages();
        return page;
    }
}
