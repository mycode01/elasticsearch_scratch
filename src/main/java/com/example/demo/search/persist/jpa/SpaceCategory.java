package com.example.demo.search.persist.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity(name = "space_category_ref")
public class SpaceCategory implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;

  @Column(name = "value", length = 32)
  private String value;

  @Column(name = "display_name", length = 32)
  private String displayName;

  @Column(name = "is_shown")
  private boolean isShown;

  @Column(name = "display_order")
  private int displayOrder;

  protected SpaceCategory() {
  }

  public SpaceCategory(String value, String displayName, boolean isShown,
      int displayOrder) {

    this.value = value;
    this.displayName = displayName;
    this.isShown = isShown;
    this.displayOrder = displayOrder;
  }
}
