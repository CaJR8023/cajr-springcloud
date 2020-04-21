package com.cajr.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cajr.vo.news.News;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CAJR
 * @date 2020/4/20 6:38 下午
 */
public class NewsFillDataUtil {
    public static void fillNews(News news) {
        news.setTime(TimeUtil.format(news.getCreatedAt()));
        JSONArray jsonObject = JSONArray.parseArray(news.getAllContent());
        if (jsonObject.getString(0).length() < 150){
            if (news.getContent().length() < 150){
                news.setDesc(news.getContent());
            }else {
                news.setDesc(news.getContent().substring(1, 150));
            }
        }else {
            news.setDesc(jsonObject.getString(0));
        }
        if (!news.getNewsImages().isEmpty()){
            news.setBanner(news.getNewsImages().get(0).getUrl());
            news.setExistBanner(true);
        }else {
            news.setExistBanner(false);
        }
        List<String> contentList = new ArrayList<>();
        for (Object o : jsonObject){
            if (o.toString().contains(CommonParam.IS_IMG_STRING)){
                if (o.toString().contains(CommonParam.IS_IMG_STRING1)){
                    JSONObject jsonObject1 = JSONObject.parseObject(o.toString());
                    contentList.add(CommonParam.IMG_HTML_FRONT + jsonObject1.get("url") + CommonParam.IMG_HTML_AFTER);
                }
            }else {
                contentList.add(o.toString());
            }
        }
        news.setContentList(contentList);
    }
}
