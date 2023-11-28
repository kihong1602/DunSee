package com.dfo.dunsee.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamicInsert
public class Member {

  @Builder(builderMethodName = "generalBuilder")
  private Member(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }

  @Builder(builderMethodName = "oAuth2Builder")
  private Member(String username, String password, String email, String role,
      String provider, String providerId) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.provider = provider;
    this.providerId = providerId;
  }

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true)
  private String username;

  private String password;

  @Column(unique = true)
  private String email;

  @ColumnDefault("'ROLE_USER'")
  private String role;

  @ColumnDefault("'dunsee'")
  private String provider;

  @Column(name = "provider_id")
  @ColumnDefault("199716020616")
  private String providerId;

  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private Favorite favorite;

  @PrePersist
  public void prePersist() {
    this.createdDate = LocalDateTime.now();
  }

}