package com.dfo.dunsee.search.controller;

import com.dfo.dunsee.search.dto.DefaultCharacterInfo;
import com.dfo.dunsee.search.service.CharacterListSearchService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CharacterListSearchController {

  private final CharacterListSearchService characterListSearchService;

  public CharacterListSearchController(CharacterListSearchService characterListSearchService) {
    this.characterListSearchService = characterListSearchService;
  }

  @GetMapping("/search/list")
  public String searchCharacterDefaultInfo(@RequestParam String characterName,
      @RequestParam String serverId, Model model) {

    List<DefaultCharacterInfo> characterInfoList = characterListSearchService.getCharacterSearchResult(characterName,
                                                                                                       serverId);
    model.addAttribute("characterList", characterInfoList);
    return "character-list";
  }

}
