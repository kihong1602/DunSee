package com.dfo.dunsee.domain.board.repository;


import com.dfo.dunsee.domain.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

}
