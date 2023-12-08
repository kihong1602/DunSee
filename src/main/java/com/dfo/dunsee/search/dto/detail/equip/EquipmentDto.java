package com.dfo.dunsee.search.dto.detail.equip;

import com.dfo.dunsee.apiresponse.charequipment.Equipment;
import com.dfo.dunsee.apiresponse.charequipment.fusionepic.UpgradeInfo;
import lombok.Builder;
import lombok.Getter;

@Getter
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

  public static EquipmentDto createEquipmentDto(String imgUrl, Equipment equipment) {
    String slotName = equipment.getSlotName();
    String itemName = equipment.getItemName();
    String itemRarity = equipment.getItemRarity();
    String amplificationName = equipment.getAmplificationName();
    int reinforce = equipment.getReinforce();

    String upgradeInfo = null;
    UpgradeInfo upgradeInfoObj = equipment.getUpgradeInfo();
    if (upgradeInfoObj != null) {
      upgradeInfo = upgradeInfoObj.getItemName();
    }

    EnchantDto enchantDto = EnchantDto.createEnchantDto(equipment.getEnchant());
    ReinforceDto reinforceDto = ReinforceDto.createReinforceDto(amplificationName, reinforce);

    return EquipmentDto.builder().imgUrl(imgUrl)
                                 .slotName(slotName)
                                 .itemName(itemName)
                                 .itemRarity(itemRarity)
                                 .reinforceDto(reinforceDto)
                                 .upgradeInfo(upgradeInfo)
                                 .enchantDto(enchantDto)
                                 .build();
  }

  private String upgradeInfoSubstring(String upgradeInfo) {
    if (upgradeInfo == null) {
      return null;
    }
    return upgradeInfo.substring(0, 2);
  }
}
