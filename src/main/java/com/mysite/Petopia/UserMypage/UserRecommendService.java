package com.mysite.Petopia.UserMypage;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.BoardRepository;
import com.mysite.Petopia.Board.BoardDTO.BoardCategory;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

@Service
public class UserRecommendService {

	private UserRecommendRepository repository;
	private BoardRepository boardRepository;

	public UserRecommendService(UserRecommendRepository repository, BoardRepository boardRepository) {
		this.repository = repository;
		this.boardRepository = boardRepository;
	}

	// 검사
	public Boolean selectRecommend(Long post_id, String user_email) {
		return repository.existsByPost_idAndUser_email(Long.valueOf(post_id), user_email);
	}

	// 추천
	public void insertRecommend(BoardDTO post_id, UsersDTO user_email) {
		UserRecommendDTO recommendDTO = new UserRecommendDTO();
		recommendDTO.setPost(post_id);
		recommendDTO.setUser(user_email);
		recommendDTO.setClickedAt(LocalDateTime.now());
		boardRepository.updateRecommends(post_id.getId());
		repository.save(recommendDTO);
	}

	// 해제
	@Transactional
	public void deleteRecommend(BoardDTO post_id, UsersDTO user_email) {
		boardRepository.deleteRecommend(post_id.getId());
		repository.deleteByPost_idAndUser_email(post_id.getId(), user_email.getEmail());
	}

	public void deleteAllByUser_email(String email) {
		repository.deleteAllByUser_email(email);

	}

	public List<UserRecommendDTO> findAllByUser_email(String user_email) {
		return repository.findAllByUser_email(user_email);
	}

}
