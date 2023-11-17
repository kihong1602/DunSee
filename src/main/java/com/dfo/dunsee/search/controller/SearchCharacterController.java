package com.dfo.dunsee.search.controller;

import com.dfo.dunsee.search.dto.DefaultCharacterInfo;
import com.dfo.dunsee.search.service.SearchService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchCharacterController {

  private final SearchService searchService;

  public SearchCharacterController(SearchService searchService) {
    this.searchService = searchService;
  }

  @GetMapping("/search/character")
  public String searchDefaultCharacterInfo(@RequestParam String characterName,
      @RequestParam String serverName, Model model) {

    List<DefaultCharacterInfo> characterInfoList = searchService.getCharacterSearchResult(characterName,
                                                                                          serverName);
    model.addAttribute("characterList", characterInfoList);
    return "character-list";
  }
}
