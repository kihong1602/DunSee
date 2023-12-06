package com.dfo.dunsee.member.service;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.member.dto.ImgUrlDto;
import com.dfo.dunsee.member.entity.Bookmark;
import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.member.repository.BookmarkRepository;
import com.dfo.dunsee.search.repository.CharacterInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookmarkService {

  private final BookmarkRepository bookmarkRepository;
  private final CharacterInfoRepository characterInfoRepository;

  @Transactional
  public ResultType addBookmarkCharacter(ServiceCode serviceCode, ImgUrlDto imgUrlDto, Member member) {
    log.info(ServiceCode.setServiceMsg(serviceCode) + "즐겨찾기 추가 서비스 진행");
    try {
      CharacterInfo characterInfo = characterInfoRepository.findByImgUrl(imgUrlDto.getImgUrl());
      if (isBookmarkExist(member, characterInfo)) {
        return ResultType.EXIST;
      }
      Bookmark bookmark = Bookmark.createBookmark(member, characterInfo);

      bookmarkRepository.save(bookmark);

      return ResultType.SUCCESS;
    } catch (NullPointerException e) {
      log.error(ServiceCode.setServiceMsg(serviceCode) + "NullPointerException 발생");
      return ResultType.FAILURE;
    } catch (DataAccessException e) {
      log.error(ServiceCode.setServiceMsg(serviceCode) + "DataAccessException 발생");
      return ResultType.FAILURE;
    }
  }

  private boolean isBookmarkExist(Member member, CharacterInfo characterInfo) {
    Bookmark findBookmark = bookmarkRepository.findByMemberAndCharacterInfo(member, characterInfo);
    return findBookmark != null;
  }
}
