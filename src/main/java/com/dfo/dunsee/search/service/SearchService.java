package com.dfo.dunsee.search.service;

import com.dfo.dunsee.common.Server;
import com.dfo.dunsee.common.UrlFactory;
import com.dfo.dunsee.search.dto.CharacterList;
import com.dfo.dunsee.search.dto.DefaultCharacterInfo;
import com.dfo.dunsee.search.dto.ResponseCharacterInfo;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SearchService {

  private final UrlFactory urlFactory;
  private final RestTemplate restTemplate;

  public SearchService(UrlFactory urlFactory, RestTemplate restTemplate) {
    this.urlFactory = urlFactory;
    this.restTemplate = restTemplate;
  }

  public List<DefaultCharacterInfo> getCharacterSearchResult(String characterName, String serverName) {

    String searchDefaultCharacterUrl = urlFactory.setSearchCharacterListUrl(characterName, serverName);

    CharacterList characterList = getApiResponseJson(searchDefaultCharacterUrl, CharacterList.class);

    List<DefaultCharacterInfo> characterInfoList = getDefaultCharacterInfoList(characterList);

    return characterInfoList;
  }

  private List<DefaultCharacterInfo> getDefaultCharacterInfoList(CharacterList characterList) {
    List<DefaultCharacterInfo> defaultCharacterInfoList = new ArrayList<>();

    for (ResponseCharacterInfo info : characterList.getRows()) {
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
              .characterName(info.getCharacterName())
              .level(info.getLevel())
              .jobGrowName(info.getJobGrowName())
              .fame(info.getFame())
              .imgUrl(imgUrl)
              .build();
      defaultCharacterInfoList.add(defaultCharacterInfo);
    }

    return defaultCharacterInfoList.stream().sorted().toList();
  }

  private <T> T getApiResponseJson(String searchUrl, Class<T> className) {
    return restTemplate.getForObject(searchUrl, className);
  }
}
