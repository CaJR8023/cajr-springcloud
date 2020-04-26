package com.cajr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cajr.service.ModuleService;
import com.cajr.service.NewsDataService;
import com.cajr.service.NewsImageService;
import com.cajr.service.NewsService;
import com.cajr.util.TimeUtil;
import com.cajr.util.URLRequestUtils;
import com.cajr.vo.news.Module;
import com.cajr.vo.news.News;
import com.cajr.vo.news.NewsImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/1/15 5:14 下午
 */
@Service
@RefreshScope
public class NewsDataServiceImpl implements NewsDataService {
    @Value("${news.data.secretId}")
    private String secretId;

    @Value("${news.data.secretKey}")
    private String secretKey;

    @Value("${news.data.urlNews}")
    private String urlNews;

    @Value("${news.data.urlChannel}")
    private String urlChannel;

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsImageService newsImageService;

    @Autowired
    private ModuleService moduleService;

    @Override
    public String crawlChannelData() throws Exception {
        //查询参数
        Map<String, String> queryParams = new HashMap<>(16);
        List<Module> modules = new ArrayList<>();

        String response = crawlData(urlChannel,queryParams);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.getInteger("showapi_res_code") == 0){
            JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("showapi_res_body"));
            JSONArray jsonArray = JSONArray.parseArray(jsonObject1.getString("channelList"));
            jsonArray.forEach(json ->{
                JSONObject moduleJson = JSON.parseObject(json.toString());
                Module module = new Module();
                if (moduleJson.getString("name") != null){
                    module.setName(moduleJson.getString("name").replace("焦点","").replace("最新","").trim());
//                    module.setName(moduleJson.getString("name"));
                }
                if (moduleJson.getString("name").equals("最新")){
                    module.setSign(moduleJson.getString("channelId"));
                }
                module.setSign(moduleJson.getString("channelId"));
                module.setStatus(1);
                module.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
                module.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
                System.out.println(module.toString());
                modules.add(module);
            });
            this.moduleService.addList(modules);
        }
        return "success";
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "cajr::module::count::SimpleKey")
            }
    )
    public String crawlNewsData() throws Exception {
        //查询参数
        Map<String, String> queryParams = new HashMap<>(16);
        queryParams.put("needAllList", "1");
        queryParams.put("needContent", "1");
        queryParams.put("channelId", "");
        queryParams.put("page", "1");

        List<Module> modules = this.moduleService.findAll();
        if (!modules.isEmpty()){
            for (Module module : modules) {
                if (module.getId() > 0){
                    try {
                        queryParams.put("channelId",module.getSign());
                        JSONObject jsonAll = JSON.parseObject(crawlData(urlNews, queryParams));
                        if (jsonAll != null){
                            if (jsonAll.getInteger("showapi_res_code") == 0){
                                JSONObject jsonPageBean = JSON.parseObject(JSON.parseObject(jsonAll.getString("showapi_res_body")).getString("pagebean"));
                                int allPages = jsonPageBean.getInteger("allPages");
                                insertData(module, jsonPageBean);

                                for (int i = 1; i <= allPages; i++) {
                                    queryParams.put("page",String.valueOf(i));
                                    JSONObject jsonAllLoop = JSON.parseObject(crawlData(urlNews, queryParams));
                                    JSONObject jsonPageBeanLoop = JSON.parseObject(JSON.parseObject(jsonAllLoop.getString("showapi_res_body")).getString("pagebean"));
                                    insertData(module, jsonPageBeanLoop);
                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        return "1";
    }

    private void insertData(Module module, JSONObject jsonPageBean){
        JSONArray contentList = JSONArray.parseArray(jsonPageBean.getString("contentlist"));
        contentList.forEach(contentObject -> {
            JSONObject content = JSON.parseObject(contentObject.toString());
            JSONArray jsonImageUrls = JSONArray.parseArray(content.getString("imageurls"));
            if (content.getTimestamp("pubDate").before(TimeUtil.getInRecTimestamp(3))){
                return;
            }
            if (!jsonImageUrls.isEmpty()){
                News news = new News();
                System.out.println("news==>"+module.getId());
                news.setModuleId(module.getId());
                news.setDesc(content.getString("desc"));
                news.setAllContent(content.getString("allList"));
                news.setContent(content.getString("content"));
                news.setTitle(content.getString("title"));
                news.setSource(content.getString("source"));
                news.setCreatedAt(content.getTimestamp("pubDate"));
                news.setNewsDataSign(content.getString("id"));
                news.setStatus(1);
                int newsId = this.newsService.add(news);

                jsonImageUrls.forEach(imageObject -> {
                    JSONObject jsonImage = JSON.parseObject(imageObject.toString());
                    NewsImage newsImage = new NewsImage();
                    newsImage.setNewsId(newsId);
                    newsImage.setUrl(jsonImage.getString("url"));
                    if (newsImage.getNewsId() > 0){
                        this.newsImageService.add(newsImage);
                    }
                });
            }
        });
    }


    private String crawlData(String url, Map<String, String> queryParams) throws Exception {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String dateTime = simpleDateFormat.format(calendar.getTime());

        //签名
        String source = "market";
        String auth = URLRequestUtils.calcAuthorization(source, secretId, secretKey, dateTime);
        System.out.println(auth);
        //请求方法
        String method = "GET";
        //请求头
        Map<String, String> headers = new HashMap<>(16);
        headers.put("X-Source", source);
        headers.put("X-Date", dateTime);
        headers.put("Authorization", auth);


        //body参数
        Map<String, String> bodyParams = new HashMap<>();

        //url参数拼接
        if (!queryParams.isEmpty()) {
            url += "?" + URLRequestUtils.urlEncode(queryParams);
        }

        BufferedReader reader = null;

        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod(method);

            //request headers
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            //request body
            Map<String, Boolean> methods = new HashMap<>();
            methods.put("POST", true);
            methods.put("PUT", true);
            methods.put("PATCH", true);
            Boolean hashBody = methods.get(method);
            if (hashBody != null) {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

                connection.setDoOutput(true);
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
                outputStream.writeBytes(URLRequestUtils.urlEncode(bodyParams));
                outputStream.flush();
                outputStream.close();
            }

            //定义 BufferedReader输入流来读取URl的响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }
}
