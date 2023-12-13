package com.dfo.dunsee.board.entity;

import com.dfo.dunsee.member.entity.BaseEntity;
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
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@DynamicInsert
public class Post extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Column(length = 7000)
  private String content;

  @ColumnDefault("0")
  private Integer viewCount;

  @ColumnDefault("0")
  private Integer likeCount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Builder
  private Post(String title, String content, int viewCount, int likeCount, Member member) {
    this.title = title;
    this.content = content;
    this.viewCount = viewCount;
    this.likeCount = likeCount;
    this.member = member;
  }
}
