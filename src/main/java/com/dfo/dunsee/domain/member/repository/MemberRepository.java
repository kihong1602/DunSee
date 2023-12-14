package com.dfo.dunsee.domain.member.repository;

import com.dfo.dunsee.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByUsername(String username);

  Member findByEmail(String email);

}
