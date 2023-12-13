package com.dfo.dunsee.member.repository;

import com.dfo.dunsee.member.entity.Bookmark;
import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer>, BookmarkCustomRepository {

  Bookmark findByMemberAndCharacterInfo(Member member, CharacterInfo characterInfo);

  int deleteByMemberAndCharacterInfo(Member member, CharacterInfo characterInfo);
}
