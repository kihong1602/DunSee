package com.dfo.dunsee.domain.search.service;

import com.dfo.dunsee.common.CharUtils;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.search.dto.CharacterSearchKeyword;
import com.dfo.dunsee.domain.search.dto.SimpleCharacterInfo;
import com.dfo.dunsee.domain.search.repository.CharacterInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdvListService {

  private final CharUtils charUtils;
  private final CharacterInfoRepository characterInfoRepository;

  public List<SimpleCharacterInfo> getAdvList(ServiceCode serviceCode, CharacterSearchKeyword keyword) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "Adventure List Service Start");
    String adventureName = keyword.getCharacterName();
    List<CharacterInfo> savedCharacterInfoList = characterInfoRepository.findByAdventureName(adventureName);

    return charUtils.getSimpleCharInfoList(serviceCode, savedCharacterInfoList);
  }

}
