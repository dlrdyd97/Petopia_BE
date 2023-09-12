package com.mysite.Petopia.MapReview;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.AdminPage.BoardReportRepository;
import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.BoardImgDTO;
import com.mysite.Petopia.Map.MapDTO;
import com.mysite.Petopia.Map.MapRepository;
import com.mysite.Petopia.Users.UserRepository;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Sort;

@Service
public class MapReviewServiceImpl implements MapReviewService {

	private MapReviewRepository mapReviewRepository;
	private UserRepository userRepository;
	private MapRepository mapRepository;
	private ReviewImgRepository reviewImgRepository;
	private BoardReportRepository boardReportRepository;

	public MapReviewServiceImpl(MapReviewRepository mapReviewRepository, UserRepository userRepository,
			MapRepository mapRepository, ReviewImgRepository reviewImgRepository, BoardReportRepository boardReportRepository) {
		this.mapReviewRepository = mapReviewRepository;
		this.userRepository = userRepository;
		this.mapRepository = mapRepository;
		this.reviewImgRepository = reviewImgRepository;
		this.boardReportRepository = boardReportRepository;
	}

	@Override
	public Long insertReview(MapReviewDTO mapReviewDTO) {

		UsersDTO dto = new UsersDTO();
		MapDTO map = new MapDTO();
		Optional<UsersDTO> userdto = userRepository.findById(mapReviewDTO.getUsername());
		if (userdto.isPresent()) {
			dto = userdto.get();
		}

		Optional<MapDTO> mapdto = mapRepository.findById(mapReviewDTO.getMapid());
		if (mapdto.isPresent()) {
			map = mapdto.get();
		}

		mapReviewDTO.setLocation(map);
		mapReviewDTO.setUpdatedAt(LocalDateTime.now());
		mapReviewDTO.setWriter(dto);
		return mapReviewRepository.save(mapReviewDTO).getId();

	}

	@Override
	public void reviewimgupload(String uploadfiles, Long num) {
		MapReviewDTO mapdto = new MapReviewDTO();
		Optional<MapReviewDTO> reviewdto = mapReviewRepository.findById(num);
		if (reviewdto.isPresent()) {
			mapdto = reviewdto.get();
		}

		ReviewImgDTO dto = new ReviewImgDTO();
		dto.setImageUrl(uploadfiles);
		dto.setReview(mapdto);
		reviewImgRepository.save(dto);
	}

	@Override
	public List<MapReviewDTO> reviewList(Long id, int num) {
		MapDTO map = new MapDTO();
		Optional<MapDTO> mapdto = mapRepository.findById(id);
		if (mapdto.isPresent()) {
			map = mapdto.get();
		}

//		return mapReviewRepository.findByLocation(map);
		if (num == 0) {
			return mapReviewRepository.findByLocationOrderByUpdatedAtDesc(map);
		} else if (num == 1) {
			return mapReviewRepository.findByLocation(map);
		} else if (num == 2) {
			return mapReviewRepository.findByLocationOrderByRatingDescUpdatedAtDesc(map);
		} else if (num == 3) {
			return mapReviewRepository.findByLocationOrderByRatingAscUpdatedAtDesc(map);
		} else {
			return mapReviewRepository.findByLocation(map);
		}

	}

	@Override
	public List<ReviewImgDTO> reviewImgList(Long id) {

		List<ReviewImgDTO> imgdto = new ArrayList<>();

		MapDTO map = new MapDTO();
		Optional<MapDTO> mapdto = mapRepository.findById(id);
		if (mapdto.isPresent()) {
			map = mapdto.get();
		}

		List<MapReviewDTO> dto = mapReviewRepository.findByLocation(map);
		for (int i = 0; i < dto.size(); i++) {
			List<ReviewImgDTO> reviewdtos = reviewImgRepository.findByReview(dto.get(i));

			if (reviewdtos != null && !reviewdtos.isEmpty()) {
				imgdto.addAll(reviewdtos);
			}
		}

		return imgdto;
	}

	public void deleteByWriter_email(String user_email) {
		String Writer_email = user_email;
		mapReviewRepository.deleteByWriter_email(Writer_email);
		
	}
	
	// 게시글 신고 감소
	public void deleteReportReview(Long id) {
		mapReviewRepository.deleteReportReview(id);
	}


	public List<MapReviewDTO> getByWriter_email(String email) {
		return mapReviewRepository.getByWriter_email(email);
	}

	public void deleteReview(Long id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Long id) {
		mapReviewRepository.deleteById(id);
		
	}

//	@Transactional
//	// 리뷰 삭제
//	public void deleteReview(Long id) {
//		reviewImgRepository.deleteByReview_id(id);
//		boardReportRepository.deleteAllByMap_review_id(id);
//		mapReviewRepository.deleteById(id);
//	}
	
	
	@Override
	public List<ReviewImgDTO> reviewReportImg(Long id) {
		MapReviewDTO reviewDTO = new MapReviewDTO();
		Optional<MapReviewDTO> dto = mapReviewRepository.findById(id);
		if (dto.isPresent()) {
			reviewDTO = dto.get();
		}
		return reviewImgRepository.findByReview(reviewDTO);
	}

	@Override
	public int countByWriterEmail(String writerEmail) {
		return mapReviewRepository.countByWriterEmail(writerEmail);
	}

	@Override
	public void incrementReportStatus(Long reviewId) {
		mapReviewRepository.incrementReportStatus(reviewId);
	}

}