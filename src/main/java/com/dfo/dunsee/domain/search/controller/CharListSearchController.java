package com.dfo.dunsee.domain.search.controller;

import static com.dfo.dunsee.common.ServiceCode.CHR101;
import static com.dfo.dunsee.common.ServiceCode.setServiceMsg;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.domain.search.dto.CharacterSearchKeyword;
import com.dfo.dunsee.domain.search.dto.SimpleCharacterInfo;
import com.dfo.dunsee.domain.search.service.AdvListService;
import com.dfo.dunsee.domain.search.service.CharListService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CharListSearchController {

  private final CharListService charListService;
  private final AdvListService advListService;
  private static final ServiceCode SERVICE_CODE = CHR101;
  private static final String ADVENTURE = "adventure";

  @GetMapping("/search/list")
  public String searchCharacterDefaultInfo(@ModelAttribute CharacterSearchKeyword searchKeyword, Model model) {
    log.info(setServiceMsg(SERVICE_CODE) + "Character Search Process Start");

    List<SimpleCharacterInfo> characterInfoList;

    if (searchKeyword.getServerId().equals(ADVENTURE)) {
      characterInfoList = advListService.getAdvList(SERVICE_CODE, searchKeyword);
    } else {
      characterInfoList = charListService.getCharacterSearchResult(SERVICE_CODE, searchKeyword);
    }
    model.addAttribute("characterList", characterInfoList);
    return "search/list";
  }

}
