package com.dfo.dunsee.domain.member.entity;

import com.dfo.dunsee.domain.board.entity.Comment;
import com.dfo.dunsee.domain.board.entity.FreeBoard;
import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@DynamicInsert
public class Member extends BaseTimeEntity {

  @Builder
  private Member(String username, String password, String nickName, String email, String role, String provider,
      String providerId, List<Bookmark> bookmarks) {
    this.username = username;
    this.password = password;
    this.nickName = preventDuplicationNickName(nickName);
    this.email = email;
    this.role = role;
    this.provider = provider;
    this.providerId = providerId == null ? createProviderId() : providerId;
    this.bookmarks = bookmarks;
  }

  @JsonIgnore
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  @Setter(AccessLevel.PRIVATE)
  private String password;

  @Setter(AccessLevel.PRIVATE)
  private String nickName;

  @Column(unique = true)
  private String email;

  @ColumnDefault("'ROLE_USER'")
  private String role;

  @ColumnDefault("'dunsee'")
  private String provider;

  @Column(name = "provider_id")
  private String providerId;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Bookmark> bookmarks = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<FreeBoard> freeBoards = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<QuestionBoard> questionBoard = new ArrayList<>();

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  private String createProviderId() {
    return UUID.randomUUID().toString().replace("-", "").substring(0, 21);
  }

  public String preventDuplicationNickName(String nickName) {
    String uuidSplit = UUID.randomUUID().toString().replace("-", "").substring(0, 10);

    if (nickName == null) {
      return "dunsee_" + uuidSplit;
    } else {
      return nickName + "_" + uuidSplit;
    }
  }

  public void changeNickName(String nickName) {
    if (nickName == null) {
      throw new IllegalArgumentException();
    }
    setNickName(nickName);
  }

  public void changePassword(String password) {
    //패스워드 암호화 후 들어와야함.
    if (password == null) {
      throw new IllegalArgumentException();
    }
    setPassword(password);
  }
}