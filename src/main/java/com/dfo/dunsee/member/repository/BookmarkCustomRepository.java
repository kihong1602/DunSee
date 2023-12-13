package com.dfo.dunsee.member.repository;

import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.member.entity.Member;
import java.util.List;

public interface BookmarkCustomRepository {

  List<CharacterInfo> findCharacterInfoByMember(Member member);

}
