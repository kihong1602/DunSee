package com.dfo.dunsee.domain.board.service;

import com.dfo.dunsee.domain.board.entity.FreeBoard;
import com.dfo.dunsee.domain.board.repository.FreeBoardRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FreeBoardService {


  private final FreeBoardRepository freeBoardRepository;

  public List<FreeBoard> loadFreePosts() {
    return freeBoardRepository.findAll();
  }
}
