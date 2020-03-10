package com.cajr.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

/**
 * @Author CAJR
 * @create 2020/2/18 21:36
 */
public class JsonUtil {
    public static String test(){
        String json=null;
        try {
            Map<Integer, Object> moduleIdMap = new HashMap<Integer, Object>();
            Map<String, Double> keywordRateMap = new HashMap<String, Double>();
            keywordRateMap.put("政治", 123.1);
            keywordRateMap.put("金融", 35.2);
            moduleIdMap.put(1,keywordRateMap);
            keywordRateMap.put("电影", 351.1);
            moduleIdMap.put(2,keywordRateMap);
            ObjectMapper objectMapper=new ObjectMapper();
            json=objectMapper.writeValueAsString(moduleIdMap);
        } catch (IOException e) {

            e.printStackTrace();

        }
        return json;
    }

    /**
     * 获取用户所关注的模板的id的set
     * @param srcJson
     * @return
     */
    public static Set<Integer> getUserModuleIdSet(String srcJson){

        //java的擦除机制不允许直接获取泛型类的class,但是这样会使得jackson的readValue自动将键转换为String，于是需要使用jackson提供的TypeReference来解决这个问题
        Map<Integer, Object> map=null;
        try
        {
            ObjectMapper objectMapper=new ObjectMapper();
            map = objectMapper.readValue(srcJson, new TypeReference<Map<Integer,Object>>(){});
        }
        catch (JsonParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map.keySet();
    }

    /**
     * 获得用户对应module的喜好关键词列表的map
     * @param srcJson
     * @param moduleId
     * @return
     */
    @SuppressWarnings("unchecked")
    public static LinkedHashMap<String,Double> getModulePrefMap(String srcJson,int moduleId){

        LinkedHashMap<String,Double> keyWordsRateMap=null;
        try
        {
            ObjectMapper objectMapper=new ObjectMapper();
            //java的擦除机制不允许直接获取泛型类的class,但是这样会使得jackson的readValue自动将键转换为String，于是需要使用jackson提供的TypeReference来解决这个问题
            Map<Integer,Object> map=objectMapper.readValue(srcJson, new TypeReference<Map<Integer,Object>>(){});
            keyWordsRateMap=(LinkedHashMap<String,Double>) map.get(moduleId);
        }
        catch (JsonParseException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (JsonMappingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return keyWordsRateMap;
    }

    /**
     * 将用户的喜好关键词列表字符串转换为map
     * @param srcJson
     * @return
     */
    public static CustomHashMap<Integer,CustomHashMap<String, Double>> jsonPrefListToMap(String srcJson){
        ObjectMapper objectMapper = new ObjectMapper();
        CustomHashMap<Integer,CustomHashMap<String, Double>> resultMap = null;
        try {
            resultMap = objectMapper.readValue(srcJson, new TypeReference<CustomHashMap<Integer,CustomHashMap<String, Double>>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
//
//    public static void main(String[] args) {
//        String srcJson = "{\"1\":{\"膏盐\":173.08449864151828,\"应城\":22.976422775068052,\"解释\":9.210345788463428,\"断层\":64.47241212406624,\"专家\":73.68275671321855,\"开采\":55.26207473078057,\"地震\":92.10344589152318,\"湖北\":82.8933873393619,\"地处\":27.631037365390284,\"无关\":55.26207473078057},\"20\":{\"申花\":276.31825513305665,\"曾诚\":211.54772056185573,\"斩获\":163.46869316143392,\"球队\":63.63065905853266,\"欧洲\":64.47263459728147,\"陈威\":160.35475336048546,\"门将\":174.9965471938941,\"小组赛\":64.98077263321612,\"最佳\":64.4776997328394,\"豪门\":64.47241212406624}}";
//        String srcJson1 = "{}";
//        CustomHashMap<Integer,CustomHashMap<String, Double>> map = jsonPrefListToMap(srcJson1);
//        System.out.println(map.toString());
//    }
}
