package com.dfo.dunsee.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite_characters")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FavoriteCharacters {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String imgUrl;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "favorite_id")
  private Favorite favorite;
}
