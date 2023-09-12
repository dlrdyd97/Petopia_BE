package com.mysite.Petopia.UserMypage.Review;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.Petopia.MapReview.MapReviewDTO;

@RestController
public class ReviewController {

	private ReviewServiceImpl reviewServiceImpl;

	public ReviewController(ReviewServiceImpl reviewServiceImpl) {
		this.reviewServiceImpl = reviewServiceImpl;
	}

	@RequestMapping("/myreviewdelete")
	public void deleteInquiry(@RequestBody List<Long> id) {
		reviewServiceImpl.deleteById(id);
	}
	
	@RequestMapping("/myreviewlist")
	public List<MapReviewDTO> getinquirylist(@RequestParam String writer) {
		return reviewServiceImpl.findByWriter(writer);
	}

}
