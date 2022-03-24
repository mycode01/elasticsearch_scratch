package com.example.demo.search.persist;

import com.example.demo.search.persist.elasticsearch.EsSpace;
import com.example.demo.search.persist.jpa.Space;
import com.example.demo.search.persist.jpa.SpaceCategory;
import com.example.demo.search.persist.jpa.SpaceTag;
import com.example.demo.search.persist.jpa.SpaceText;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class DocumentConverter {

  public EsSpace toEs(Space space) {
    var titles = getTitles(space);
    var catNames = space.categoriesAs(SpaceCategory::getDisplayName);
    var tags = space.tagsAs(SpaceTag::getTag);
    return new EsSpace(space.getSpaceId(), titles, catNames, tags);
  }

  private List<String> getTitles(Space space) {
    return space.getWords().values().stream().map(SpaceText::getTitle)
        .collect(Collectors.toList());
  }

}
