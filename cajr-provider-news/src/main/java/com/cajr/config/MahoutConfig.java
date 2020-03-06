package com.cajr.config;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLBooleanPrefJDBCDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author CAJR
 * @date 2020/3/6 2:35 下午
 */
@Configuration
public class MahoutConfig {
    //偏好好表表名
    public static final String PREF_TABLE = "news_logs";

    //用户id 列名
    public static final String PREF_TABLE_USER_ID = "user_id";

    //新闻id 列名
    public static final String PREF_TABLE_NEWS_ID = "news_id";

    //偏好值列名名
    public static final String PREF_TABLE_PREF_VALUE = "prefer_degree";

    //用户浏览时间列名
    public static final String PREF_TABLE_BROWSE_TIME = "view_time";

    @Autowired
    private DataSource dataSource;

    @Bean
    public MySQLBooleanPrefJDBCDataModel mySQLBooleanPrefJDBCDataModel(){
        return new MySQLBooleanPrefJDBCDataModel(dataSource,PREF_TABLE, PREF_TABLE_USER_ID, PREF_TABLE_NEWS_ID, PREF_TABLE_BROWSE_TIME);
    }
}
