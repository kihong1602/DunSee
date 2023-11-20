package com.dfo.dunsee.search.service;

import com.dfo.dunsee.apiResponse.characterDefault.ResponseCharacterDefaultInfo;
import com.dfo.dunsee.apiResponse.characterEquipment.ResponseCharacterEquipInfo;
import com.dfo.dunsee.apiResponse.characterStatus.ResponseCharacterStatusInfo;
import com.dfo.dunsee.common.ApiUtils;
import com.dfo.dunsee.common.UrlFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CharacterDetailSearchService {

  private final UrlFactory urlFactory;
  private final ApiUtils apiUtils;

  public CharacterDetailSearchService(UrlFactory urlFactory, ApiUtils apiUtils) {
    this.urlFactory = urlFactory;
    this.apiUtils = apiUtils;
  }

  //받아와야하는 정보
  //캐릭터 기본정보 -> 모험단명 등등
  //능력치 조회 ->
  //장착장비조회
  //-------------------상단부는 추출완료
  // 아바타조회
  //크리쳐, 아티팩트
  //탈리, 룬
  //비동기 병렬처리로 API호출하는걸로

  public void getCharacterDetails(String imgUrl, String serverId) {

    String characterId = getCharacterId(imgUrl);
    
    String searchCharacterDefaultUrl = urlFactory.setSearchCharacterDefaultUrl(serverId, characterId);
    String searchCharacterStatusUrl = urlFactory.setSearchCharacterStatusUrl(serverId, characterId);
    String searchCharacterEquipUrl = urlFactory.setSearchCharacterEquipUrl(serverId, characterId);
    CompletableFuture<ResponseCharacterDefaultInfo> characterDefaultInfoApi =
        CompletableFuture
            .supplyAsync(
                () ->
                    apiUtils.getApiResponseJson(
                        searchCharacterDefaultUrl,
                        ResponseCharacterDefaultInfo.class)
            );
    CompletableFuture<ResponseCharacterStatusInfo> characterStatusInfoApi =
        CompletableFuture
            .supplyAsync(
                () ->
                    apiUtils.getApiResponseJson(searchCharacterStatusUrl, ResponseCharacterStatusInfo.class)
            );
    CompletableFuture<ResponseCharacterEquipInfo> characterEquipInfoApi =
        CompletableFuture
            .supplyAsync(
                () ->
                    apiUtils.getApiResponseJson(searchCharacterEquipUrl, ResponseCharacterEquipInfo.class)
            );
    CompletableFuture<Void> allOf = CompletableFuture.allOf(characterDefaultInfoApi, characterStatusInfoApi,
                                                            characterEquipInfoApi);
    allOf.thenRun(() -> {
      try {
        ResponseCharacterDefaultInfo defaultInfo = characterDefaultInfoApi.get();
        ResponseCharacterStatusInfo statusInfo = characterStatusInfoApi.get();
        ResponseCharacterEquipInfo equipInfo = characterEquipInfoApi.get();
        log.info(defaultInfo.toString());
        log.info(statusInfo.toString());
        log.info(equipInfo.toString());
      } catch (Exception e) {
        log.warn("Error", e);
      }
    });

    allOf.join();
  }

  private String getCharacterId(String imgUrl) {
    try {

      return Arrays.stream(new URL(imgUrl).getPath()
                                          .split("/"))
                   .reduce((first, second) -> second)
                   .orElse("");
    } catch (MalformedURLException e) {
      log.error("올바르지않은 URL입니다.", e);
      return null;
    }
  }
}
