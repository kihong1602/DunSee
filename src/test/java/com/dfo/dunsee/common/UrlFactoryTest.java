package com.dfo.dunsee.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UrlFactoryTest {

  private UrlFactory urlFactory;
  private ServiceCode serviceCode;
  private String serverId;
  private String characterId;

  @BeforeEach
  void init() {
    urlFactory = new UrlFactory();
    serviceCode = ServiceCode.CHR201;
    serverId = "hilder";
    characterId = "e5ef51c6e6b6f9b34e87b9ab242415de";
  }

  @Test
  @DisplayName("캐릭터 리스트 조회URL 생성 테스트")
  void charListUrlTest() {
    //given
    String characterName = "윤보미";
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters?characterName=" + characterName
        + "&limit=30&wordType=match&apikey=null";

    //when
    String resultUrl = urlFactory.setSearchCharacterListUrl(serviceCode, serverId, characterName);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("캐릭터 이미지URL 생성테스트")
  void charImgUrlTest() {
    //given
    String expectUrl = "https://img-api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de?zoom=2";

    //when
    String resultUrl = urlFactory.setSearchCharacterProfileImgUrl(serviceCode, serverId, characterId);
    //then

    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("기본정보 조회 URL 생성테스트")
  void defaultInfoUrlTest() {
    //given
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de?apikey=null";

    //when
    String resultUrl = urlFactory.setSearchCharacterDefaultUrl(serviceCode, serverId, characterId);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("스탯조회 URL 생성테스트")
  void statusInfoUrlTest() {
    //given
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de/status?apikey=null";

    //when
    String resultUrl = urlFactory.setSearchCharacterStatusUrl(serviceCode, serverId, characterId);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("장착장비 조회 URL 생성테스트")
  void equipInfoUrlTest() {
    //given
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de/equip/equipment?apikey=null";

    //when
    String resultUrl = urlFactory.setSearchCharacterEquipUrl(serviceCode, serverId, characterId);

    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("아바타URL 생성테스트")
  void avatarUrlTest() {
    //given
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de/equip/avatar?apikey=null";
    //when
    String resultUrl = urlFactory.setSearchCharacterAvatarUrl(serviceCode, serverId, characterId);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("크리쳐URL 생성테스트")
  void creatureUrlTest() {
    //given
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de/equip/creature?apikey=null";
    //when
    String resultUrl = urlFactory.setSearchCreatureUrl(serviceCode, serverId, characterId);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);

  }

  @Test
  @DisplayName("탈리스만URL 생성테스트")
  void talismanUrlTest() {
    //given
    String expectUrl = "https://api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de/equip/talisman?apikey=null";
    //when
    String resultUrl = urlFactory.setSearchTalismanUrl(serviceCode, serverId, characterId);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }

  @Test
  @DisplayName("아이템이미지 URL 생성테스트")
  void itemImgUrlTest() {
    //given
    String itemId = "testId";
    String expectUrl = "https://img-api.neople.co.kr/df/items/" + itemId;

    //when
    String resultUrl = urlFactory.setItemImgUrl(itemId);
    //then
    assertThat(resultUrl).isEqualTo(expectUrl);
  }
}
