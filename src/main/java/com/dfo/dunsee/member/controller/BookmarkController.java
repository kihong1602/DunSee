package com.dfo.dunsee.member.controller;

import static com.dfo.dunsee.common.ServiceCode.MBR401;
import static com.dfo.dunsee.common.ServiceCode.MBR402;
import static com.dfo.dunsee.common.ServiceCode.MBR403;
import static com.dfo.dunsee.common.ServiceCode.setServiceMsg;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.member.dto.ImgUrlDto;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.service.BookmarkService;
import com.dfo.dunsee.search.dto.SimpleCharacterInfo;
import com.dfo.dunsee.security.auth.oauth.PrincipalDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @PostMapping("/add")
  public ResponseEntity<ResponseJson> bookmark(@RequestBody ImgUrlDto imgUrlDto,
      @AuthenticationPrincipal PrincipalDetails principalDetails) {
    log.info(setServiceMsg(MBR401) + "즐겨찾기 추가");

    Member member = getMember(principalDetails);

    ResultType resultType = bookmarkService.addBookmarkCharacter(MBR401, imgUrlDto, member);

    ResponseJson responseJson = setResponseJson(MBR401, resultType, "즐겨찾기에 추가되었습니다.");
    return ResponseEntity.ok(responseJson);
  }

  @GetMapping("/view")
  public ModelAndView bookmarkView(@AuthenticationPrincipal PrincipalDetails principalDetails,
      ModelAndView modelAndView) {
    log.info(setServiceMsg(MBR402) + "즐겨찾기 캐릭터 조회");
    Member member = getMember(principalDetails);

    List<SimpleCharacterInfo> result = bookmarkService.searchBookmarks(MBR402, member);
    modelAndView.addObject("characterList", result);
    modelAndView.setViewName("search/bookmark");

    return modelAndView;
  }

  @PostMapping("/remove")
  public ResponseEntity<ResponseJson> remove(@RequestBody ImgUrlDto imgUrlDto,
      @AuthenticationPrincipal PrincipalDetails principalDetails) {
    log.info(setServiceMsg(MBR403) + "즐겨찾기 캐릭터 삭제");

    Member member = getMember(principalDetails);
    ResultType resultType = bookmarkService.removeBookmark(MBR403, imgUrlDto, member);

    ResponseJson responseJson = setResponseJson(MBR403, resultType, "즐겨찾기 삭제에 성공하였습니다.");
    return ResponseEntity.ok(responseJson);
  }

  private Member getMember(PrincipalDetails principalDetails) {
    return principalDetails == null ? null : principalDetails.getMember();
  }

  private ResponseJson setResponseJson(ServiceCode serviceCode, ResultType resultType, String successMsg) {
    return switch (resultType) {
      case SUCCESS -> ResponseJson.setResponseJson(serviceCode, resultType, successMsg);
      case FAILURE -> ResponseJson.setResponseJson(serviceCode, resultType, "작업이 실패하였습니다.");
      case EXIST -> ResponseJson.setResponseJson(serviceCode, resultType, "이미 즐겨찾기에 추가된 캐릭터입니다.");
    };
  }
}
