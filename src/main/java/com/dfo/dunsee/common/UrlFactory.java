package com.dfo.dunsee.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UrlFactory {

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Value("${dnf.apiKey}")
  private String apiKey;

  /**
   * https://api.neople.co.kr/df/servers/{serverId}/characters?characterName={characterName}&limit={limit}&wordType={wordType}&apikey={apikey}
   */
  public String setSearchCharacterListUrl(String characterName, String serverName) {
    StringBuilder sb = new StringBuilder();

    return sb.append(ApiPath.DEFAULT_URL.url())
             .append(ApiUri.SERVERS.value())
             .append("/")
             .append(serverName)
             .append("/")
             .append(ApiUri.CHARACTERS.value())
             .append("?")
             .append(ApiParam.CHARACTER_NAME.value())
             .append("=")
             .append(characterName)
             .append("&")
             .append(ApiParam.LIMIT.value())
             .append("=")
             .append(30)
             .append("&")
             .append(ApiParam.WORD_TYPE.value())
             .append("=")
             .append(WordType.MATCH.value())
             .append("&")
             .append(ApiParam.API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * https://img-api.neople.co.kr/df/servers/{serverId}/characters/{characterId}?zoom={zoom}
   */
  public String setSearchCharacterProfileImgUrl(String serverName, String characterId) {
    StringBuilder sb = new StringBuilder();

    return sb.append(ApiPath.IMAGE_URL.url())
             .append(ApiUri.SERVERS.value())
             .append("/")
             .append(serverName)
             .append("/")
             .append(ApiUri.CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("?")
             .append(ApiParam.ZOOM.value())
             .append("=")
             .append(2)
             .toString();
  }
}
