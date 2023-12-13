package com.dfo.dunsee.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.Getter;

@MappedSuperclass
@Getter
public class BaseEntity {

  @Column(name = "created_date", updatable = false)
  private LocalDateTime createdDate;

  @Column(name = "modified_date")
  private LocalDateTime modifiedDate;


  @PrePersist
  protected void onCreate() {
    createdDate = LocalDateTime.now();
    modifiedDate = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    modifiedDate = LocalDateTime.now();
  }

}
