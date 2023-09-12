package com.mysite.Petopia.AdminPage;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class BoardReportController {

	private BoardReportService service;
	
	public BoardReportController(BoardReportService service) {
		this.service = service;
	}
	
	@PostMapping("/boardreport")
    public void insertBoardReport(@RequestBody BoardReportDTO boardReportDTO) {
		service.insertBoardReport(boardReportDTO.getPost(), boardReportDTO.getReporter(), boardReportDTO.getReason(), boardReportDTO.getOtherReason(), boardReportDTO.getProcessingStatus());
    }
	
	@PostMapping("/reviewreport")
    public void insertReviewReport(@RequestBody BoardReportDTO boardReportDTO) {
		service.insertReviewReport(boardReportDTO.getReview(), boardReportDTO.getReporter(), boardReportDTO.getReason(), boardReportDTO.getOtherReason(), boardReportDTO.getProcessingStatus());
    }
	
	@GetMapping("/boardreportlist")
    public List<BoardReportDTO> selectBoardReportlist(@RequestParam("num") int num) {
		return service.selectBoardReportlist(num);
    }
	
	@PostMapping("/reportprogress")
    public void updateBoardReport(@RequestBody BoardReportDTO boardReportDTO) {
		service.updateBoardReport(boardReportDTO.getId(), boardReportDTO.getProcessingStatus());
    }
}
