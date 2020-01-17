package com.cajr.util;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Map;

/**
 * @author CAJR
 * @date 2020/1/15 5:03 下午
 */
public class URLRequestUtils {
    public static String calcAuthorization(String source, String secretId, String secretKey, String dataTime) throws Exception {
        String signStr = "x-date: " + dataTime + "\n" + "x-source: " + source;
        Mac mac = Mac.getInstance("HmacSHA1");
        Key sKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), mac.getAlgorithm());
        mac.init(sKey);
        byte[] hash = mac.doFinal(signStr.getBytes(StandardCharsets.UTF_8));
        String sign = new BASE64Encoder().encode(hash);

        return "hmac id=\"" + secretId + "\", algorithm=\"hmac-sha1\", headers=\"x-date x-source\", signature=\"" + sign + "\"";
    }

    public static String urlEncode(Map<?,?> map) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (stringBuilder.length() > 0){
                stringBuilder.append("&");
            }
            stringBuilder.append(String.format("%s=%s", URLEncoder.encode(entry.getKey().toString(), String.valueOf(StandardCharsets.UTF_8)),
                    URLEncoder.encode(entry.getValue().toString(), String.valueOf(StandardCharsets.UTF_8))));
        }
        return stringBuilder.toString();
    }
}
