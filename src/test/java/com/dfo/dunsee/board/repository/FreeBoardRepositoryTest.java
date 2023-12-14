package com.dfo.dunsee.board.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.dfo.dunsee.board.entity.FreeBoard;
import com.dfo.dunsee.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class FreeBoardRepositoryTest {

  @Autowired
  private FreeBoardRepository freeBoardRepository;


  @PersistenceContext
  private EntityManager em;

  @BeforeEach
  void init() {
    Member member1 = Member.builder().username("이름1").build();

    em.persist(member1);

    FreeBoard freeBoard1 = FreeBoard.builder()
        .title("제목")
        .content("내용")
        .member(member1)
        .build();
    FreeBoard freeBoard2 = FreeBoard.builder()
        .title("제목")
        .content("내용")
        .member(member1)
        .build();
    freeBoardRepository.saveAndFlush(freeBoard1);
    freeBoardRepository.saveAndFlush(freeBoard2);
    em.clear();
  }


  @Test
  @DisplayName("조회 테스트")
  void findAndPostTest() {
    //given

    //when
    List<FreeBoard> result = freeBoardRepository.findAll();
    //then
    System.out.println(result.get(0).getCreatedDate());
    System.out.println(result.get(0).getLikeCount());
    assertThat(result).hasSize(2)
                      .extracting("title", "content", "likeCount", "viewCount")
                      .containsExactlyInAnyOrder(
                          tuple("제목", "내용", 0, 0),
                          tuple("제목", "내용", 0, 0)
                      );

  }
}