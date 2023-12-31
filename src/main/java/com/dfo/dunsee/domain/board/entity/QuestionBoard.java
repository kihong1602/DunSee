package com.dfo.dunsee.domain.board.entity;

import com.dfo.dunsee.domain.member.entity.Member;
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
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@DynamicInsert
@Table(name = "question_board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionBoard extends BaseBoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "questionBoard")
  private List<Comment> comments = new ArrayList<>();

  @Builder
  private QuestionBoard(String title, String content, Integer viewCount, Integer likeCount, Member member) {
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
