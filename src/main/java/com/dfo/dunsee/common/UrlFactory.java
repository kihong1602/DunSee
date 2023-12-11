package com.dfo.dunsee.common;

import static com.dfo.dunsee.common.url.ApiParam.API_KEY;
import static com.dfo.dunsee.common.url.ApiParam.LIMIT;
import static com.dfo.dunsee.common.url.ApiParam.WORD_TYPE;
import static com.dfo.dunsee.common.url.ApiParam.ZOOM;
import static com.dfo.dunsee.common.url.ApiPath.CHAR_IMAGE_URL;
import static com.dfo.dunsee.common.url.ApiPath.DEFAULT_URL;
import static com.dfo.dunsee.common.url.ApiPath.ITEM_IMAGE_URL;
import static com.dfo.dunsee.common.url.ApiUri.AVATAR;
import static com.dfo.dunsee.common.url.ApiUri.CHARACTERS;
import static com.dfo.dunsee.common.url.ApiUri.CHARACTER_NAME;
import static com.dfo.dunsee.common.url.ApiUri.CREATURE;
import static com.dfo.dunsee.common.url.ApiUri.EQUIP;
import static com.dfo.dunsee.common.url.ApiUri.EQUIPMENT;
import static com.dfo.dunsee.common.url.ApiUri.STATUS;
import static com.dfo.dunsee.common.url.ApiUri.TALISMAN;

import com.dfo.dunsee.common.url.WordType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UrlFactory {

  @Value("${dnf.apiKey}")
  private String apiKey;

  /**
   * 캐릭터 리스트 조회
   * <p>
   * https://api.neople.co.kr/df/servers/{serverId}/characters?characterName={characterName}&limit={limit}&wordType={wordType}&apikey={apikey}
   */
  public String setSearchCharacterListUrl(ServiceCode serviceCode, String serverId, String characterName) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character List URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
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
  public String setSearchCharacterProfileImgUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character Image URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(CHAR_IMAGE_URL.url())
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
  public String setSearchCharacterDefaultUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character Default Info URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
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
  public String setSearchCharacterStatusUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character Status URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
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
  public String setSearchCharacterEquipUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Character Equipment URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
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

  /**
   * 캐릭터 아바타 조회<p> https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}/equip/avatar?apikey={apikey}
   */
  public String setSearchCharacterAvatarUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + " Character Avatar URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("/")
             .append(EQUIP.value())
             .append("/")
             .append(AVATAR.value())
             .append("?")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * 크리쳐 조회<p> https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}/equip/creature?apikey={apikey}
   */
  public String setSearchCreatureUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + " Creature URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("/")
             .append(EQUIP.value())
             .append("/")
             .append(CREATURE.value())
             .append("?")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * 캐릭터 탈리스만 조회
   * <p>
   * https://api.neople.co.kr/df/servers/{serverId}/characters/{characterId}/equip/talisman?apikey={apikey}
   */
  public String setSearchTalismanUrl(ServiceCode serviceCode, String serverId, String characterId) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Talisman URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(DEFAULT_URL.url())
             .append(serverId)
             .append("/")
             .append(CHARACTERS.value())
             .append("/")
             .append(characterId)
             .append("/")
             .append(EQUIP.value())
             .append("/")
             .append(TALISMAN.value())
             .append("?")
             .append(API_KEY.value())
             .append("=")
             .append(apiKey)
             .toString();
  }

  /**
   * 아이템 이미지 조회
   * <p>
   * https://img-api.neople.co.kr/df/items/{itemId}
   */
  public String setItemImgUrl(String itemId) {
    log.info("Item Image URL Create");
    StringBuilder sb = new StringBuilder();

    return sb.append(ITEM_IMAGE_URL.url())
             .append(itemId).toString();
  }
}
