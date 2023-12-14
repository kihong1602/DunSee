package com.dfo.dunsee.domain.search.repository;

import com.dfo.dunsee.domain.member.entity.CharacterInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterInfoRepository extends JpaRepository<CharacterInfo, Long> {

  List<CharacterInfo> findByAdventureName(String adventureName);

  boolean existsByImgUrl(String imgUrl);

  CharacterInfo findByImgUrl(String imgUrl);
}
