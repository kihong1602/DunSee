package com.dfo.dunsee.domain.board.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import com.dfo.dunsee.domain.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class QuestionBoardRepositoryTest {

  @Autowired
  private QuestionBoardRepository questionBoardRepository;

  @PersistenceContext
  private EntityManager em;

  @BeforeEach
  void init() {
    Member member1 = Member.builder().username("이름1").build();

    em.persist(member1);

    QuestionBoard freeBoard1 = QuestionBoard.builder()
        .title("제목")
        .content("내용")
        .member(member1)
        .build();
    QuestionBoard freeBoard2 = QuestionBoard.builder()
        .title("제목")
        .content("내용")
        .member(member1)
        .build();
    questionBoardRepository.saveAndFlush(freeBoard1);
    questionBoardRepository.saveAndFlush(freeBoard2);
    em.clear();
  }

  @Test
  @DisplayName("질문게시판 조회 테스트")
  void findByMemberId() {
    //given
    Member member1 = Member.builder().username("이름1").build();

    //when
    List<QuestionBoard> result = questionBoardRepository.findByUsername(member1.getUsername());
    //then
    assertThat(result).hasSize(2)
                      .extracting("title", "content", "likeCount", "viewCount")
                      .containsExactlyInAnyOrder(
                          tuple("제목", "내용", 0, 0),
                          tuple("제목", "내용", 0, 0)
                      );

  }
}