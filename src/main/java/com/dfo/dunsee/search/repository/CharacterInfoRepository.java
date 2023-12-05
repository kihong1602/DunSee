package com.dfo.dunsee.search.repository;

import com.dfo.dunsee.member.entity.CharacterInfo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterInfoRepository extends JpaRepository<CharacterInfo, Integer> {

  List<CharacterInfo> findByAdventureName(String adventureName);

  boolean existsByImgUrl(String imgUrl);
}
