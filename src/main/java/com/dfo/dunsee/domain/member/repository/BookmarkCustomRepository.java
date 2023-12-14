package com.dfo.dunsee.domain.member.repository;

import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import com.dfo.dunsee.domain.member.entity.Member;
import java.util.List;

public interface BookmarkCustomRepository {

  List<CharacterInfo> findCharacterInfoByMember(Member member);

}
