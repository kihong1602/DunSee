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
import com.dfo.dunsee.response.ApiResponse;
import com.dfo.dunsee.response.charavatar.ResponseCharacterAvatarInfo;
import com.dfo.dunsee.response.charcreature.ResponseCreatureInfo;
import com.dfo.dunsee.response.chardefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.response.charequipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.response.charstatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.response.chartalisman.ResponseCharacterTalismanInfo;
import com.dfo.dunsee.search.dto.ImgUrlParserCharacterInfo;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncCharDetailService {

  private final ApiUtilsConfig apiUtilsConfig;
  private final AsyncCallApiService asyncCallApiService;
  private String serviceMsg;

  public void aSyncSearch(ServiceCode serviceCode, String imgUrl) {
    serviceMsg = ServiceCode.setServiceMsg(serviceCode);
    log.info(serviceMsg + "Sync Character Detail Info Search Service Start");
    long startTime = System.currentTimeMillis();

    ImgUrlParserCharacterInfo imgUrlParserCharacterInfo = apiUtilsConfig.getUrlParser()
                                                                        .imgUrlParseCharacterInfo(serviceCode, imgUrl);

    Map<KeyType, String> urlMap = setCallApiUrlMap(serviceCode, imgUrlParserCharacterInfo.getServerId(),
                                                   imgUrlParserCharacterInfo.getCharacterId());

    Map<KeyType, ApiResponse> resMap = getApiResponseData(serviceCode, urlMap);

    buildCharacterDetails(resMap);

    long endTime = System.currentTimeMillis();
    long workTime = endTime - startTime;
    log.info("비동기처리 작업시간 :: {} ms", workTime);
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

  private Map<KeyType, ApiResponse> getApiResponseData(ServiceCode serviceCode, Map<KeyType, String> urlMap) {
    Map<KeyType, ApiResponse> resMap = new EnumMap<>(KeyType.class);
    CompletableFuture<ApiResponse> defaultInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(DEFAULT),
                                                                                   ResponseCharacterDefaultInfo.class);
    CompletableFuture<ApiResponse> statusInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(STATUS),
                                                                                  ResponseCharacterStatusInfo.class);
    CompletableFuture<ApiResponse> equipInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(EQUIP),
                                                                                 ResponseCharacterEquipInfo.class);
    CompletableFuture<ApiResponse> creatureInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(CREATURE),
                                                                                    ResponseCreatureInfo.class);
    CompletableFuture<ApiResponse> avatarInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(AVATAR),
                                                                                  ResponseCharacterAvatarInfo.class);
    CompletableFuture<ApiResponse> talismanInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(TALISMAN),
                                                                                    ResponseCharacterTalismanInfo.class);
    CompletableFuture.allOf(defaultInfo, statusInfo, equipInfo, creatureInfo, avatarInfo, talismanInfo)
                     .join();
    try {
      resMap.put(DEFAULT, defaultInfo.get());
      resMap.put(STATUS, statusInfo.get());
      resMap.put(EQUIP, equipInfo.get());
      resMap.put(CREATURE, creatureInfo.get());
      resMap.put(AVATAR, avatarInfo.get());
      resMap.put(TALISMAN, talismanInfo.get());
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread()
            .interrupt();
      throw new RuntimeException(ServiceCode.setServiceMsg(serviceCode) + "\n" + e);
    }
    return resMap;
  }

  private void buildCharacterDetails(Map<KeyType, ApiResponse> resMap) {
    ResponseCharacterDefaultInfo defaultInfo = (ResponseCharacterDefaultInfo) resMap.get(DEFAULT);
    ResponseCharacterStatusInfo statusInfo = (ResponseCharacterStatusInfo) resMap.get(STATUS);
    ResponseCharacterEquipInfo equipInfo = (ResponseCharacterEquipInfo) resMap.get(EQUIP);
    ResponseCreatureInfo creatureInfo = (ResponseCreatureInfo) resMap.get(CREATURE);
    ResponseCharacterAvatarInfo avatarInfo = (ResponseCharacterAvatarInfo) resMap.get(AVATAR);
    ResponseCharacterTalismanInfo talismanInfo = (ResponseCharacterTalismanInfo) resMap.get(TALISMAN);
    log.info(serviceMsg + defaultInfo);
    log.info(serviceMsg + statusInfo);
    log.info(serviceMsg + equipInfo);
    log.info(serviceMsg + creatureInfo);
    log.info(serviceMsg + avatarInfo);
    log.info(serviceMsg + talismanInfo);
  }

}
