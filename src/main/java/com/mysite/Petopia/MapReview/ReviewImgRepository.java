package com.mysite.Petopia.MapReview;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImgRepository extends JpaRepository<ReviewImgDTO, Long> {

	void deleteAllByReview(MapReviewDTO review);

	List<ReviewImgDTO> findByReview(MapReviewDTO dto);

	void deleteByReview_id(Long review_id);

}