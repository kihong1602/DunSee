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
import static com.dfo.dunsee.search.dto.detail.word.StatusType.ASPD;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.CAST;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.CENH;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.CRES;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.DENH;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.DRES;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.FAME;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.FENH;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.FRES;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.IATK;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.INT;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.LENH;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.LRES;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.MATK;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.MDEF;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.MSPEED;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.PATK;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.PDEF;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.SPR;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.STR;
import static com.dfo.dunsee.search.dto.detail.word.StatusType.VIT;

import com.dfo.dunsee.common.KeyType;
import com.dfo.dunsee.common.UrlFactory;
import com.dfo.dunsee.response.ApiResponse;
import com.dfo.dunsee.response.charavatar.Avatar;
import com.dfo.dunsee.response.charavatar.ResponseCharacterAvatarInfo;
import com.dfo.dunsee.response.charcreature.ResponseCreatureInfo;
import com.dfo.dunsee.response.chardefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.response.charequipment.Equipment;
import com.dfo.dunsee.response.charequipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.response.charequipment.fusionepic.UpgradeInfo;
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
import com.dfo.dunsee.search.dto.detail.equip.EnchantDto;
import com.dfo.dunsee.search.dto.detail.equip.EquipmentDto;
import com.dfo.dunsee.search.dto.detail.equip.ReinforceDto;
import com.dfo.dunsee.search.dto.detail.talisman.RuneDto;
import com.dfo.dunsee.search.dto.detail.talisman.TalismanDto;
import com.dfo.dunsee.search.dto.detail.word.AvatarSlotType;
import com.dfo.dunsee.search.dto.detail.word.EquipSlotType;
import java.util.ArrayList;
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
    NormalCharInfoDto normalCharInfoDto = createNormalCharInfoDto(resMap.get(DEFAULT), imgUrl);
    StatusDto statusDto = createStatusDto(resMap.get(STATUS));
    Map<EquipSlotType, EquipmentDto> equipmentDtoMap = createEquipmentDto(resMap.get(EQUIP));
    CreatureDto creatureDto = createCreatureDto(resMap.get(CREATURE));
    Map<AvatarSlotType, AvatarDto> avatarDtoMap = createAvatarDto(resMap.get(AVATAR));
    List<TalismanDto> talismanDtoList = createTalismanList(resMap.get(TALISMAN));

    return DetailCharInfoDto.builder()
        .normalCharInfoDto(normalCharInfoDto)
        .statusDto(statusDto)
        .equipmentDtos(equipmentDtoMap)
        .avatarDtos(avatarDtoMap)
        .creatureDto(creatureDto)
        .talismanDtos(talismanDtoList)
        .build();
  }

  private NormalCharInfoDto createNormalCharInfoDto(ApiResponse apiResponse, String imgUrl) {
    ResponseCharacterDefaultInfo defaultInfo = (ResponseCharacterDefaultInfo) apiResponse;

    String characterName = defaultInfo.getCharacterName();
    int level = defaultInfo.getLevel();
    String jobGrowName = defaultInfo.getJobGrowName();
    String guildName = defaultInfo.getGuildName();
    String adventureName = defaultInfo.getAdventureName();

    return NormalCharInfoDto.builder()
        .imgUrl(imgUrl)
        .characterName(characterName)
        .level(level)
        .jobGrowName(jobGrowName)
        .guildName(guildName)
        .adventureName(adventureName)
        .build();
  }

  private StatusDto createStatusDto(ApiResponse apiResponse) {
    Map<String, Status> statusMap = convertStatusListToMap(((ResponseCharacterStatusInfo) apiResponse).getStatus());

    int fame = statusMap.get(FAME.getName()).getValue();
    int strength = statusMap.get(STR.getName()).getValue();
    int intelligence = statusMap.get(INT.getName()).getValue();
    int vitality = statusMap.get(VIT.getName()).getValue();
    int spirit = statusMap.get(SPR.getName()).getValue();
    int physicalAttack = statusMap.get(PATK.getName()).getValue();
    int magicAttack = statusMap.get(MATK.getName()).getValue();
    int independentAttack = statusMap.get(IATK.getName()).getValue();
    double physicalDefense = statusMap.get(PDEF.getName()).getValue();
    double magicDefense = statusMap.get(MDEF.getName()).getValue();
    double attackSpeed = statusMap.get(ASPD.getName()).getValue();
    double castingSpeed = statusMap.get(CAST.getName()).getValue();
    double moveSpeed = statusMap.get(MSPEED.getName()).getValue();
    int fireEnhance = statusMap.get(FENH.getName()).getValue();
    int coldEnhance = statusMap.get(CENH.getName()).getValue();
    int lightEnhance = statusMap.get(LENH.getName()).getValue();
    int darkEnhance = statusMap.get(DENH.getName()).getValue();
    int fireResist = statusMap.get(FRES.getName()).getValue();
    int coldResist = statusMap.get(CRES.getName()).getValue();
    int lightResist = statusMap.get(LRES.getName()).getValue();
    int darkResist = statusMap.get(DRES.getName()).getValue();

    return StatusDto.builder()
        .fame(fame)
        .strength(strength)
        .intelligence(intelligence)
        .vitality(vitality)
        .spirit(spirit)
        .physicalAttack(physicalAttack)
        .magicAttack(magicAttack)
        .independentAttack(independentAttack)
        .physicalDefense(physicalDefense)
        .magicDefense(magicDefense)
        .attackSpeed(attackSpeed)
        .castingSpeed(castingSpeed)
        .moveSpeed(moveSpeed)
        .fireEnhance(fireEnhance)
        .coldEnhance(coldEnhance)
        .lightEnhance(lightEnhance)
        .darkEnhance(darkEnhance)
        .fireResist(fireResist)
        .coldResist(coldResist)
        .lightResist(lightResist)
        .darkResist(darkResist)
        .build();
  }

  private Map<EquipSlotType, EquipmentDto> createEquipmentDto(ApiResponse apiResponse) {
    Map<EquipSlotType, EquipmentDto> equipDtoMap = new EnumMap<>(EquipSlotType.class);
    Map<String, Equipment> equipmentMap = convertEquipListToMap(
        ((ResponseCharacterEquipInfo) apiResponse).getEquipment());

    for (EquipSlotType equipSlotType : EquipSlotType.values()) {

      String imgUrl = urlFactory.setItemImgUrl(equipmentMap.get(equipSlotType.name()).getItemId());
      String slotName = equipmentMap.get(equipSlotType.name()).getSlotName();
      String itemName = equipmentMap.get(equipSlotType.name()).getItemName();
      String itemRarity = equipmentMap.get(equipSlotType.name()).getItemRarity();
      String amplificationName = equipmentMap.get(equipSlotType.name()).getAmplificationName();
      int reinforce = equipmentMap.get(equipSlotType.name()).getReinforce();

      String upgradeInfo = null;
      UpgradeInfo upgradeInfoObj = equipmentMap.get(equipSlotType.name()).getUpgradeInfo();
      if (upgradeInfoObj != null) {
        upgradeInfo = upgradeInfoObj.getItemName();
      }

      EnchantDto enchantDto = EnchantDto.createEnchantDto(equipmentMap.get(equipSlotType.name()).getEnchant());
      ReinforceDto reinforceDto = ReinforceDto.createReinforceDto(amplificationName, reinforce);

      EquipmentDto build = EquipmentDto.builder().imgUrl(imgUrl)
                                                 .slotName(slotName)
                                                 .itemName(itemName)
                                                 .itemRarity(itemRarity)
                                                 .reinforceDto(reinforceDto)
                                                 .upgradeInfo(upgradeInfo)
                                                 .enchantDto(enchantDto)
                                                 .build();
      equipDtoMap.put(equipSlotType, build);
    }

    return equipDtoMap;
  }

  private Map<AvatarSlotType, AvatarDto> createAvatarDto(ApiResponse apiResponse) {
    Map<AvatarSlotType, AvatarDto> avatarDtoMap = new EnumMap<>(AvatarSlotType.class);
    Map<String, Avatar> avatarMap = convertAvatarListToMap(((ResponseCharacterAvatarInfo) apiResponse).getAvatar());

    for (AvatarSlotType avatarSlotType : values()) {
      String imgUrl;
      String itemName;
      if (avatarSlotType.equals(SKIN) || avatarSlotType.equals(AURORA)) {
        imgUrl = urlFactory.setItemImgUrl(avatarMap.get(avatarSlotType.name()).getItemId());
        itemName = avatarMap.get(avatarSlotType.name()).getItemName();
      } else {
        imgUrl = urlFactory.setItemImgUrl(avatarMap.get(avatarSlotType.name()).getClone().getItemId());
        itemName = avatarMap.get(avatarSlotType.name()).getClone().getItemName();
      }
      String slotName = avatarMap.get(avatarSlotType.name()).getSlotName();
      String optionAbility = avatarMap.get(avatarSlotType.name()).getOptionAbility();
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
    String imgUrl = urlFactory.setItemImgUrl(creatureInfo.getCreature().getItemId());
    String itemName = creatureInfo.getCreature().getItemName();
    return CreatureDto.builder().imgUrl(imgUrl).itemName(itemName).build();
  }

  private List<TalismanDto> createTalismanList(ApiResponse apiResponse) {

    ResponseCharacterTalismanInfo talismanInfo = (ResponseCharacterTalismanInfo) apiResponse;
    List<TalismanDto> talismanDtoList = new ArrayList<>();
    List<Talisman> talismans = talismanInfo.getTalismans();

    for (Talisman talisman : talismans) {
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
