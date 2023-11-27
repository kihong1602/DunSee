package com.dfo.dunsee.search.controller;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.search.service.CharDetailSearchAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CharDetailSearchController {

  private final CharDetailSearchAdapter charDetailSearchAdapter;
  private static final ServiceCode SERVICE_CODE = ServiceCode.CHR201;
  private static final String SYNC = "sync";
  private static final String ASYNC = "async";

  @GetMapping("/search/character")
  public String searchCharacterDetailInfo(@RequestParam String imgUrl) throws InterruptedException {
    log.info(ServiceCode.setServiceMsg(SERVICE_CODE) + "Character Detail Search Process Start");

    charDetailSearchAdapter.searchAdapter(SERVICE_CODE, imgUrl, ASYNC);

    return "character-list";
  }
}
