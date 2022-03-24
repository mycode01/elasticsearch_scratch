package com.example.demo.search.persist.elasticsearch;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "space",
    createIndex = true) // filter, tokenizer 커스텀시 해당 속성 false
public class EsSpace {
  @Id
  private String spaceId;

  private List<String> title;
  // desc 를 검색대상으로 지정하게 되면 악용할수 있으므로 제외
  // Tag도 마찬가지의 이유에서 갯수 제한을 해두는편이 좋을것임.

  private List<String> categoryName;

  private List<String> tags;


}
