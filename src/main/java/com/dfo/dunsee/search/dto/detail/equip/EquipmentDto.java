package com.dfo.dunsee.search.dto.detail.equip;

import lombok.Builder;

public class EquipmentDto {

  private String imgUrl;
  private String slotName;
  private String itemName;
  private String itemRarity;
  private ReinforceDto reinforceDto;
  private EnchantDto enchantDto;
  private String upgradeInfo;

  @Builder
  private EquipmentDto(String imgUrl, String slotName, String itemName, String itemRarity, ReinforceDto reinforceDto,
      EnchantDto enchantDto, String upgradeInfo) {
    this.imgUrl = imgUrl;
    this.slotName = slotName;
    this.itemName = itemName;
    this.itemRarity = itemRarity;
    this.reinforceDto = reinforceDto;
    this.enchantDto = enchantDto;
    this.upgradeInfo = upgradeInfoSubstring(upgradeInfo);
  }

  private String upgradeInfoSubstring(String upgradeInfo) {
    if (upgradeInfo == null) {
      return null;
    }
    return upgradeInfo.substring(0, 2);
  }
}
