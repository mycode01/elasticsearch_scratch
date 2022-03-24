package com.example.demo.search.persist.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Getter
@Entity
@Table(name = "space_tag_inst",
    uniqueConstraints = @UniqueConstraint(name = "UQ_space_tag_inst_01", columnNames = {"space_id", "tag"}))
public class SpaceTag implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long no;

  @Column(name = "tag", length = 16)
  private String tag;

  @Column(name = "sequence")
  private int sequence;

  protected SpaceTag() {
  }

  public SpaceTag(String tag, int sequence) {
    this.tag = tag;
    this.sequence = sequence;
  }
}
