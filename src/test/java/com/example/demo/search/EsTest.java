package com.example.demo.search;

import com.example.demo.search.persist.DocumentConverter;
import com.example.demo.search.persist.elasticsearch.EsSpaceRepo;
import com.example.demo.search.persist.jpa.SpaceRepo;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@Slf4j
@SpringBootTest
public class EsTest {

  @Autowired
  EsSpaceRepo esCache;

  @Autowired
  SpaceRepo spaceRepo;

  @Autowired
  ElasticsearchOperations esOperations;

  @Autowired
  DocumentConverter converter;

  @BeforeEach
  void loader(){
    //docker run -d --name es7152 -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.15.2
    esCache.deleteAll();
    var esSpaces = spaceRepo.findAll().stream().map(converter::toEs).collect(Collectors.toList());
    esCache.saveAll(esSpaces);
  }

  @Test
  void test(){
    var text = "전시";
    var result = esCache.findBySearchText(text, PageRequest.of(0, 100));
    result.getContent().forEach(System.out::println);
    Assertions.assertTrue(result.getTotalElements() > 0);
    System.out.println(result.getTotalElements());
  }
}
