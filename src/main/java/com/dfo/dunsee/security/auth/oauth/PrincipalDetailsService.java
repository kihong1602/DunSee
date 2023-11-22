package com.dfo.dunsee.security.auth.oauth;

import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Member memberEntity = memberRepository.findByUsername(username);
    return memberEntity != null ? new PrincipalDetails(memberEntity) : null;
    
  }
}
