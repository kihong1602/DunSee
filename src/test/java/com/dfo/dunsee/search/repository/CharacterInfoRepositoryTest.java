package com.dfo.dunsee.search.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.search.repository.CharacterInfoRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@SpringBootTest
class CharacterInfoRepositoryTest {

  @Autowired
  private CharacterInfoRepository characterInfoRepository;


  @BeforeEach
  void before() {
    for (int i = 1; i <= 100; i++) {
      CharacterInfo savedInfo = CharacterInfo.builder().imgUrl("testUrl" + i).adventureName("testAdventure").build();
      characterInfoRepository.save(savedInfo);
    }
  }

  @Test
  @DisplayName("모험단이름으로 캐릭터목록을 조회한다.")
  void findByAdventureName() {
    //given
    String adventureName = "testAdventure";

    //when
    List<CharacterInfo> infoList = characterInfoRepository.findByAdventureName(adventureName);

    //then
    assertThat(infoList).hasSize(100);
  }

  @Test
  @DisplayName("ImageUrl를 통해 캐릭터를 존재하는지 확인하고 존재하면 true를 반환한다.")
  void existsByImgUrlTrue() {
    //given
    String inputImgUrl = "testUrl1";
    //when
    boolean result = characterInfoRepository.existsByImgUrl(inputImgUrl);
    //then
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("ImageUrl을 통해 캐릭터가 존재하는지 확인하고 없다면 false를 반환한다.")
  void existsByImgUrlFalse() {
    //given
    String inputImgUrl = "testUrl0";
    //when
    boolean result = characterInfoRepository.existsByImgUrl(inputImgUrl);
    //then
    assertThat(result).isFalse();
  }

  @Test
  @DisplayName("ImageUrl을 통해 캐릭터를 조회한다.")
  void findByImgUrl() {
    //given
    String inputImgUrl = "testUrl1";
    //when
    CharacterInfo findInfo = characterInfoRepository.findByImgUrl(inputImgUrl);
    //then
    assertThat(findInfo).extracting("imgUrl", "adventureName")
                        .contains(inputImgUrl, "testAdventure");
  }

}