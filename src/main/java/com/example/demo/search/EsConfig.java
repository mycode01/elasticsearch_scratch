package com.example.demo.search;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(basePackages = "com.example.demo.search.persist.elasticsearch")
@Configuration
public class EsConfig {

  @Bean
  public RestHighLevelClient esClient(){
    var config = ClientConfiguration.builder()
        .connectedTo("localhost:9200")
        .build();
    return RestClients.create(config).rest();
  }

  @Bean(name = "elasticsearchTemplate") // 이름이 바뀔시 서비스가 빈을 못찾는 경우가 생김
  public ElasticsearchOperations esTemplate(RestHighLevelClient client){
    return new ElasticsearchRestTemplate(client);
  }

}
