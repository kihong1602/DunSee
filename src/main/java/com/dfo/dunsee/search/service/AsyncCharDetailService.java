package com.dfo.dunsee.search.service;

import static com.dfo.dunsee.common.KeyType.DEFAULT;
import static com.dfo.dunsee.common.KeyType.EQUIP;
import static com.dfo.dunsee.common.KeyType.STATUS;

import com.dfo.dunsee.apiresponse.ApiResponse;
import com.dfo.dunsee.apiresponse.characterdefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.apiresponse.characterequipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.apiresponse.characterstatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.common.KeyType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
import com.dfo.dunsee.search.dto.ImgUrlParserCharacterInfo;
import java.util.HashMap;
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

  //받아와야하는 정보
  //캐릭터 기본정보 -> 모험단명 등등
  //능력치 조회 ->
  //장착장비조회
  //-------------------상단부는 추출완료
  // 아바타조회
  //크리쳐, 아티팩트
  //탈리, 룬
  //비동기 병렬처리로 API호출하는걸로

  public void aSyncSearch(ServiceCode serviceCode, String imgUrl) {
    String serviceMsg = ServiceCode.setServiceMsg(serviceCode);
    log.info(serviceMsg + "Sync Character Detail Info Search Service Start");
    long startTime = System.currentTimeMillis();
    ImgUrlParserCharacterInfo imgUrlParserCharacterInfo = apiUtilsConfig.getUrlParser()
                                                                        .imgUrlParseCharacterInfo(serviceCode, imgUrl);
    String serverId = imgUrlParserCharacterInfo.getServerId();
    String characterId = imgUrlParserCharacterInfo.getCharacterId();
    Map<KeyType, String> urlMap = setCallApiUrlMap(serviceCode, serverId, characterId);

    Map<KeyType, ApiResponse> resMap = getApiResponseData(serviceCode, urlMap);

    ResponseCharacterDefaultInfo defaultInfo = (ResponseCharacterDefaultInfo) resMap.get(DEFAULT);
    ResponseCharacterStatusInfo statusInfo = (ResponseCharacterStatusInfo) resMap.get(STATUS);
    ResponseCharacterEquipInfo equipInfo = (ResponseCharacterEquipInfo) resMap.get(EQUIP);
    log.info(serviceMsg + defaultInfo.toString());
    log.info(serviceMsg + statusInfo);
    log.info(serviceMsg + equipInfo);

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
                             .setSearchCharacterEquipUrl(serviceCode, serverId, characterId)
    );
  }

  private Map<KeyType, ApiResponse> getApiResponseData(ServiceCode serviceCode, Map<KeyType, String> urlMap) {
    Map<KeyType, ApiResponse> resMap = new HashMap<>();
    CompletableFuture<ApiResponse> defaultInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(DEFAULT),
                                                                                   ResponseCharacterDefaultInfo.class);
    CompletableFuture<ApiResponse> statusInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(STATUS),
                                                                                  ResponseCharacterStatusInfo.class);
    CompletableFuture<ApiResponse> equipInfo = asyncCallApiService.callNeopleApi(serviceCode, urlMap.get(EQUIP),
                                                                                 ResponseCharacterEquipInfo.class);
    CompletableFuture.allOf(defaultInfo, statusInfo, equipInfo)
                     .join();
    try {
      resMap.put(DEFAULT, defaultInfo.get());
      resMap.put(STATUS, statusInfo.get());
      resMap.put(EQUIP, equipInfo.get());
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread()
            .interrupt();
      throw new RuntimeException(ServiceCode.setServiceMsg(serviceCode) + "\n" + e);
    }
    return resMap;
  }


}
