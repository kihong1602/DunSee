package com.dfo.dunsee.domain.search.service;

import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.search.repository.CharacterInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharSaveService {

  private final CharacterInfoRepository characterInfoRepository;

  @Transactional
  public boolean saveCharacterInfo(CharacterInfo characterInfo) {

    try {

      if (!characterInfoRepository.existsByImgUrl(characterInfo.getImgUrl())) {

        characterInfoRepository.save(characterInfo);
      } else {
        log.info("이미 저장되어있는 캐릭터입니다. 생략하고 넘어갑니다.");
      }
      return true;
    } catch (Exception e) {
      log.error("캐릭터 정보 저장 중 오류가 발생했습니다. {}", e.getMessage());
      return false;
    }
  }
}
