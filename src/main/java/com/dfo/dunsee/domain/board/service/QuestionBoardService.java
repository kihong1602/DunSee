package com.dfo.dunsee.domain.board.service;

import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import com.dfo.dunsee.domain.board.repository.QuestionBoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionBoardService {

  private final QuestionBoardRepository questionBoardRepository;

  public List<QuestionBoard> loadQuestionPosts() {
    return questionBoardRepository.findAll();
  }

}
