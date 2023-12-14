package com.dfo.dunsee.domain.search.dto.detail;

import static com.dfo.dunsee.domain.search.dto.detail.word.StatusType.FAME;

import com.dfo.dunsee.domain.search.dto.detail.avatar.AvatarDto;
import com.dfo.dunsee.domain.search.dto.detail.equip.EquipmentDto;
import com.dfo.dunsee.domain.search.dto.detail.talisman.TalismanDto;
import com.dfo.dunsee.domain.search.dto.detail.word.AvatarSlotType;
import com.dfo.dunsee.domain.search.dto.detail.word.EquipSlotType;
import com.dfo.dunsee.domain.search.dto.detail.word.StatusType;
import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;


@Getter
public class DetailCharInfoDto {

  private String imgUrl;
  private String characterName;
  private int level;
  private String jobGrowName;
  private String guildName;
  private String adventureName;
  private String fame;
  private Map<StatusType, StatusDto> statusDto;
  private Map<EquipSlotType, EquipmentDto> equipmentDtos;
  private Map<AvatarSlotType, AvatarDto> avatarDtos;
  private List<TalismanDto> talismanDtos;
  private CreatureDto creatureDto;

  @Builder
  private DetailCharInfoDto(String imgUrl, NormalCharInfoDto normalCharInfoDto, Map<StatusType, StatusDto> statusDtos,
      Map<EquipSlotType, EquipmentDto> equipmentDtos,
      Map<AvatarSlotType, AvatarDto> avatarDtos, List<TalismanDto> talismanDtos, CreatureDto creatureDto) {
    this.imgUrl = imgUrl;
    this.characterName = normalCharInfoDto.getCharacterName();
    this.level = normalCharInfoDto.getLevel();
    this.jobGrowName = normalCharInfoDto.getJobGrowName();
    this.guildName = normalCharInfoDto.getGuildName();
    this.adventureName = normalCharInfoDto.getAdventureName();
    this.fame = statusDtos.get(FAME).getValue();
    this.statusDto = statusDtos;
    this.equipmentDtos = equipmentDtos;
    this.avatarDtos = avatarDtos;
    this.talismanDtos = talismanDtos;
    this.creatureDto = creatureDto;
  }
}
