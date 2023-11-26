package com.dfo.dunsee.search.service;

import com.dfo.dunsee.common.ServiceCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CharDetailSearchAdapter {
  private final SyncCharDetailService syncCharDetailService;
  private final AsyncCharDetailService asyncCharDetailService;

  public void searchAdapter(ServiceCode serviceCode,String imgUrl,String keyword) {
    switch(keyword){
      case"async"-> asyncCharDetailService.aSyncSearch(serviceCode,imgUrl);
      case "sync" -> syncCharDetailService.syncSearch(serviceCode,imgUrl);
      default -> throw new IllegalArgumentException(ServiceCode.setServiceMsg(serviceCode)+"잘못된 입력입니다.");
    }
  }
}
