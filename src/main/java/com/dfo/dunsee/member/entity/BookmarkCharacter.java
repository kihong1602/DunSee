package com.dfo.dunsee.member.entity;

import jakarta.persistence.Entity;
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

@Entity
@Table(name = "bookmark_character")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookmarkCharacter {

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
  private BookmarkCharacter(Member member, CharacterInfo characterInfo) {
    this.member = member;
    this.characterInfo = characterInfo;
  }
}
