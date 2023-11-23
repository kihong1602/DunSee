package com.dfo.dunsee.search.controller;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.search.service.AsyncCharDetailService;
import com.dfo.dunsee.search.service.SyncCharDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CharDetailSearchController {

  private final SyncCharDetailService syncCharDetailService;
  private final AsyncCharDetailService asyncCharDetailService;
  private final ServiceCode SERVICE_CODE = ServiceCode.CHR201;

  @GetMapping("/search/character")
  public String searchCharacterDetailInfo(@RequestParam String imgUrl) {
    log.info(ServiceCode.setServiceMsg(SERVICE_CODE) + "Character Detail Search Process Start");
    //비동기 처리
    asyncCharDetailService.aSyncSearch(SERVICE_CODE, imgUrl);

    //동기처리
    //syncDetailsSearch.syncSearch(serviceCode, imgUrl);

    return "character-list";
  }
}
