package com.dfo.dunsee.member.repository;

import com.dfo.dunsee.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

  Member findByUsername(String username);

  Member findByEmail(String email);

}
