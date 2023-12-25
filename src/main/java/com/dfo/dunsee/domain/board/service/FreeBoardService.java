package com.dfo.dunsee.domain.board.service;

import static com.dfo.dunsee.common.ResultType.FAILURE;
import static com.dfo.dunsee.common.ResultType.SUCCESS;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.domain.board.dto.BoardDto;
import com.dfo.dunsee.domain.board.dto.PostRequestDto;
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

  public List<BoardDto> loadFreePosts() {
    List<FreeBoard> boards = freeBoardRepository.findAll();
    return boards.stream()
                 .map(board ->
                     new BoardDto(
                         board.getId(),
                         board.getMember()
                              .getNickName(),
                         board.getTitle(),
                         board.getContent(),
                         board.getViewCount(),
                         board.getLikeCount(),
                         board.getCreatedDate()))
                 .toList();
  }

  public ResultType save(PostRequestDto postRequestDto, Member authMember) {
    FreeBoard savePost = FreeBoard.builder()
                                  .member(authMember)
                                  .title(postRequestDto.getTitle())
                                  .content(postRequestDto.getContent())
                                  .build();
    FreeBoard saveResult = freeBoardRepository.save(savePost);
    return savePost.equals(saveResult) ? SUCCESS : FAILURE;
  }

  public BoardDto findDetailInfo(Long id) {
    FreeBoard findBoard = freeBoardRepository.findById(id)
                                             .orElseThrow(RuntimeException::new);
    return new BoardDto(findBoard.getId(), findBoard.getMember()
                                                    .getNickName(), findBoard.getTitle(),
        findBoard.getContent(), findBoard.getViewCount(), findBoard.getLikeCount(),
        findBoard.getCreatedDate());
  }
}
