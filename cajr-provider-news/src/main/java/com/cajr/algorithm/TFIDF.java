package com.cajr.algorithm;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.List;

/**
 * @Author CAJR
 * @create 2020/2/18 22:38
 */
public class TFIDF {
    public static Result spilt(String text){
        return ToAnalysis.parse(text);
    }

    public static List<Keyword> getTfidf(String title ,String content, int keyNum){
        KeyWordComputer keyWordComputer = new KeyWordComputer(keyNum);
        return keyWordComputer.computeArticleTfidf(title, content);
    }

    public static List<Keyword> getTfidf(String content, int keyNum){
        KeyWordComputer keyWordComputer = new KeyWordComputer(keyNum);
        return keyWordComputer.computeArticleTfidf(content);
    }
}
