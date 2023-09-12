package com.mysite.Petopia.Board;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.Users.UsersDTO;

@Service
public class CommentService {

	private CommentRepository repository;
	
	public CommentService(CommentRepository repository) {
		this.repository = repository;
	}
	
	public void insertComment(BoardDTO board, UsersDTO user, String content) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setPost(board);
		commentDTO.setAuthor(user);
		commentDTO.setContent(content);
		commentDTO.setCreatedAt(LocalDateTime.now());
		commentDTO.setUpdatedAt(LocalDateTime.now());
		repository.save(commentDTO);
	}
	
	public List<CommentDTO> findallComment(Long post_id) {
		return repository.findAllByPost_id(post_id);
	}
}
