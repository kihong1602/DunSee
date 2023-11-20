package com.dfo.dunsee.search.controller;

import com.dfo.dunsee.search.service.CharacterDetailSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CharacterDetailSearchController {

  private final CharacterDetailSearchService characterDetailSearchService;

  public CharacterDetailSearchController(CharacterDetailSearchService characterDetailSearchService) {
    this.characterDetailSearchService = characterDetailSearchService;
  }

  @GetMapping("/search/character")
  public String searchCharacterDetailInfo(@RequestParam String imgUrl, @RequestParam String serverId) {
    //받은 파라미터 그대로 service로 넘김
    characterDetailSearchService.getCharacterDetails(imgUrl, serverId);
//    characterDetailSearchService.getCharacterDetailsSync(imgUrl, serverId);
    return "character-list";
  }
}
