package com.dfo.dunsee.domain.board.repository;


import com.dfo.dunsee.domain.board.entity.FreeBoard;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

  @Query("select f from FreeBoard f where f.member.username = :username")
  List<FreeBoard> findByUsername(@Param("username") String username);
}
