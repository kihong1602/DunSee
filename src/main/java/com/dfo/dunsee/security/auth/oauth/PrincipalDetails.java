package com.dfo.dunsee.security.auth.oauth;

import com.dfo.dunsee.domain.member.entity.Member;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

  private transient Member member;
  private Map<String, Object> attributes;

  public PrincipalDetails(Member member) {
    this.member = member;
  }

  public PrincipalDetails(Member member, Map<String, Object> attributes) {
    this.member = member;
    this.attributes = attributes;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    //해당 유저의 권한을 반환하는 메서드
    Collection<GrantedAuthority> collect = new ArrayList<>();
    collect.add((GrantedAuthority) member::getRole);
    return collect;
  }


  @Override
  public String getUsername() {
    return member.getUsername();
  }

  @Override
  public String getPassword() {
    return member.getPassword();
  }


  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Map<String, Object> getAttributes() {
    return attributes;
  }

  @Override
  public String getName() {
    return member.getEmail();
  }
}
