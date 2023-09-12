package com.mysite.Petopia.UserMypage;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysite.Petopia.Board.BoardDTO;

@RestController
@RequestMapping("/interest")
public class UserInterestController {

	private UserInterestService service;

	public UserInterestController(UserInterestService service) {
		this.service = service;
	}

	// 검사
	@PostMapping("/confirmlike")
	public Boolean selectInterest(@RequestBody UserInterestDTO interest) {
		return service.selectInterest(interest.getPost().getId(), interest.getUser().getEmail());
	}

	// 추천
	@PostMapping("/upperlike")
	public void insertInterest(@RequestBody UserInterestDTO interest) {
		service.insertInterest(interest.getPost(), interest.getUser());
	}

	// 해제
	@PostMapping("/lowerlike")
	public void deleteInterest(@RequestBody UserInterestDTO interest) {
		service.deleteInterest(interest.getPost(), interest.getUser());
	}

	// 관심목록 리스트
	@GetMapping("/list")
	public List<BoardDTO> getInterestList(@RequestParam("email") String email) {
		return service.getInterestList(email);
	}

}
