package com.example.demo.search.persist.jpa;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity(name = "client_inst")
public class Client implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "no")
  private Long no;

  @Column(name = "name", length = 64)
  private String name;

  @Column(name = "thumbnail_url", length = 2000)
  private String thumbnailUrl;

  @Column(name = "logo_url", length = 2000)
  private String logoUrl;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_no")
  private List<Space> spaces;

  @CreatedDate
  @Column(name = "created_at", updatable = false, columnDefinition = "DATETIME(3)")
  Instant createdAt;

  protected Client() {
  }

  public Client(String name, String thumbnailUrl, String logoUrl) {
    this.name = name;
    this.thumbnailUrl = thumbnailUrl;
    this.logoUrl = logoUrl;
  }
}
