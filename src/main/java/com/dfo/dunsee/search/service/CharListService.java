package com.dfo.dunsee.search.service;

import com.dfo.dunsee.common.Server;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.config.ApiUtilsConfig;
import com.dfo.dunsee.response.charlist.ResponseCharacterInfo;
import com.dfo.dunsee.response.charlist.ResponseCharacterList;
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

    ResponseCharacterList responseCharacterList = apiUtilsConfig.getApiUtils()
                                                                .getApiResponseJson(serviceCode, searchCharacterUrl,
                                                                                    ResponseCharacterList.class);

    return getDefaultCharacterInfoList(serviceCode, responseCharacterList);
  }

  private List<SimpleCharacterInfo> getDefaultCharacterInfoList(ServiceCode serviceCode,
      ResponseCharacterList responseCharacterList) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character List DataProcessing");
    List<SimpleCharacterInfo> simpleCharacterInfoList = new ArrayList<>();

    for (ResponseCharacterInfo info : responseCharacterList.getRows()) {
      if (simpleCharacterInfoList.size() == 20) {
        break;
      }
      String imgUrl = apiUtilsConfig.getUrlFactory()
                                    .setSearchCharacterProfileImgUrl(serviceCode, info.getServerId(),
                                                                     info.getCharacterId());
      SimpleCharacterInfo simpleCharacterInfo =
          SimpleCharacterInfo
              .builder()
              .serverId(Server.fromValue(info.getServerId()))
              .characterId(info.getCharacterId())
              .characterName(info.getCharacterName())
              .level(info.getLevel())
              .jobGrowName(info.getJobGrowName())
              .fame(info.getFame())
              .imgUrl(imgUrl)
              .build();
      simpleCharacterInfoList.add(simpleCharacterInfo);
    }

    return simpleCharacterInfoList.stream()
                                  .sorted()
                                  .toList();
  }


}
