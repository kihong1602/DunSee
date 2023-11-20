package com.dfo.dunsee.common;

import static com.dfo.dunsee.common.url.ApiParam.API_KEY;
import static com.dfo.dunsee.common.url.ApiParam.LIMIT;
import static com.dfo.dunsee.common.url.ApiParam.WORD_TYPE;
import static com.dfo.dunsee.common.url.ApiParam.ZOOM;
import static com.dfo.dunsee.common.url.ApiPath.DEFAULT_URL;
import static com.dfo.dunsee.common.url.ApiPath.IMAGE_URL;
import static com.dfo.dunsee.common.url.ApiUri.CHARACTERS;
import static com.dfo.dunsee.common.url.ApiUri.CHARACTER_NAME;
import static com.dfo.dunsee.common.url.ApiUri.EQUIP;
import static com.dfo.dunsee.common.url.ApiUri.EQUIPMENT;
import static com.dfo.dunsee.common.url.ApiUri.SERVERS;
import static com.dfo.dunsee.common.url.ApiUri.STATUS;

import com.dfo.dunsee.common.url.WordType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlFactory {

  @Value("${dnf.apiKey}")
  private String apiKey;

  /**
   * 캐릭터 리스트 조회
   * <p>
   * https://api.neople.co.kr/df/servers/{serverId}/characters?characterName={characterName}&limit={limit}&wordType={wordType}&apikey={apikey}
   */
  public String setSearchCharacterListUrl(String characterName, String serverId) {
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(SERVERS.value())
             .append("/")
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("?")
             .append(CHARACTER_NAME.value())
             .append("=")
             .append(characterName)
             .append("&")
             .append(LIMIT.value())
             .append("=")
             .append(30)
             .append("&")
             .append(WORD_TYPE.value())
             .append("=")
             .append(WordType.MATCH.value())
             .append("&")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * 캐릭터 이미지 조회
   * <p>
   * https://img-api.neople.co.kr/df/servers/{serverId}/characters/{characterId}?zoom={zoom}
   */
  public String setSearchCharacterProfileImgUrl(String serverId, String characterId) {
    StringBuilder sb = new StringBuilder();

    return sb.append(IMAGE_URL.url())
             .append(SERVERS.value())
             .append("/")
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("?")
             .append(ZOOM.value())
             .append("=")
             .append(2)
             .toString();
  }

  /**
   * 캐릭터 기본정보 조회
   * <p>
   * https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}?apikey={apikey}
   */
  public String setSearchCharacterDefaultUrl(String serverId, String characterId) {
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(SERVERS.value())
             .append("/")
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("?")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * 캐릭터 스탯 조회
   * <p>
   * https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}/status?apikey={apikey}
   */
  public String setSearchCharacterStatusUrl(String serverId, String characterId) {
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(SERVERS.value())
             .append("/")
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("/")
             .append(STATUS.value())
             .append("?")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * 캐릭터 장착장비 조회
   * <p>
   * https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}/equip/equipment?apikey={apikey}
   */
  public String setSearchCharacterEquipUrl(String serverId, String characterId) {
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(SERVERS.value())
             .append("/")
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("/")
             .append(EQUIP.value())
             .append("/")
             .append(EQUIPMENT.value())
             .append("?")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }
}
