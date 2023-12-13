package com.dfo.dunsee.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

import com.dfo.dunsee.member.entity.Bookmark;
import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.member.entity.Member;
import com.dfo.dunsee.search.repository.CharacterInfoRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BookmarkRepositoryTest {


  @Autowired
  private BookmarkRepository bookmarkRepository;

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private CharacterInfoRepository characterInfoRepository;

  private Member testMember;
  private CharacterInfo testInfo1;
  private CharacterInfo testInfo2;

  @BeforeEach
  void before() {
    testMember = Member.builder().build();
    testInfo1 = CharacterInfo.builder().imgUrl("testImgUrl1").adventureName("testAdventureName").build();
    testInfo2 = CharacterInfo.builder().imgUrl("testImgUrl2").adventureName("testAdventureName").build();

    memberRepository.save(testMember);
    characterInfoRepository.save(testInfo1);
    characterInfoRepository.save(testInfo2);

    Bookmark testBookmark1 = Bookmark.createBookmark(testMember, testInfo1);
    Bookmark testBookmark2 = Bookmark.createBookmark(testMember, testInfo2);

    bookmarkRepository.save(testBookmark1);
    bookmarkRepository.save(testBookmark2);
  }

  @Test
  @DisplayName("멤버 엔티티를 입력하면 캐릭터정보가 반환된다.")
  void findCharacterInfoByMemberTest() {
    //given

    //when
    List<CharacterInfo> result = bookmarkRepository.findCharacterInfoByMember(testMember);

    //then
    assertThat(result).hasSize(2).extracting("imgUrl", "adventureName").containsExactlyInAnyOrder(
        tuple("testImgUrl1", "testAdventureName"),
        tuple("testImgUrl2", "testAdventureName")
    );
  }

  @Test
  @DisplayName("회원정보와 캐릭터정보를 등록한 북마크가 있는지 체크한다")
  void findBookmarkByMemberAndCharacterInfoTest() {
    //given

    //when
    Bookmark testBookmark1 = bookmarkRepository.findByMemberAndCharacterInfo(testMember, testInfo1);
    Bookmark testBookmark2 = bookmarkRepository.findByMemberAndCharacterInfo(testMember, testInfo2);

    //then
    assertThat(testBookmark1.getCharacterInfo()).extracting("imgUrl", "adventureName")
                                                .contains("testImgUrl1", "testAdventureName");
    assertThat(testBookmark2.getCharacterInfo()).extracting("imgUrl", "adventureName")
                                                .contains("testImgUrl2", "testAdventureName");
    assertThat(testBookmark1.getMember()).isEqualTo(testMember);
    assertThat(testBookmark2.getMember()).isEqualTo(testMember);
  }

  @Test
  @DisplayName("북마크를 삭제한다")
  void deleteBookmark() {
    //given

    //when
    int result1 = bookmarkRepository.deleteByMemberAndCharacterInfo(testMember, testInfo1);
    int result2 = bookmarkRepository.deleteByMemberAndCharacterInfo(testMember, testInfo2);

    //then
    assertThat(result1).isEqualTo(1);
    assertThat(result2).isEqualTo(1);
  }
}