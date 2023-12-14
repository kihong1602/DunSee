package com.dfo.dunsee.domain.member.repository;

import com.dfo.dunsee.domain.member.entity.Bookmark;
import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>, BookmarkCustomRepository {

  Bookmark findByMemberAndCharacterInfo(Member member, CharacterInfo characterInfo);

  int deleteByMemberAndCharacterInfo(Member member, CharacterInfo characterInfo);
}
