package com.dfo.dunsee.search.service;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
import com.dfo.dunsee.response.charlist.ResCharInfo;
import com.dfo.dunsee.response.charlist.ResCharList;
import com.dfo.dunsee.search.dto.CharacterSearchKeyword;
import com.dfo.dunsee.search.dto.SimpleCharacterInfo;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharListService {

  private final ApiUtilsConfig apiUtilsConfig;

  public List<SimpleCharacterInfo> getCharacterSearchResult(ServiceCode serviceCode, CharacterSearchKeyword keyword) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character Search Service Start");

    String characterName = keyword.getCharacterName();
    String serverId = keyword.getServerId();
    String searchCharacterUrl = apiUtilsConfig.getUrlFactory()
                                              .setSearchCharacterListUrl(serviceCode, characterName, serverId);

    ResCharList resCharList = apiUtilsConfig.getApiUtils()
                                            .getApiResponseJson(serviceCode, searchCharacterUrl,
                                                                ResCharList.class);

    return getDefaultCharacterInfoList(serviceCode, resCharList);
  }

  private List<SimpleCharacterInfo> getDefaultCharacterInfoList(ServiceCode serviceCode,
      ResCharList resCharList) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character List DataProcessing");
    List<SimpleCharacterInfo> simpleCharacterInfoList = new ArrayList<>();

    for (ResCharInfo info : resCharList.getRows()) {
      if (info.getFame() == null) {
        continue;
      }

      String serverId = info.getServerId();
      String characterId = info.getCharacterId();

      String imgUrl = apiUtilsConfig.getUrlFactory()
                                    .setSearchCharacterProfileImgUrl(serviceCode, serverId, characterId);
      SimpleCharacterInfo simpleCharacterInfo = SimpleCharacterInfo.createSimpleCharacterInfo(imgUrl, info);

      simpleCharacterInfoList.add(simpleCharacterInfo);
    }

    return simpleCharacterInfoList.stream().sorted().toList();
  }


}
