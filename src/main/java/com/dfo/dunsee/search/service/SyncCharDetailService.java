package com.dfo.dunsee.search.service;

import com.dfo.dunsee.apiResponse.characterDefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.apiResponse.characterEquipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.apiResponse.characterStatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
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
  private final String DEFAULT = "default";
  private final String STATUS = "status";
  private final String EQUIP = "equip";

  public void syncSearch(ServiceCode serviceCode, String imgUrl) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Sync Character Detail Info Search Service Start");
    long startTime = System.currentTimeMillis();
    ImgUrlParserCharacterInfo imgUrlParserCharacterInfo = apiUtilsConfig.getUrlParser()
                                                                        .imgUrlParseCharacterInfo(serviceCode, imgUrl);

    String serverId = imgUrlParserCharacterInfo.getServerId();
    String characterId = imgUrlParserCharacterInfo.getCharacterId();
    Map<String, String> apiUrlMap = setCallApiUrlMap(serviceCode, serverId, characterId);

    ResponseCharacterDefaultInfo defaultInfo = apiUtilsConfig.getApiUtils()
                                                             .getApiResponseJson(serviceCode, apiUrlMap.get(DEFAULT),
                                                                                 ResponseCharacterDefaultInfo.class);
    ResponseCharacterStatusInfo statusInfo = apiUtilsConfig.getApiUtils()
                                                           .getApiResponseJson(serviceCode, apiUrlMap.get(STATUS),
                                                                               ResponseCharacterStatusInfo.class);
    ResponseCharacterEquipInfo equipInfo = apiUtilsConfig.getApiUtils()
                                                         .getApiResponseJson(serviceCode, apiUrlMap.get(EQUIP),
                                                                             ResponseCharacterEquipInfo.class);
    log.info(defaultInfo.toString());
    log.info(statusInfo.toString());
    log.info(equipInfo.toString());
    long endTime = System.currentTimeMillis();
    long workTime = endTime - startTime;
    log.info("동기처리 작업시간 :: {} ms", workTime);
  }

  private Map<String, String> setCallApiUrlMap(ServiceCode serviceCode, String serverId, String characterId) {
    return Map.of(
        DEFAULT, apiUtilsConfig.getUrlFactory()
                               .setSearchCharacterDefaultUrl(serviceCode, serverId, characterId),
        STATUS, apiUtilsConfig.getUrlFactory()
                              .setSearchCharacterStatusUrl(serviceCode, serverId, characterId),
        EQUIP, apiUtilsConfig.getUrlFactory()
                             .setSearchCharacterEquipUrl(serviceCode, serverId, characterId)
    );
  }
}
