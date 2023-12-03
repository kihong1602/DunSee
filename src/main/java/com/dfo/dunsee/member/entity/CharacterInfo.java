package com.dfo.dunsee.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "character_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CharacterInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String imgUrl;

  @Builder
  private CharacterInfo(String imgUrl) {
    this.imgUrl = imgUrl;
  }
}
