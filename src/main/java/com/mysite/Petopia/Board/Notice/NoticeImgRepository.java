package com.mysite.Petopia.Board.Notice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeImgRepository extends JpaRepository<NoticeImageDTO, Long> {

	List<NoticeImageDTO> findAllByPost(NoticeDTO notice_id);

    void deleteByPost(NoticeDTO post);
}
