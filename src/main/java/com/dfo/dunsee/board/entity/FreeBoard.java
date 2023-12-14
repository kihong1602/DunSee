package com.dfo.dunsee.board.entity;

import com.dfo.dunsee.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "free_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FreeBoard extends BaseBoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "freeBoard", fetch = FetchType.LAZY)
  private List<Comment> comments = new ArrayList<>();

  @Builder
  private FreeBoard(String title, String content, Integer viewCount, Integer likeCount, Member member) {
    super(title, content, viewCount, likeCount);
    this.member = member;
  }

  public void updateTitle(String title) {
    //유효성 검사 구현 예정
    this.setTitle(title);
  }

  public void updateContent(String content) {
    //유효성 검사 구현 예정
    this.setContent(content);
  }

  public void updateViewCount(Integer viewCount) {
    //유효성 검사 구현 예정
    this.setViewCount(viewCount);
  }

  public void updateLikeCount(Integer likeCount) {
    //유효성 검사 구현 예정
    this.setLikeCount(likeCount);
  }
}
