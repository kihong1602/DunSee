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
import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.response.ApiResponse;
import com.dfo.dunsee.response.charavatar.ResponseCharacterAvatarInfo;
import com.dfo.dunsee.response.charcreature.ResponseCreatureInfo;
import com.dfo.dunsee.response.chardefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.response.charequipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.response.charstatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.response.chartalisman.ResponseCharacterTalismanInfo;
import com.dfo.dunsee.search.dto.ImgUrlParserCharacterInfo;
import com.dfo.dunsee.search.dto.detail.DetailCharInfoDto;
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
public class CharDetailService {

  private final ApiUtilsConfig apiUtilsConfig;
  private final CallApiService callApiService;
  private final CharDataProcessor charDataProcessor;
  private final CharSaveService charSaveService;

  private String serviceMsg;

  public DetailCharInfoDto charDetailSearch(ServiceCode serviceCode, String imgUrl) {
    serviceMsg = ServiceCode.setServiceMsg(serviceCode);
    log.info(serviceMsg + "Sync Character Detail Info Search Service Start");

    ImgUrlParserCharacterInfo imgUrlParserCharacterInfo = apiUtilsConfig.getUrlParser()
                                                                        .imgUrlParseCharacterInfo(serviceCode, imgUrl);

    Map<KeyType, String> urlMap = setCallApiUrlMap(serviceCode, imgUrlParserCharacterInfo.getServerId(),
                                                   imgUrlParserCharacterInfo.getCharacterId());

    Map<KeyType, ApiResponse> resMap = getApiResponseData(serviceCode, urlMap);

    DetailCharInfoDto detailCharInfoDto = buildCharacterDetails(resMap, imgUrl);

    //이쯤에서 반환할예정
    //반환하기 전, 모든 데이터가 가공이 완료되었으면, CompletableFuture를 통해 모험단명과 imgUrl을 DB에 저장
    CompletableFuture.runAsync(() -> saveCharacterInfoProcess(detailCharInfoDto));
    return detailCharInfoDto;
  }


  private void saveCharacterInfoProcess(DetailCharInfoDto detailCharInfoDto) {
    log.info(serviceMsg + "Async Character Info Save Process Start");
    CharacterInfo characterInfo = CharacterInfo.builder().fame(detailCharInfoDto.getFame())
                                                         .imgUrl(detailCharInfoDto.getImgUrl())
                                                         .adventureName(detailCharInfoDto.getAdventureName())
                                                         .build();
    boolean result = charSaveService.saveCharacterInfo(characterInfo);
    if (result) {
      log.info(serviceMsg + "Character Info Save Success");
    } else {
      log.error(serviceMsg + "Character Info Save Fail");
    }
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
    CompletableFuture<ApiResponse> defaultInfo = callApiService.callNeopleApi(serviceCode, urlMap.get(DEFAULT),
                                                                              ResponseCharacterDefaultInfo.class);
    CompletableFuture<ApiResponse> statusInfo = callApiService.callNeopleApi(serviceCode, urlMap.get(STATUS),
                                                                             ResponseCharacterStatusInfo.class);
    CompletableFuture<ApiResponse> equipInfo = callApiService.callNeopleApi(serviceCode, urlMap.get(EQUIP),
                                                                            ResponseCharacterEquipInfo.class);
    CompletableFuture<ApiResponse> creatureInfo = callApiService.callNeopleApi(serviceCode, urlMap.get(CREATURE),
                                                                               ResponseCreatureInfo.class);
    CompletableFuture<ApiResponse> avatarInfo = callApiService.callNeopleApi(serviceCode, urlMap.get(AVATAR),
                                                                             ResponseCharacterAvatarInfo.class);
    CompletableFuture<ApiResponse> talismanInfo = callApiService.callNeopleApi(serviceCode, urlMap.get(TALISMAN),
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

  private DetailCharInfoDto buildCharacterDetails(Map<KeyType, ApiResponse> resMap, String imgUrl) {

    return charDataProcessor.buildCharacterDetails(resMap, imgUrl);
  }

}
