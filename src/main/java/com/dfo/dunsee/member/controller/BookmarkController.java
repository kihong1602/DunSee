package com.dfo.dunsee.member.controller;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.member.dto.ImgUrlDto;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.service.BookmarkService;
import com.dfo.dunsee.security.auth.oauth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookmarkController {

  private final ServiceCode serviceCode = ServiceCode.MBR401;
  private final BookmarkService bookmarkService;

  @PostMapping("/bookmark/add")
  public ResponseEntity<ResponseJson> bookmark(@RequestBody ImgUrlDto imgUrlDto,
      @AuthenticationPrincipal PrincipalDetails principalDetails) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "즐겨찾기 추가");

    Member member = getMember(principalDetails);

    ResultType resultType = bookmarkService.addBookmarkCharacter(serviceCode, imgUrlDto, member);

    ResponseJson responseJson = setResponseJson(serviceCode, resultType);
    return ResponseEntity.ok(responseJson);
  }

  private Member getMember(PrincipalDetails principalDetails) {
    return principalDetails == null ? null : principalDetails.getMember();
  }

  private ResponseJson setResponseJson(ServiceCode serviceCode, ResultType resultType) {
    return switch (resultType) {
      case SUCCESS -> ResponseJson.setResponseJson(serviceCode, resultType, "즐겨찾기에 추가되었습니다.");
      case FAILURE -> ResponseJson.setResponseJson(serviceCode, resultType, "즐겨찾기 추가에 실패하였습니다.");
      case EXIST -> ResponseJson.setResponseJson(serviceCode, resultType, "이미 즐겨찾기에 추가된 캐릭터입니다.");
    };
  }
}
