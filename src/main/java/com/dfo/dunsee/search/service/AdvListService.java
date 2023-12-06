package com.dfo.dunsee.search.service;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.response.charstatus.ResCharStatusInfo;
import com.dfo.dunsee.search.dto.CharacterSearchKeyword;
import com.dfo.dunsee.search.dto.ImgUrlParserCharacterInfo;
import com.dfo.dunsee.search.dto.SimpleCharacterInfo;
import com.dfo.dunsee.search.repository.CharacterInfoRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvListService {

  private final ApiUtilsConfig apiUtilsConfig;
  private final CallApiService callApiService;
  private final CharacterInfoRepository characterInfoRepository;

  public List<SimpleCharacterInfo> getAdvList(ServiceCode serviceCode, CharacterSearchKeyword keyword) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Adventure List Service Start");
    String adventureName = keyword.getCharacterName();
    List<CharacterInfo> savedCharacterInfoList = characterInfoRepository.findByAdventureName(adventureName);
    if (savedCharacterInfoList.isEmpty()) {
      return Collections.emptyList();
    }

    List<String> urlList = new ArrayList<>();
    for (CharacterInfo info : savedCharacterInfoList) {
      ImgUrlParserCharacterInfo parseInfo = getParserCharacterInfo(serviceCode, info);
      String searchUrl = apiUtilsConfig.getUrlFactory()
                                       .setSearchCharacterStatusUrl(serviceCode, parseInfo.getServerId(),
                                                                    parseInfo.getCharacterId());
      urlList.add(searchUrl);

    }
    Map<String, ResCharStatusInfo> statusInfoMap = callApi(serviceCode, urlList);

    return createSimpleCharInfoList(serviceCode, savedCharacterInfoList, statusInfoMap);
  }


  private Map<String, ResCharStatusInfo> callApi(ServiceCode serviceCode, List<String> urlList) {
    Map<String, ResCharStatusInfo> statusInfoMap = new HashMap<>();

    List<CompletableFuture<ResCharStatusInfo>> futures =
        urlList.stream()
               .map(url -> callApiService.callNeopleApi(serviceCode, url, ResCharStatusInfo.class)
                                         .thenApply(ResCharStatusInfo.class::cast)).toList();

    futures.stream()
           .map(CompletableFuture::join)
           .forEach(statusInfo -> statusInfoMap.put(statusInfo.getCharacterId(), statusInfo));

    return statusInfoMap;
  }

  private List<SimpleCharacterInfo> createSimpleCharInfoList(ServiceCode serviceCode,
      List<CharacterInfo> savedCharacterInfoList,
      Map<String, ResCharStatusInfo> statusInfoMap) {
    List<SimpleCharacterInfo> simpleCharInfoList = new ArrayList<>();

    for (CharacterInfo characterInfo : savedCharacterInfoList) {
      ImgUrlParserCharacterInfo parseInfo = getParserCharacterInfo(serviceCode, characterInfo);
      ResCharStatusInfo statusInfo = statusInfoMap.get(parseInfo.getCharacterId());

      SimpleCharacterInfo simpleCharInfo = SimpleCharacterInfo
          .createSimpleCharacterInfo(parseInfo, statusInfo, characterInfo);

      simpleCharInfoList.add(simpleCharInfo);
    }
    return simpleCharInfoList;
  }

  private ImgUrlParserCharacterInfo getParserCharacterInfo(ServiceCode serviceCode, CharacterInfo info) {
    return apiUtilsConfig.getUrlParser()
                         .imgUrlParseCharacterInfo(serviceCode, info.getImgUrl());
  }
}
