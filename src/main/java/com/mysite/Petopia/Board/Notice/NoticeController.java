package com.mysite.Petopia.Board.Notice;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class NoticeController {
	
	private NoticeService noticeService;
	
	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}
	
	Long noticeid;
	String thumbnail = "";
	
	// 게시글 작성
	@RequestMapping("/notice/write")
	public Long insertTravelBoard(@RequestBody NoticeDTO notice) {
		thumbnail = UUID.randomUUID().toString().replaceAll("-", "")+".jpg";
		noticeid = noticeService.insertNotice(notice.getAdmin(), notice.getTitle(), notice.getContent(), thumbnail);
		return noticeid;
	}
	
	// 파일 업로드
	@RequestMapping("/notice/uploadfiles")
	public void uploadfiles(@RequestBody MultipartFile[] uploadfiles) throws IOException {
		for (MultipartFile file : uploadfiles) {
			if (thumbnail.length() != 0) {
				File storedFilename = new File(thumbnail);
				file.transferTo(storedFilename);
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setId(noticeid);
				noticeService.saveuploadfiles(storedFilename.toString(), noticeDTO);
				thumbnail = "";
				continue;
			}
			if (!file.isEmpty()) {
				File storedFilename = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
				file.transferTo(storedFilename);
				NoticeDTO noticeDTO = new NoticeDTO();
				noticeDTO.setId(noticeid);
				noticeService.saveuploadfiles(storedFilename.toString(), noticeDTO);
			}
		}
	}
	
	// 게시글 상세
	@PostMapping("/notice/detail")
	public Optional<NoticeDTO> selectNotice(@RequestBody NoticeDTO notice) {
		return noticeService.selectNotice(notice.getId());
	}
	
	@PostMapping("/notice/detailimg")
	public List<NoticeImageDTO> selectNoticeImg(@RequestBody NoticeImageDTO noticeimg) {
		return noticeService.selectNoticeImg(noticeimg.getPost());
	}
		
	//게시글 삭제
	@PostMapping("/notice/delete")
	public void deleteBoard(@RequestBody NoticeDTO notice) {
		noticeService.deleteNotice(notice.getId(), notice);
	}
	
}
