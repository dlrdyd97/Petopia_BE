package com.mysite.Petopia.AdminPage;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.Petopia.AdminPage.BoardReportDTO.ProcessingStatus;
import com.mysite.Petopia.AdminPage.BoardReportDTO.ReportReason;
import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.BoardRepository;
import com.mysite.Petopia.MapReview.MapReviewDTO;
import com.mysite.Petopia.MapReview.MapReviewRepository;

import com.mysite.Petopia.MapReview.MapReviewDTO;
import com.mysite.Petopia.MapReview.MapReviewRepository;

import com.mysite.Petopia.Users.UsersDTO;

@Service
public class BoardReportService {

	private BoardReportRepository repository;
	private BoardRepository boardRepository;
	private MapReviewRepository mapReviewRepository;

	public BoardReportService(BoardReportRepository repository, BoardRepository boardRepository,
			MapReviewRepository mapReviewRepository) {
		this.repository = repository;
		this.boardRepository = boardRepository;
		this.mapReviewRepository = mapReviewRepository;
	}

	public void insertBoardReport(BoardDTO board, UsersDTO user, ReportReason reportReason, String otherReason,
			ProcessingStatus status) {
		BoardReportDTO boardReportDTO = new BoardReportDTO();
		boardReportDTO.setPost(board);
		boardReportDTO.setReportDate(LocalDateTime.now());
		boardReportDTO.setReporter(user);
		boardReportDTO.setReason(reportReason);
		boardReportDTO.setOtherReason(otherReason);
		boardReportDTO.setProcessingStatus(status);
		repository.save(boardReportDTO);
		boardRepository.reportBoard(board.getId());
	}

	public void insertReviewReport(MapReviewDTO review, UsersDTO user, ReportReason reportReason, String otherReason,
			ProcessingStatus status) {
		BoardReportDTO boardReportDTO = new BoardReportDTO();
		boardReportDTO.setReview(review);
		boardReportDTO.setReportDate(LocalDateTime.now());
		boardReportDTO.setReporter(user);
		boardReportDTO.setReason(reportReason);
		boardReportDTO.setOtherReason(otherReason);
		boardReportDTO.setProcessingStatus(status);
		repository.save(boardReportDTO);
		mapReviewRepository.reportReview(review.getId());
	}

	public List<BoardReportDTO> selectBoardReportlist(int num) {
//		return repository.findAllByOrderByPostIdAscReportDateDesc();
		if (num == 0) {
	        return repository.findByReviewIsNullOrderByReportDateDesc();
	    } else if (num == 1) {
	        return repository.findByPostIsNullOrderByReportDateDesc();
	    } else {
	        return Collections.emptyList(); 
	    }
	}

	public void updateBoardReport(Long id, ProcessingStatus status) {
		String temp = status.toString();
		repository.updateBoardReport(id, temp);
	}
	
	//삭제를 위한 리포트 리스트 get
	public List<BoardReportDTO> getByReporter_email(String email) {
		String reporter_email = email;
		return repository.getByReporter_email(reporter_email);
	}

	// 해당 계정이 리포트한 데이터 삭제
	public void deleteByEmail(String email) {
		
	}

	public void deleteAllByReporter_email(String email) {
		repository.deleteAllByReporter_email(email);
	}

	public void deleteByReporter_email(String reporter_email) {
		repository.deleteByReporter_email(reporter_email);
		
	}

	public List<BoardReportDTO> findAllByReporter_email(String user_email) {
		String reporter_email = user_email;
		return repository.findAllByReporter_email(reporter_email);
	}

	public void deleteByReview_id(Long id) {
		repository.deleteByReview_id(id);
		
	}

}
