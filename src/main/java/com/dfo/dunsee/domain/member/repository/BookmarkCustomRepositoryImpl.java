package com.dfo.dunsee.domain.member.repository;


import static com.dfo.dunsee.domain.member.entity.QBookmark.bookmark;

import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.member.entity.Member;
import com.dfo.dunsee.domain.member.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookmarkCustomRepositoryImpl implements BookmarkCustomRepository {


  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<CharacterInfo> findCharacterInfoByMember(Member member) {

    return jpaQueryFactory.select(bookmark.characterInfo)
                          .from(QMember.member)
                          .innerJoin(QMember.member.bookmarks, bookmark)
                          .where(QMember.member.eq(member))
                          .fetch();
  }
}
