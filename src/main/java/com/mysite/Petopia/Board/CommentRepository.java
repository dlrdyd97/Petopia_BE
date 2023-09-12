package com.mysite.Petopia.Board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentDTO, Long>{

	List<CommentDTO> findAllByPost_id(Long post_id);

}
