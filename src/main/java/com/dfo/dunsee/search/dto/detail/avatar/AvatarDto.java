package com.dfo.dunsee.search.dto.detail.avatar;

import java.util.List;
import lombok.Builder;

public class AvatarDto {

  //itemId
  // slotName -> 모자아바타, 목가슴 아바타 ,...
  // itemName -> clone.getItemName()
  // optionAbility -> 아바타 능력치
  // emblems -> 아바타 엠블렘

  private String imgUrl;
  private String slotName;
  private String itemName;
  private String optionAbility;
  private List<EmblemDto> emblemDtos;

  @Builder
  private AvatarDto(String imgUrl, String slotName, String itemName, String optionAbility, List<EmblemDto> emblemDtos) {
    this.imgUrl = imgUrl;
    this.slotName = slotName;
    this.itemName = itemName;
    this.optionAbility = optionAbility;
    this.emblemDtos = emblemDtos;
  }
}
