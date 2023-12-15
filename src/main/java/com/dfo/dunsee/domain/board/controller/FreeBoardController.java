package com.dfo.dunsee.domain.board.controller;

import com.dfo.dunsee.domain.board.entity.FreeBoard;
import com.dfo.dunsee.domain.board.service.FreeBoardService;
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
@RequestMapping("/board/free")
public class FreeBoardController {

  private final FreeBoardService freeBoardService;

  @GetMapping("/list")
  public String board(Model model) {
    List<FreeBoard> freeBoards = freeBoardService.loadFreePosts();
    model.addAttribute("freeBoards", freeBoards);
    return "board/freeBoard";
  }

  @GetMapping("/write")
  public String write() {
    return "board/post";
  }
}
