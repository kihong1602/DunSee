package com.dfo.dunsee.search.service;

import static com.dfo.dunsee.common.KeyType.AVATAR;
import static com.dfo.dunsee.common.KeyType.CREATURE;
import static com.dfo.dunsee.common.KeyType.DEFAULT;
import static com.dfo.dunsee.common.KeyType.EQUIP;
import static com.dfo.dunsee.common.KeyType.STATUS;
import static com.dfo.dunsee.common.KeyType.TALISMAN;
import static com.dfo.dunsee.search.dto.detail.word.AvatarSlotType.AURORA;
import static com.dfo.dunsee.search.dto.detail.word.AvatarSlotType.SKIN;
import static com.dfo.dunsee.search.dto.detail.word.AvatarSlotType.values;

import com.dfo.dunsee.common.KeyType;
import com.dfo.dunsee.common.UrlFactory;
import com.dfo.dunsee.response.ApiResponse;
import com.dfo.dunsee.response.charavatar.Avatar;
import com.dfo.dunsee.response.charavatar.ResponseCharacterAvatarInfo;
import com.dfo.dunsee.response.charcreature.ResponseCreatureInfo;
import com.dfo.dunsee.response.chardefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.response.charequipment.Equipment;
import com.dfo.dunsee.response.charequipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.response.charstatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.response.charstatus.Status;
import com.dfo.dunsee.response.chartalisman.ResponseCharacterTalismanInfo;
import com.dfo.dunsee.response.chartalisman.Rune;
import com.dfo.dunsee.response.chartalisman.Talisman;
import com.dfo.dunsee.search.dto.detail.CreatureDto;
import com.dfo.dunsee.search.dto.detail.DetailCharInfoDto;
import com.dfo.dunsee.search.dto.detail.NormalCharInfoDto;
import com.dfo.dunsee.search.dto.detail.StatusDto;
import com.dfo.dunsee.search.dto.detail.avatar.AvatarDto;
import com.dfo.dunsee.search.dto.detail.avatar.EmblemDto;
import com.dfo.dunsee.search.dto.detail.equip.EquipmentDto;
import com.dfo.dunsee.search.dto.detail.talisman.RuneDto;
import com.dfo.dunsee.search.dto.detail.talisman.TalismanDto;
import com.dfo.dunsee.search.dto.detail.word.AvatarSlotType;
import com.dfo.dunsee.search.dto.detail.word.EquipSlotType;
import com.dfo.dunsee.search.dto.detail.word.StatusType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharDataProcessor {

  private final UrlFactory urlFactory;

  public DetailCharInfoDto buildCharacterDetails(Map<KeyType, ApiResponse> resMap, String imgUrl) {

    return DetailCharInfoDto.builder()
        .imgUrl(imgUrl)
        .normalCharInfoDto(createNormalCharInfoDto(resMap.get(DEFAULT)))
        .statusDtos(createStatusDto(resMap.get(STATUS)))
        .equipmentDtos(createEquipmentDto(resMap.get(EQUIP)))
        .avatarDtos(createAvatarDto(resMap.get(AVATAR)))
        .creatureDto(createCreatureDto(resMap.get(CREATURE)))
        .talismanDtos(createTalismanList(resMap.get(TALISMAN)))
        .build();
  }

  private NormalCharInfoDto createNormalCharInfoDto(ApiResponse apiResponse) {
    ResponseCharacterDefaultInfo defaultInfo = (ResponseCharacterDefaultInfo) apiResponse;

    return NormalCharInfoDto.createNormalCharInfoDto(defaultInfo);
  }

  private Map<StatusType, StatusDto> createStatusDto(ApiResponse apiResponse) {
    Map<StatusType, StatusDto> statusDtoMap = new EnumMap<>(StatusType.class);
    Map<String, Status> statusMap = convertStatusListToMap(((ResponseCharacterStatusInfo) apiResponse).getStatus());

    for (StatusType statusType : StatusType.values()) {
      Status status = statusMap.get(statusType.getName());
      statusDtoMap.put(statusType, StatusDto.createStatusDto(status));
    }

    return statusDtoMap;
  }

  private Map<EquipSlotType, EquipmentDto> createEquipmentDto(ApiResponse apiResponse) {
    Map<EquipSlotType, EquipmentDto> equipDtoMap = new EnumMap<>(EquipSlotType.class);
    List<Equipment> equipmentList = ((ResponseCharacterEquipInfo) apiResponse).getEquipment();
    if (equipmentList == null) {
      return Collections.emptyMap();
    }

    Map<String, Equipment> equipmentMap = convertEquipListToMap(equipmentList);

    for (EquipSlotType equipSlotType : EquipSlotType.values()) {
      if (equipmentMap.get(equipSlotType.name()) == null) {
        continue;
      }
      Equipment equipment = equipmentMap.get(equipSlotType.name());
      String imgUrl = urlFactory.setItemImgUrl(equipment.getItemId());

      EquipmentDto equipmentDto = EquipmentDto.createEquipmentDto(imgUrl, equipment);

      equipDtoMap.put(equipSlotType, equipmentDto);
    }

    return equipDtoMap;
  }

  private Map<AvatarSlotType, AvatarDto> createAvatarDto(ApiResponse apiResponse) {
    List<Avatar> avatarList = ((ResponseCharacterAvatarInfo) apiResponse).getAvatar();
    if (avatarList == null) {
      return Collections.emptyMap();
    }

    Map<AvatarSlotType, AvatarDto> avatarDtoMap = new EnumMap<>(AvatarSlotType.class);
    Map<String, Avatar> avatarMap = convertAvatarListToMap(avatarList);

    for (AvatarSlotType avatarSlotType : values()) {
      Avatar avatar = avatarMap.get(avatarSlotType.name());
      if (avatar == null) {
        continue;
      }
      String imgUrl;
      String itemName;
      if (avatarSlotType.equals(SKIN) || avatarSlotType.equals(AURORA)) {
        imgUrl = urlFactory.setItemImgUrl(avatar.getItemId());
        itemName = avatar.getItemName();
      } else {
        imgUrl = urlFactory.setItemImgUrl(avatar.getClone().getItemId());
        itemName = avatar.getClone().getItemName();
      }
      String slotName = avatar.getSlotName();
      String optionAbility = avatar.getOptionAbility();
      List<EmblemDto> emblemDtos = createEblemDtoList(avatarSlotType, avatarMap);

      AvatarDto avatarDto = AvatarDto.builder()
          .imgUrl(imgUrl)
          .slotName(slotName)
          .itemName(itemName)
          .optionAbility(optionAbility)
          .emblemDtos(emblemDtos)
          .build();
      avatarDtoMap.put(avatarSlotType, avatarDto);
    }

    return avatarDtoMap;
  }

  private List<EmblemDto> createEblemDtoList(AvatarSlotType avatarSlotType, Map<String, Avatar> avatarMap) {
    return avatarMap.get(avatarSlotType.name())
                    .getEmblems()
                    .stream()
                    .map(EmblemDto::createEmblemDto)
                    .toList();
  }

  private CreatureDto createCreatureDto(ApiResponse apiResponse) {
    ResponseCreatureInfo creatureInfo = (ResponseCreatureInfo) apiResponse;
    if (creatureInfo.getCreature() == null) {
      return null;
    }

    String imgUrl = urlFactory.setItemImgUrl(creatureInfo.getCreature().getItemId());
    String itemName = creatureInfo.getCreature().getItemName();
    return CreatureDto.builder().imgUrl(imgUrl).itemName(itemName).build();
  }

  private List<TalismanDto> createTalismanList(ApiResponse apiResponse) {
    ResponseCharacterTalismanInfo talismanInfo = (ResponseCharacterTalismanInfo) apiResponse;
    if (talismanInfo.getTalismans() == null) {
      return Collections.emptyList();
    }

    List<TalismanDto> talismanDtoList = new ArrayList<>();
    List<Talisman> talismans = talismanInfo.getTalismans();

    for (Talisman talisman : talismans) {
      if (talisman.getTalisman() == null) {
        continue;
      }
      String imgUrl = urlFactory.setItemImgUrl(talisman.getTalisman().getItemId());
      String itemName = talisman.getTalisman().getItemName();
      List<RuneDto> runeDtos = createRuneDtoList(talisman);

      TalismanDto talismanDto = TalismanDto.builder().imgUrl(imgUrl)
                                                     .itemName(itemName)
                                                     .runeDtos(runeDtos)
                                                     .build();
      talismanDtoList.add(talismanDto);
    }

    return talismanDtoList;
  }

  private List<RuneDto> createRuneDtoList(Talisman talisman) {
    List<RuneDto> runeDtos = new ArrayList<>();
    for (Rune rune : talisman.getRunes()) {
      String runeImgUrl = urlFactory.setItemImgUrl(rune.getItemId());
      String runeItemName = rune.getItemName();
      RuneDto runeDto = RuneDto.builder().imgUrl(runeImgUrl).itemName(runeItemName).build();
      runeDtos.add(runeDto);
    }
    return runeDtos;
  }

  private Map<String, Status> convertStatusListToMap(List<Status> statusList) {
    return statusList.stream()
                     .collect(Collectors.toMap(Status::getName, status -> status));
  }

  private Map<String, Equipment> convertEquipListToMap(List<Equipment> equipmentList) {
    return equipmentList.stream()
                        .collect(Collectors.toMap(Equipment::getSlotId, equipment -> equipment));
  }

  private Map<String, Avatar> convertAvatarListToMap(List<Avatar> avatarList) {
    return avatarList.stream().collect(Collectors.toMap(Avatar::getSlotId, avatar -> avatar));
  }
}
