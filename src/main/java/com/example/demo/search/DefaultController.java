package com.example.demo.search;

import com.example.demo.search.persist.elasticsearch.EsSpace;
import com.example.demo.search.persist.elasticsearch.EsSpaceRepo;
import com.example.demo.search.persist.jpa.Space;
import com.example.demo.search.persist.jpa.SpaceRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

  @Autowired
  EsSpaceRepo esCache;

  @Autowired
  SpaceRepo repo;

  @GetMapping("/search/spaces")
  public SearchResult search(SearchRequest r) {
    var esResult = esCache.findBySearchText(r.text, PageRequest.of(r.pageNo, r.pageSize));
    var result = repo.findBySpaceIdIn(
        esResult.getContent().stream().map(EsSpace::getSpaceId).collect(Collectors.toList()));
    return new SearchResult(result, esResult.hasNext(), r.pageNo, r.pageSize);
  }

  @AllArgsConstructor
  @Data
  static class SearchResult {
    List<Space> data;
    boolean hasNext;
    int pageNo;
    int pageSize;
  }

  @Data
  static class SearchRequest{
    String text;
    int pageNo;
    int pageSize;
  }
}
