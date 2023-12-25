package com.dfo.dunsee.domain.board.service;

import static com.dfo.dunsee.common.ResultType.FAILURE;
import static com.dfo.dunsee.common.ResultType.SUCCESS;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.domain.board.dto.BoardRequestDto;
import com.dfo.dunsee.domain.board.dto.BoardResponseDto;
import com.dfo.dunsee.domain.board.entity.FreeBoard;
import com.dfo.dunsee.domain.board.repository.FreeBoardRepository;
import com.dfo.dunsee.domain.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FreeBoardService {


  private final FreeBoardRepository freeBoardRepository;

  public List<BoardResponseDto> loadFreePosts() {
    List<FreeBoard> boards = freeBoardRepository.findAll();
    return boards.stream()
                 .map(BoardResponseDto::new)
                 .toList();
  }

  public ResultType save(BoardRequestDto boardRequestDto, Member authMember) {
    FreeBoard savePost = FreeBoard.builder()
                                  .member(authMember)
                                  .title(boardRequestDto.title())
                                  .content(boardRequestDto.content())
                                  .build();
    FreeBoard saveResult = freeBoardRepository.save(savePost);
    return savePost.equals(saveResult) ? SUCCESS : FAILURE;
  }

  public BoardResponseDto findDetailInfo(Long id) {
    FreeBoard findBoard = freeBoardRepository.findById(id)
                                             .orElseThrow(RuntimeException::new);
    return new BoardResponseDto(findBoard);
  }
}
