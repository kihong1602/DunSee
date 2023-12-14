package com.dfo.dunsee.domain.search.dto.detail.avatar;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AvatarDto {

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
