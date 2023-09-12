package com.mysite.Petopia.UserMypage.Review;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.AdminPage.BoardReportRepository;
import com.mysite.Petopia.MapReview.MapReviewDTO;
import com.mysite.Petopia.MapReview.MapReviewRepository;
import com.mysite.Petopia.MapReview.ReviewImgRepository;
import com.mysite.Petopia.Users.UserRepository;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	private UserRepository userRepository;

	private MapReviewRepository mapReviewRepository;

	private ReviewImgRepository reviewImgRepository;

	private BoardReportRepository boardReportRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository,
			MapReviewRepository mapReviewRepository, ReviewImgRepository reviewImgRepository,
			BoardReportRepository boardReportRepository) {
		this.reviewRepository = reviewRepository;
		this.userRepository = userRepository;
		this.mapReviewRepository = mapReviewRepository;
		this.reviewImgRepository = reviewImgRepository;
		this.boardReportRepository = boardReportRepository;
	}

	@Override
	@Transactional
	public void deleteById(List<Long> id) {
		MapReviewDTO reviewdto = new MapReviewDTO();
		Optional<MapReviewDTO> dto;
		for (int i = 0; i < id.size(); i++) {
			dto = mapReviewRepository.findById(id.get(i));
			if (dto.isPresent()) {
				reviewdto = dto.get();
				reviewImgRepository.deleteAllByReview(reviewdto);
			}
		}
		boardReportRepository.deleteByReview_id(id.get(0));
		reviewRepository.deleteById(id);
	}

	@Override
	public List<MapReviewDTO> findByWriter(String writer) {
		Optional<UsersDTO> optionaldto = userRepository.findById(writer);
		UsersDTO dto = new UsersDTO();
		if (optionaldto.isPresent()) {
			dto = optionaldto.get();
		} else {
			System.out.println("이메일이 존재하지 않음");
		}
		return reviewRepository.findByWriterOrderByUpdatedAtDesc(dto);
	}

	public void deleteByWriter_email(String user_email) {
		String writer_email = user_email;
		reviewRepository.deleteByWriter_email(writer_email);
		
	}


}
