package com.example.demo.search.persist.jpa;

import com.example.demo.search.type.LanguageCode;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "space_inst")
public class Space implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;

  @Column(name = "space_id", length = 16, unique = true, nullable = false)
  private String spaceId;

  @ElementCollection
  @Fetch(FetchMode.JOIN)
  @CollectionTable(name = "space_words_inst", indexes = @Index(columnList = "space_id"),
      joinColumns = @JoinColumn(name = "space_id", referencedColumnName = "space_id"))
  @MapKeyJoinColumn(name = "lang_code")
  @MapKeyColumn(name = "lang_code", length = 8)
  @MapKeyEnumerated(EnumType.STRING)
  private Map<LanguageCode, SpaceText> words;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "mapper_category_space", joinColumns = @JoinColumn(name = "space_no"),
      inverseJoinColumns = @JoinColumn(name = "category_no"))
  private Set<SpaceCategory> categories;

  @OneToMany(cascade = CascadeType.ALL, fetch= FetchType.EAGER)
  @JoinColumn(name = "space_id", referencedColumnName = "space_id")
  @OrderBy("sequence asc")
  private Set<SpaceTag> tags;

  @CreatedDate
  @Column(name = "createdAt", updatable = false, columnDefinition = "DATETIME(3)")
  Instant createdAt;

  protected Space() {}

  public SpaceText getWord(LanguageCode langCode){
    return words.getOrDefault(langCode, new SpaceText("", ""));
  }

  public <T> List<T> categoriesAs(Function<SpaceCategory, T> mapper){
    return this.categories.stream().map(mapper).collect(Collectors.toList());
  }
  public <T> List<T> tagsAs(Function<SpaceTag, T> mapper){
    return this.tags.stream().map(mapper).collect(Collectors.toList());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Space that = (Space) o;
    return Objects.equals(no, that.no) && Objects.equals(spaceId, that.spaceId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, spaceId);
  }
}
