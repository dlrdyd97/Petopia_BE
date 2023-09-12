package com.mysite.Petopia.Board.Notice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.BoardImgDTO;
import com.mysite.Petopia.Board.BoardImgRepository;
import com.mysite.Petopia.Board.BoardRepository;
import com.mysite.Petopia.Board.BoardDTO.BoardCategory;
import com.mysite.Petopia.Board.Travel.TravelRepository;
import com.mysite.Petopia.UserMypage.UserInterestRepository;
import com.mysite.Petopia.UserMypage.UserRecommendRepository;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

@Service
public class NoticeService {
	private NoticeRepository repository;
	private NoticeImgRepository imgRepository;
	private UserRecommendRepository userRecommendRepository;
	private UserInterestRepository userInterestRepository;
	
	public NoticeService(NoticeRepository repository, NoticeImgRepository imgRepository, UserRecommendRepository userRecommendRepository, UserInterestRepository userInterestRepository) {
		this.repository = repository;
		this.imgRepository = imgRepository;
		this.userRecommendRepository = userRecommendRepository;
		this.userInterestRepository = userInterestRepository;
	}
	
	// 게시글 작성
	public Long insertNotice(UsersDTO usersDTO, String title, String content, String thumbnailImage) {
		NoticeDTO notice = new NoticeDTO();
		notice.setAdmin(usersDTO);
		notice.setTitle(title);
		notice.setContent(content);
		notice.setThumbnailImage(thumbnailImage);
		notice.setCreatedAt(LocalDateTime.now());
		notice.setUpdatedAt(LocalDateTime.now());
		return repository.save(notice).getId();
	}
	
	// 파일 업로드
	public void saveuploadfiles(String uploadfiles, NoticeDTO notice) {
		NoticeImageDTO imgs = new NoticeImageDTO();
		imgs.setImageUrl(uploadfiles);
		imgs.setPost(notice);
		imgRepository.save(imgs);
	};
	
	// 게시글 상세
	public Optional<NoticeDTO> selectNotice(Long id) {
		repository.updateNoticeView(id);
		return repository.findById(id);
	}
	
	public List<NoticeImageDTO> selectNoticeImg(NoticeDTO post) {
		return imgRepository.findAllByPost(post);
	}
	
	@Transactional
	// 게시글 삭제
	public void deleteNotice(Long id, NoticeDTO post) {
		userRecommendRepository.deleteByPost_id(id);
		userInterestRepository.deleteByPost_id(id);
		imgRepository.deleteByPost(post);
		repository.deleteById(id);
	}
	

}
