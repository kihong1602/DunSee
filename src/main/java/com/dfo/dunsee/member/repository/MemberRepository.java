package com.dfo.dunsee.member.repository;

import com.dfo.dunsee.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {

  Member findByUsername(String username);

  Member findByEmail(String email);

}
