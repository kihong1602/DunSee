package com.dfo.dunsee.domain.board.service;

import com.dfo.dunsee.domain.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {


  private final CommentRepository commentRepository;

}
