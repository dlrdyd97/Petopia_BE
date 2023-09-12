package com.mysite.Petopia.Board.Notice;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface NoticeRepository extends JpaRepository<NoticeDTO, Long> {
	
	public Optional<NoticeDTO> findById(Long id);
	
	// 공지사항 조회수, 추천, 좋아요
	@Modifying
	@Transactional
	@Query(value="update notice set views = views + 1 where id = :id", nativeQuery=true)
	public void updateNoticeView(Long id);

	@Modifying
	@Transactional
	@Query(value="update notice set recommends = recommends + 1 where id = :id", nativeQuery=true)
	public void updateNoticeRecommends(Long id);
	
	@Modifying
	@Transactional
	@Query(value="update notice set recommends = recommends - 1 where id = :id", nativeQuery=true)
	public void deleteNoticeRecommend(Long id);

	@Modifying
	@Transactional
	@Query(value="update notice set likes = likes + 1 where id = :id", nativeQuery=true)
	public void updateNoticeInterest(Long id);

	@Modifying
	@Transactional
	@Query(value="update notice set likes = likes - 1 where id = :id", nativeQuery=true)
	public void deleteNoticeInterest(Long id);
	
}
