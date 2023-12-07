package com.dfo.dunsee.member.repository;

import com.dfo.dunsee.member.entity.Bookmark;
import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {

  Bookmark findByMemberAndCharacterInfo(Member member, CharacterInfo characterInfo);

  @Query("select b.characterInfo from Member m join m.bookmarks b where m = :member")
  List<CharacterInfo> findCharacterInfoByMember(Member member);
}
