package com.dfo.dunsee.domain.board.dto;

import java.time.LocalDateTime;

public record BoardDto(Long id, String nickName, String title, String content, Integer viewCount,
                       Integer likeCount, LocalDateTime createdDate) {

}
