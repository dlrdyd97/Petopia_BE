package com.mysite.Petopia.UserMypage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.BoardRepository;
import com.mysite.Petopia.Users.UserRepository;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

@Service
public class UserInterestService {

	private UserInterestRepository repository;
	private BoardRepository boardRepository;
	private UserRepository userRepository;

	public UserInterestService(UserInterestRepository repository, BoardRepository boardRepository,
			UserRepository userRepository) {
		this.repository = repository;
		this.boardRepository = boardRepository;
		this.userRepository = userRepository;
	}

	// 검사
	public Boolean selectInterest(Long post_id, String user_email) {
		return repository.existsByPost_idAndUser_email(Long.valueOf(post_id), user_email);
	}

	// 추천
	public void insertInterest(BoardDTO post_id, UsersDTO user_email) {
		UserInterestDTO InterestDTO = new UserInterestDTO();
		InterestDTO.setPost(post_id);
		InterestDTO.setUser(user_email);
		InterestDTO.setClickedAt(LocalDateTime.now());
		boardRepository.updateInterest(post_id.getId());
		repository.save(InterestDTO);
	}

	// 해제
	@Transactional
	public void deleteInterest(BoardDTO post_id, UsersDTO user_email) {
		boardRepository.deleteInterest(post_id.getId());
		repository.deleteByPost_idAndUser_email(post_id.getId(), user_email.getEmail());
	}

	@Transactional
	public int deleteByUser_email(String user_email) {
		return repository.deleteByUser_email(user_email);

	}

	public List<UserInterestDTO> findAllByUser_email(String user_email) {
		return repository.findAllByUser_email(user_email);
	}

	// 관심목록 리스트
	public List<BoardDTO> getInterestList(String email) {

		Optional<UsersDTO> optionaldto = userRepository.findById(email);
		UsersDTO userdto = new UsersDTO();
		if (optionaldto.isPresent()) {
			userdto = optionaldto.get();
		}

		List<BoardDTO> boardDTO = new ArrayList<>();
		List<UserInterestDTO> dto = repository.findByUser(userdto);

		Optional<BoardDTO> odto;
		BoardDTO bdto = null;

		System.out.println("데이터 값 : " + dto.get(0).getId());

		for (int i = 0; i < dto.size(); i++) {
			// odto = boardRepository.findById(dto.get(i).getId());
			odto = boardRepository.findById(dto.get(i).getPost().getId());

			if (odto.isPresent()) {
				bdto = odto.get();
				boardDTO.add(bdto);
			}
		}

		return boardDTO;
	}
}
