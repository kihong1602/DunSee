package com.dfo.dunsee.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id", unique = true)
  private Member member;

  @OneToMany(mappedBy = "favorite", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FavoriteCharacters> favoriteCharactersList = new ArrayList<>();
  //cascade All -> 연관된 엔티티에 대한 모든 변경사항을 적용
  //orphanRemoval true -> FavoriteCharacters 에서 Favorite 에 속하지 않는 경우 자동으로 삭제
}
