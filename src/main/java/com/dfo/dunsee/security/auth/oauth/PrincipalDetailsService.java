package com.dfo.dunsee.security.auth.oauth;

import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.domain.member.entity.Member;
import com.dfo.dunsee.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

  private final MemberRepository memberRepository;
  private static final ServiceCode SERVICE_CODE = ServiceCode.MBR201;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(ServiceCode.setServiceMsg(SERVICE_CODE) + "DB 회원조회");
    Member memberEntity = memberRepository.findByUsername(username);
    return memberEntity != null ? new PrincipalDetails(memberEntity) : null;

  }
}
