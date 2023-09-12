package com.mysite.Petopia.Board;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.AdminPage.BoardReportRepository;
import com.mysite.Petopia.Board.BoardDTO.BoardCategory;
import com.mysite.Petopia.Board.Travel.TravelRepository;
import com.mysite.Petopia.UserMypage.UserInterestRepository;
import com.mysite.Petopia.UserMypage.UserRecommendRepository;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

@Service
public class BoardService {

	private BoardRepository repository;
	private BoardImgRepository imgRepository;
	private TravelRepository travelRepository;
	private UserRecommendRepository userRecommendRepository;
	private UserInterestRepository userInterestRepository;
	private BoardReportRepository boardReportRepository;

	public BoardService(BoardRepository repository, BoardImgRepository imgRepository, TravelRepository travelRepository,
			UserRecommendRepository userRecommendRepository, UserInterestRepository userInterestRepository, BoardReportRepository boardReportRepository) {
		this.repository = repository;
		this.imgRepository = imgRepository;
		this.travelRepository = travelRepository;
		this.userRecommendRepository = userRecommendRepository;
		this.userInterestRepository = userInterestRepository;
		this.boardReportRepository = boardReportRepository;
	}

	// 게시글 작성
	public Long insertBoard(UsersDTO usersDTO, String title, String content, String thumbnailImage,
			BoardCategory boardCategory) {
		BoardDTO board = new BoardDTO();
		board.setAuthor(usersDTO);
		board.setTitle(title);
		board.setContent(content);
		board.setThumbnailImage(thumbnailImage);
		board.setCategory(boardCategory);
		board.setCreatedAt(LocalDateTime.now());
		board.setUpdatedAt(LocalDateTime.now());
		return repository.save(board).getId();
	}

	// 파일 업로드
	public void saveuploadfiles(String uploadfiles, BoardDTO board) {
		BoardImgDTO imgs = new BoardImgDTO();
		imgs.setImageUrl(uploadfiles);
		imgs.setPost(board);
		imgRepository.save(imgs);
	};

	// 게시글 상세
	public BoardDTO selectBoard(Long id, BoardCategory boardCategory) {
		repository.updateBoardView(id);
		return repository.findByIdAndCategory(id, boardCategory);
	}

	public List<BoardImgDTO> selectBoardImg(Long post_id) {
		return imgRepository.findAllByPost_id(post_id);
	}

	@Transactional
	// 게시글 삭제
	public void deleteBoard(Long id) {
		userRecommendRepository.deleteByPost_id(id);
		userInterestRepository.deleteByPost_id(id);
		travelRepository.deleteByPost_id(id);
		imgRepository.deleteByPost_id(id);
		boardReportRepository.deleteByPost_id(id);
		repository.deleteById(id);
	}

	// 게시글 수정
	public void updateBoard(Long id, String title, String content) {
		repository.updateBoard(id, title, content);
	}

	// 여행추천 Best
	public List<BoardDTO> selectTravelBoardBest(BoardCategory category) {
		return repository.findTop3ByCategoryOrderByRecommendsDesc(category);
	}

	// 여행추천 Best5
	public List<BoardDTO> selectTravelBoardBest5(BoardCategory category) {
		return repository.findTop5ByCategoryOrderByRecommendsDesc(category);
	}

	public List<BoardDTO> getByAuthor_email(String email) {
		return repository.getByAuthor_email(email);
	}

	// 게시글 추천 감소
	public void deleteRecommend(Long id) {
		repository.deleteRecommend(id);
	}

	// 게시글 좋아요 감소
	public void deleteInterest(Long id) {
		repository.deleteInterest(id);
	}

	// 게시글 신고 감소
	public void deleteReportBoard(Long id) {
		repository.deleteReportBoard(id);
	}

	public List<BoardDTO> selectBoardAll(BoardCategory category) {
		return repository.findByCategoryOrderByCreatedAtDesc(category);
	}
	
	public List<BoardDTO> selectTravelBoardSearch(String title, String content) {
		return repository.findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase(title, content);
	}
	
	// 내 작성글 카운트
	public int myPostcountByAuthorEmail(String authorEmail) {
		return repository.countByAuthorEmail(authorEmail);
	}
	

}
