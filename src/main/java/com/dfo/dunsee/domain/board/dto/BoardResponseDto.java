package com.dfo.dunsee.domain.board.dto;

import com.dfo.dunsee.domain.board.entity.FreeBoard;
import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import java.time.LocalDateTime;

public record BoardResponseDto(Long id, String nickName, String title, String content,
                               Integer viewCount,
                               Integer likeCount, LocalDateTime createdDate) {

  public BoardResponseDto(FreeBoard freeBoard) {
    this(freeBoard.getId(),
        freeBoard.getMember()
                 .getNickName(),
        freeBoard.getTitle(),
        freeBoard.getContent(),
        freeBoard.getViewCount(),
        freeBoard.getLikeCount(),
        freeBoard.getCreatedDate());
  }

  public BoardResponseDto(QuestionBoard questionBoard) {
    this(questionBoard.getId(),
        questionBoard.getMember()
                     .getNickName(),
        questionBoard.getTitle(),
        questionBoard.getContent(),
        questionBoard.getViewCount(),
        questionBoard.getLikeCount(),
        questionBoard.getCreatedDate());
  }
}
