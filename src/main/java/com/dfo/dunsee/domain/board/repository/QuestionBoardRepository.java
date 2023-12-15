package com.dfo.dunsee.domain.board.repository;


import com.dfo.dunsee.domain.board.entity.QuestionBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionBoardRepository extends JpaRepository<QuestionBoard, Long> {

  @Query("select q from QuestionBoard q where q.member.username = :username")
  List<QuestionBoard> findByUsername(@Param("username") String username);
}
