package com.example.demo.search.persist.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class SpaceText implements Serializable {

  @Column(name = "title", length = 64)
  private String title;

  @Column(name = "description", columnDefinition = "TEXT")
  private String desc;

  protected SpaceText() {
  }

  public SpaceText(String title, String desc) {
    this.title = title;
    this.desc = desc;
  }
}
