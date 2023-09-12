package com.mysite.Petopia.MapReview;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MapReviewController {

	private MapReviewServiceImpl mapReviewServiceImpl;

	Long reviewid;

	@Value("${spring.servlet.multipart.location}")
	String uploadDir;

	public MapReviewController(MapReviewServiceImpl mapReviewServiceImpl) {
		this.mapReviewServiceImpl = mapReviewServiceImpl;
	}

	@RequestMapping("/mapReviewWrite")
	public void insertinquiry(@RequestBody MapReviewDTO mapReviewDTO) {
		reviewid = mapReviewServiceImpl.insertReview(mapReviewDTO);
	}

	@ResponseBody
	@RequestMapping("/mapReviewImgUpload")
	public void upload(@RequestParam MultipartFile[] uploadfiles) throws IOException {
		for (MultipartFile file : uploadfiles) {
			if (!file.isEmpty()) {
				File storedFilename = new File(UUID.randomUUID().toString());
				file.transferTo(storedFilename);
				mapReviewServiceImpl.reviewimgupload(storedFilename.toString(), reviewid);
			}
		}
	}

	@RequestMapping("/mapReviewList")
	public List<MapReviewDTO> mapReviewList(@RequestParam("id") Long id, @RequestParam("num") int num) {
		return mapReviewServiceImpl.reviewList(id, num);
	}

	@RequestMapping("/mapImgList")
	public List<ReviewImgDTO> mapImgList(@RequestParam("id") Long id) {
		return mapReviewServiceImpl.reviewImgList(id);
	}

	@RequestMapping("/mapReportImg")
	public List<ReviewImgDTO> mapReportImg(@RequestParam("id") Long id) {
		return mapReviewServiceImpl.reviewReportImg(id);
	}

	@RequestMapping("/mapReviewCount")
	public int mapReviewCountByWriterEmail(@RequestParam("email") String email) {
		return mapReviewServiceImpl.countByWriterEmail(email);
	}
	
	@RequestMapping("/reportprocess")
	public void reviewReportProcess(@RequestBody MapReviewDTO mapReviewDTO) {
		mapReviewServiceImpl.incrementReportStatus(mapReviewDTO.getId());
	}

}