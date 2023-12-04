package com.dfo.dunsee.search.controller;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.search.dto.detail.DetailCharInfoDto;
import com.dfo.dunsee.search.service.CharDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CharDetailSearchController {

  private final CharDetailService charDetailService;
  private static final ServiceCode SERVICE_CODE = ServiceCode.CHR201;

  @GetMapping("/search/character")
  public String searchCharacterDetailInfo(@RequestParam String imgUrl, Model model) {
    log.info(ServiceCode.setServiceMsg(SERVICE_CODE) + "Character Detail Search Process Start");

    DetailCharInfoDto detailCharInfoDto = charDetailService.charDetailSearch(SERVICE_CODE, imgUrl);
    model.addAttribute("characterInfo", detailCharInfoDto);
    return "character-list";
  }
}
