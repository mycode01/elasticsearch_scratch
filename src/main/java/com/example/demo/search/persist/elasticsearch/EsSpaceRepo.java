package com.example.demo.search.persist.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsSpaceRepo extends ElasticsearchRepository<EsSpace, String> {

  @Query("{\"query_string\":{\"query\":\"?0\"}}")
  Page<EsSpace> findBySearchText(String text, Pageable pageable);
}
