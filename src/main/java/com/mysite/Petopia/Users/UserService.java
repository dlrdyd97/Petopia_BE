package com.mysite.Petopia.Users;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.AdminPage.BoardReportRepository;
import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.BoardImgRepository;
import com.mysite.Petopia.Board.BoardRepository;
import com.mysite.Petopia.Board.Travel.TravelRepository;
import com.mysite.Petopia.MapReview.MapReviewDTO;
import com.mysite.Petopia.MapReview.ReviewImgDTO;
import com.mysite.Petopia.UserMypage.UserInterestRepository;
import com.mysite.Petopia.UserMypage.UserRecommendRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private UserRepository repository;
	private BoardRepository boardRepository;
	private BoardImgRepository imgRepository;
	private TravelRepository travelRepository;
	private UserRecommendRepository userRecommendRepository;
	private UserInterestRepository userInterestRepository;
	private BoardReportRepository boardReportRepository;

	public UserService(UserRepository repository, BoardRepository boardRepository, BoardImgRepository imgRepository,
			TravelRepository travelRepository, UserRecommendRepository userRecommendRepository,
			UserInterestRepository userInterestRepository, BoardReportRepository boardReportRepository) {
		this.repository = repository;
		this.boardRepository = boardRepository;
		this.imgRepository = imgRepository;
		this.travelRepository = travelRepository;
		this.userRecommendRepository = userRecommendRepository;
		this.userInterestRepository = userInterestRepository;
		this.boardReportRepository = boardReportRepository;
	}

	public void insertUserPetopia(String email, String provider, String password, String name, String nickname,
			LocalDate birthday, String profileImage) {
		repository.insertUserPetopia(email, provider, password, name, nickname, birthday, profileImage);

	}

	public void insertUserKakao(String email, String provider, String password, String nickname, String profileImage) {
		repository.insertUserKakao(email, provider, password, nickname, profileImage);

	}

	public void insertUserNaver(String email, String provider, String password, String name, String nickname,
			String profileImage) {
		repository.insertUserNaver(email, provider, password, name, nickname, profileImage);

	}

	public UsersDTO getUserPetopia(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}

	public UsersDTO getUserInfo(String email) {
		return repository.findById(email).orElse(null);
	}

	public List<UsersDTO> findUserEmail(String name, String password, LocalDate birthday) {
		return repository.findByNameAndPasswordAndBirthday(name, password, birthday);
	}

	public UsersDTO findUserPassword(String email, String name, LocalDate birthday) {
		return repository.findByEmailAndNameAndBirthday(email, name, birthday);
	}

	public void updateUserInfo(String email, String name, String nickname, String password) {
		repository.updateUserInfo(email, name, nickname, password);
	}
	
	// 닉네임 변경
	public void updateNickname(String email, String nickname) {
		repository.updateNickname(email, nickname);
	}
	
	// 프로필사진 변경
	public void updateProFileImg(String email, String uploadfiles) {
		repository.updateProFileImg(email, uploadfiles);
	}
	
	// 회원탈퇴
	@Transactional
	public void deleteUserInfo(String email) {
		repository.deleteByEmail(email);
	}

}
