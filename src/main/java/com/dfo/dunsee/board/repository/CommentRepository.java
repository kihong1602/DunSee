package com.dfo.dunsee.board.repository;

import com.dfo.dunsee.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
