package com.dfo.dunsee.domain.board.controller;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.domain.board.dto.BoardRequestDto;
import com.dfo.dunsee.domain.board.dto.BoardResponseDto;
import com.dfo.dunsee.domain.board.service.FreeBoardService;
import com.dfo.dunsee.domain.member.entity.Member;
import com.dfo.dunsee.security.auth.oauth.PrincipalDetails;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board/free")
public class FreeBoardController {

  private final FreeBoardService freeBoardService;

  @GetMapping("/list")
  public ModelAndView board(ModelAndView modelAndView) {
    List<BoardResponseDto> freeBoards = freeBoardService.loadFreePosts();
    freeBoards.forEach(board -> log.info(board.toString()));
    modelAndView.addObject("freeBoards", freeBoards);
    modelAndView.setViewName("board/freeBoard");
    return modelAndView;
  }

  @GetMapping("/write")
  public ModelAndView write(ModelAndView modelAndView) {
    modelAndView.setViewName("board/post");
    return modelAndView;
  }

  @PostMapping("/save")
  public ResponseEntity<ResponseJson> save(@RequestBody BoardRequestDto boardRequestDto,
      @AuthenticationPrincipal PrincipalDetails principalDetails) {
    Member authMember = principalDetails.getMember();
    log.info(authMember.toString());
    log.info(boardRequestDto.title());
    log.info(boardRequestDto.content());

    ResultType result = freeBoardService.save(boardRequestDto, authMember);
    ResponseJson responseJson = switch (result) {
      case SUCCESS -> ResponseJson.setResponseJson(ServiceCode.BRD201, ResultType.SUCCESS,
          "작성성공!");
      case FAILURE -> ResponseJson.setResponseJson(ServiceCode.BRD201, ResultType.FAILURE,
          "작성실패..");
      default -> ResponseJson.setResponseJson(ServiceCode.BRD201, ResultType.FAILURE,
          "서버오류입니다..");
    };
    return ResponseEntity.ok()
                         .body(responseJson);
  }

  @GetMapping("/{id}")
  public ModelAndView viewPost(@PathVariable("id") Long id, ModelAndView modelAndView) {
    BoardResponseDto detailInfo = freeBoardService.findDetailInfo(id);
    modelAndView.addObject("detail", detailInfo);
    modelAndView.setViewName("board/view");
    return modelAndView;
  }
}
