package com.mysite.Petopia.UserMypage;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.Petopia.Board.BoardDTO;

@RestController
@RequestMapping("/recommend")
public class UserRecommendController {

	private UserRecommendService service;

	public UserRecommendController(UserRecommendService service) {
		this.service = service;
	}

	// 검사
	@PostMapping("/confirm")
	public Boolean selectRecommend(@RequestBody UserRecommendDTO recommend) {
		return service.selectRecommend(recommend.getPost().getId(), recommend.getUser().getEmail());
	}

	// 추천
	@PostMapping("/upper")
	public void insertRecommend(@RequestBody UserRecommendDTO recommend) {
		service.insertRecommend(recommend.getPost(), recommend.getUser());
	}

	// 해제
	@PostMapping("/lower")
	public void deleteRecommend(@RequestBody UserRecommendDTO recommend) {
		service.deleteRecommend(recommend.getPost(), recommend.getUser());
	}
}
