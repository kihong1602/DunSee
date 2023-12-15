package com.dfo.dunsee.domain.board.controller;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.common.response.ResponseJson;
import com.dfo.dunsee.domain.board.dto.PostRequestDto;
import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import com.dfo.dunsee.domain.board.service.QuestionBoardService;
import com.dfo.dunsee.domain.member.entity.Member;
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
@RequiredArgsConstructor
@RequestMapping("/board/question")
public class QuestionBoardController {

  private final QuestionBoardService questionBoardService;

  @GetMapping("/list")
  public ModelAndView board(ModelAndView modelAndView) {
    List<QuestionBoard> questionBoards = questionBoardService.loadQuestionPosts();
    modelAndView.addObject("questionBoards", questionBoards);
    modelAndView.setViewName("board/questionBoard");
    return modelAndView;
  }

  @GetMapping("/write")
  public ModelAndView write(ModelAndView modelAndView) {
    modelAndView.setViewName("board/post");
    return modelAndView;
  }

  @PostMapping("/save")
  public ResponseEntity<ResponseJson> save(@RequestBody PostRequestDto postRequestDto,
      @AuthenticationPrincipal PrincipalDetails principalDetails) {
    Member authMember = principalDetails.getMember();
    log.info(authMember.toString());
    log.info(postRequestDto.getTitle());
    log.info(postRequestDto.getContent());
    ResponseJson responseJson = ResponseJson.setResponseJson(ServiceCode.BRD201, ResultType.SUCCESS, "작성성공!");
    return ResponseEntity.ok().body(responseJson);
  }
}
