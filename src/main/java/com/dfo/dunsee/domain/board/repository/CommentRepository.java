package com.dfo.dunsee.domain.board.repository;

import com.dfo.dunsee.domain.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
