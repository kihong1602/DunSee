package com.dfo.dunsee.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "bookmark", uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "character_id"})})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "character_id")
  private CharacterInfo characterInfo;

  @Builder
  private Bookmark(Member member, CharacterInfo characterInfo) {
    this.member = member;
    this.characterInfo = characterInfo;
  }

  public static Bookmark createBookmark(Member member, CharacterInfo characterInfo) {
    return Bookmark.builder()
        .member(member)
        .characterInfo(characterInfo)
        .build();
  }
}
