package com.dfo.dunsee.domain.board.controller;

import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import com.dfo.dunsee.domain.board.service.QuestionBoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board/question")
public class QuestionBoardController {

  private final QuestionBoardService questionBoardService;

  @GetMapping("/list")
  public String board(Model model) {
    List<QuestionBoard> questionBoards = questionBoardService.loadQuestionPosts();
    model.addAttribute("questionBoards", questionBoards);
    return "board/questionBoard";
  }

  @GetMapping("/write")
  public String write() {
    return "board/post";
  }
}
