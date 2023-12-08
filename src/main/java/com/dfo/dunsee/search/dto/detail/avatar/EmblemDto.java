package com.dfo.dunsee.search.dto.detail.avatar;

import com.dfo.dunsee.apiresponse.charavatar.Emblem;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EmblemDto {

  private String itemName;
  private String itemRarity;

  @Builder
  private EmblemDto(String itemName, String itemRarity) {
    this.itemName = itemName;
    this.itemRarity = itemRarity;
  }

  public static EmblemDto createEmblemDto(Emblem emblem) {
    return EmblemDto.builder()
        .itemName(emblem.getItemName())
        .itemRarity(emblem.getItemRarity())
        .build();
  }
}
