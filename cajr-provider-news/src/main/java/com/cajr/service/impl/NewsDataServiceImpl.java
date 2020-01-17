package com.cajr.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cajr.service.ModuleService;
import com.cajr.service.NewsDataService;
import com.cajr.service.NewsImageService;
import com.cajr.service.NewsService;
import com.cajr.util.URLRequestUtils;
import com.cajr.vo.news.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author CAJR
 * @date 2020/1/15 5:14 下午
 */
@Service
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
                }
                module.setSign(moduleJson.getString("channelId"));
                System.out.println(module.toString());
                modules.add(module);
            });
            this.moduleService.addList(modules);
        }
        return "success";
    }

    @Override
    public String crawlNewsData() throws Exception {
        //查询参数
        Map<String, String> queryParams = new HashMap<>(16);
        queryParams.put("channelId", "5572a108b3cdc86cf39001d1");
        queryParams.put("channelName", "互联网焦点");
        queryParams.put("needAllList", "1");
        queryParams.put("needContent", "1");
        queryParams.put("page", "1");
//        queryParams.put("title", "");
        String result = crawlData(urlNews, queryParams);
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println(jsonObject.getString("showapi_res_body"));
        return result;
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
