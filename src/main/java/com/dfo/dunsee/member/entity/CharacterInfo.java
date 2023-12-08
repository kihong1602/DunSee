package com.dfo.dunsee.member.entity;

import com.dfo.dunsee.search.dto.detail.DetailCharInfoDto;
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

  private String fame;

  private String adventureName;

  @Builder
  private CharacterInfo(String imgUrl, String fame, String adventureName) {
    this.imgUrl = imgUrl;
    this.fame = fame;
    this.adventureName = adventureName;
  }

  public static CharacterInfo createCharacterInfo(DetailCharInfoDto detailCharInfoDto) {
    return CharacterInfo.builder().fame(detailCharInfoDto.getFame())
                                  .imgUrl(detailCharInfoDto.getImgUrl())
                                  .adventureName(detailCharInfoDto.getAdventureName())
                                  .build();
  }
}
