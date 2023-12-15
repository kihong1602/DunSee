package com.dfo.dunsee.domain.board.entity;

import com.dfo.dunsee.domain.member.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BaseBoardEntity extends BaseTimeEntity {

  private String title;

  @Column(length = 7000)
  private String content;

  @ColumnDefault("0")
  private Integer viewCount;

  @ColumnDefault("0")
  private Integer likeCount;

  protected BaseBoardEntity(String title, String content, Integer viewCount, Integer likeCount) {
    this.title = title;
    this.content = content;
    this.viewCount = viewCount;
    this.likeCount = likeCount;
  }
}
