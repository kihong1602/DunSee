package com.dfo.dunsee.search.service;

import com.dfo.dunsee.search.repository.CharacterInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharSaveService {

  private final CharacterInfoRepository characterInfoRepository;

  public void saveCharacterInfo() {

  }
}
