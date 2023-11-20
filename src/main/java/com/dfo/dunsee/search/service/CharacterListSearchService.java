package com.dfo.dunsee.search.service;

import com.dfo.dunsee.apiResponse.characterList.ResponseCharacterInfo;
import com.dfo.dunsee.apiResponse.characterList.ResponseCharacterList;
import com.dfo.dunsee.common.ApiUtils;
import com.dfo.dunsee.common.Server;
import com.dfo.dunsee.common.UrlFactory;
import com.dfo.dunsee.search.dto.DefaultCharacterInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CharacterListSearchService {

  private final UrlFactory urlFactory;

  private final ApiUtils apiUtils;

  public CharacterListSearchService(UrlFactory urlFactory, ApiUtils apiUtils) {
    this.urlFactory = urlFactory;
    this.apiUtils = apiUtils;
  }

  public List<DefaultCharacterInfo> getCharacterSearchResult(String characterName, String serverId) {

    String searchCharacterUrl = urlFactory.setSearchCharacterListUrl(characterName, serverId);

    ResponseCharacterList responseCharacterList = apiUtils.getApiResponseJson(searchCharacterUrl,
                                                                              ResponseCharacterList.class);
    List<DefaultCharacterInfo> characterInfoList = getDefaultCharacterInfoList(responseCharacterList);

    return characterInfoList;
  }

  private List<DefaultCharacterInfo> getDefaultCharacterInfoList(ResponseCharacterList responseCharacterList) {
    List<DefaultCharacterInfo> defaultCharacterInfoList = new ArrayList<>();

    for (ResponseCharacterInfo info : responseCharacterList.getRows()) {
      if (info.getLevel() < 95) {
        continue;
      } else if (defaultCharacterInfoList.size() == 20) {
        break;
      }
      String imgUrl = urlFactory.setSearchCharacterProfileImgUrl(info.getServerId(), info.getCharacterId());
      DefaultCharacterInfo defaultCharacterInfo =
          DefaultCharacterInfo
              .builder()
              .serverId(Server.fromValue(info.getServerId()))
              .characterId(info.getCharacterId())
              .characterName(info.getCharacterName())
              .level(info.getLevel())
              .jobGrowName(info.getJobGrowName())
              .fame(info.getFame())
              .imgUrl(imgUrl)
              .build();
      defaultCharacterInfoList.add(defaultCharacterInfo);
    }

    return defaultCharacterInfoList.stream()
                                   .sorted()
                                   .toList();
  }


}
