package com.dfo.dunsee.board.repository;


import com.dfo.dunsee.board.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

}
