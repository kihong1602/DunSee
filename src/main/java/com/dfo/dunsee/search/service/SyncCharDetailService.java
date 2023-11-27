package com.dfo.dunsee.search.service;

import static com.dfo.dunsee.common.KeyType.AVATAR;
import static com.dfo.dunsee.common.KeyType.CREATURE;
import static com.dfo.dunsee.common.KeyType.DEFAULT;
import static com.dfo.dunsee.common.KeyType.EQUIP;
import static com.dfo.dunsee.common.KeyType.STATUS;
import static com.dfo.dunsee.common.KeyType.TALISMAN;

import com.dfo.dunsee.common.KeyType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
import com.dfo.dunsee.response.charavatar.ResponseCharacterAvatarInfo;
import com.dfo.dunsee.response.charcreature.ResponseCreatureInfo;
import com.dfo.dunsee.response.chardefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.response.charequipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.response.charstatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.response.chartalisman.ResponseCharacterTalismanInfo;
import com.dfo.dunsee.search.dto.ImgUrlParserCharacterInfo;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SyncCharDetailService {

  private final ApiUtilsConfig apiUtilsConfig;

  public void syncSearch(ServiceCode serviceCode, String imgUrl) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Sync Character Detail Info Search Service Start");
    long startTime = System.currentTimeMillis();
    ImgUrlParserCharacterInfo imgUrlParserCharacterInfo = apiUtilsConfig.getUrlParser()
                                                                        .imgUrlParseCharacterInfo(serviceCode, imgUrl);

    String serverId = imgUrlParserCharacterInfo.getServerId();
    String characterId = imgUrlParserCharacterInfo.getCharacterId();
    Map<KeyType, String> apiUrlMap = setCallApiUrlMap(serviceCode, serverId, characterId);

    ResponseCharacterDefaultInfo defaultInfo = apiUtilsConfig.getApiUtils()
                                                             .getApiResponseJson(serviceCode, apiUrlMap.get(DEFAULT),
                                                                                 ResponseCharacterDefaultInfo.class);
    ResponseCharacterStatusInfo statusInfo = apiUtilsConfig.getApiUtils()
                                                           .getApiResponseJson(serviceCode, apiUrlMap.get(STATUS),
                                                                               ResponseCharacterStatusInfo.class);
    ResponseCharacterEquipInfo equipInfo = apiUtilsConfig.getApiUtils()
                                                         .getApiResponseJson(serviceCode, apiUrlMap.get(EQUIP),
                                                                             ResponseCharacterEquipInfo.class);
    ResponseCreatureInfo creatureInfo = apiUtilsConfig.getApiUtils()
                                                      .getApiResponseJson(serviceCode, apiUrlMap.get(CREATURE),
                                                                          ResponseCreatureInfo.class);
    ResponseCharacterAvatarInfo avatarInfo = apiUtilsConfig.getApiUtils()
                                                           .getApiResponseJson(serviceCode, apiUrlMap.get(AVATAR),
                                                                               ResponseCharacterAvatarInfo.class);
    ResponseCharacterTalismanInfo talismanInfo = apiUtilsConfig.getApiUtils()
                                                               .getApiResponseJson(serviceCode,
                                                                                   apiUrlMap.get(TALISMAN),
                                                                                   ResponseCharacterTalismanInfo.class);
    log.info(defaultInfo.toString());
    log.info(statusInfo.toString());
    log.info(equipInfo.toString());
    log.info(creatureInfo.toString());
    log.info(avatarInfo.toString());
    log.info(talismanInfo.toString());

    long endTime = System.currentTimeMillis();
    long workTime = endTime - startTime;
    log.info("동기처리 작업시간 :: {} ms", workTime);
  }

  private Map<KeyType, String> setCallApiUrlMap(ServiceCode serviceCode, String serverId, String characterId) {
    return Map.of(
        DEFAULT, apiUtilsConfig.getUrlFactory()
                               .setSearchCharacterDefaultUrl(serviceCode, serverId, characterId),
        STATUS, apiUtilsConfig.getUrlFactory()
                              .setSearchCharacterStatusUrl(serviceCode, serverId, characterId),
        EQUIP, apiUtilsConfig.getUrlFactory()
                             .setSearchCharacterEquipUrl(serviceCode, serverId, characterId),
        CREATURE, apiUtilsConfig.getUrlFactory()
                                .setSearchCreatureUrl(serviceCode, serverId, characterId),
        AVATAR, apiUtilsConfig.getUrlFactory()
                              .setSearchCharacterAvatarUrl(serviceCode, serverId, characterId),
        TALISMAN, apiUtilsConfig.getUrlFactory()
                                .setSearchTalismanUrl(serviceCode, serverId, characterId)
    );
  }
}
