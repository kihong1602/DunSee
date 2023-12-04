package com.dfo.dunsee.search.dto.detail;

import com.dfo.dunsee.search.dto.detail.avatar.AvatarDto;
import com.dfo.dunsee.search.dto.detail.equip.EquipmentDto;
import com.dfo.dunsee.search.dto.detail.talisman.TalismanDto;
import com.dfo.dunsee.search.dto.detail.word.AvatarSlotType;
import com.dfo.dunsee.search.dto.detail.word.EquipSlotType;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;


public class DetailCharInfoDto {

  private String imgUrl;
  private String characterName;
  private int level;
  private String jobGrowName;
  private String guildName;
  @Getter
  private String adventureName;
  private int fame;
  private StatusDto statusDto;
  private Map<EquipSlotType, EquipmentDto> equipmentDtos;
  private Map<AvatarSlotType, AvatarDto> avatarDtos;
  private List<TalismanDto> talismanDtos;
  private CreatureDto creatureDto;

  @Builder
  private DetailCharInfoDto(NormalCharInfoDto normalCharInfoDto, StatusDto statusDto,
      Map<EquipSlotType, EquipmentDto> equipmentDtos,
      Map<AvatarSlotType, AvatarDto> avatarDtos, List<TalismanDto> talismanDtos, CreatureDto creatureDto) {
    this.imgUrl = normalCharInfoDto.getImgUrl();
    this.characterName = normalCharInfoDto.getCharacterName();
    this.level = normalCharInfoDto.getLevel();
    this.jobGrowName = normalCharInfoDto.getJobGrowName();
    this.guildName = normalCharInfoDto.getGuildName();
    this.adventureName = normalCharInfoDto.getAdventureName();
    this.fame = statusDto.getFame();
    this.statusDto = statusDto;
    this.equipmentDtos = equipmentDtos;
    this.avatarDtos = avatarDtos;
    this.talismanDtos = talismanDtos;
    this.creatureDto = creatureDto;
  }
}
