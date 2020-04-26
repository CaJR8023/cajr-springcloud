package com.cajr.config;

import com.aliyun.opensearch.DocumentClient;
import com.aliyun.opensearch.OpenSearchClient;
import com.aliyun.opensearch.SearcherClient;
import com.aliyun.opensearch.sdk.generated.OpenSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

/**
 * @author CAJR
 * @date 2020/4/26 12:41 下午
 */

public class OpenSearchConfig {
    @Value("${aliyun.access-key}")
    private String accessKey;

    @Value("${aliyun.secret-key}")
    private String secretKey;

    @Value("${aliyun.open-search.host}")
    private String host;

    @Value("${aliyun.open-search.app-name}")
    private String appName;

    @Bean(name = "openSearchClient")
    public OpenSearchClient openSearchClient(){
        OpenSearch openSearch = new OpenSearch(accessKey,secretKey,host);
        return new OpenSearchClient(openSearch);
    }

    @Bean
    @DependsOn("openSearchClient")
    public DocumentClient documentClient(OpenSearchClient openSearchClient){
        return new DocumentClient(openSearchClient);
    }

    @Bean
    @DependsOn("openSearchClient")
    public SearcherClient searcherClient(OpenSearchClient openSearchClient) {
        return new SearcherClient(openSearchClient);
    }
}
