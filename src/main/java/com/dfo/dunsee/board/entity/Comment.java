package com.dfo.dunsee.board.entity;

import com.dfo.dunsee.member.entity.BaseTimeEntity;
import com.dfo.dunsee.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
public class Comment extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 7000)
  private String content;

  @ColumnDefault("0")
  private Integer likeCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "free_board_id")
  private FreeBoard freeBoard;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "question_board_id")
  private QuestionBoard questionBoard;

  public void updateContent(String content) {
    //유효성 검사 구현 예정
    this.content = content;
  }

  public void updateLikeCount(Integer likeCount) {
    //유효성 검사 구현 예정
    this.likeCount = likeCount;
  }

  @Builder
  private Comment(String content, Integer likeCount, QuestionBoard questionBoard, FreeBoard freeBoard, Member member) {
    if (questionBoard == null && freeBoard == null) {
      throw new IllegalArgumentException();
    }
    this.content = content;
    this.likeCount = likeCount != null ? likeCount : 0;
    addMember(member);
    addFreeBoard(freeBoard);
    addQuestionBoard(questionBoard);
  }

  private void addQuestionBoard(QuestionBoard questionBoard) {
    this.questionBoard = questionBoard;
    if (questionBoard != null) {
      questionBoard.getComments().add(this);
    }
  }

  private void addFreeBoard(FreeBoard freeBoard) {
    this.freeBoard = freeBoard;
    if (freeBoard != null) {
      freeBoard.getComments().add(this);
    }
  }

  private void addMember(Member member) {
    if (member == null) {
      throw new NullPointerException();
    }
    this.member = member;
    member.getComments().add(this);
  }
}
