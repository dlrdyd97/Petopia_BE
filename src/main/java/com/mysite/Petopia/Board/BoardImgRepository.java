package com.mysite.Petopia.Board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardImgRepository extends JpaRepository<BoardImgDTO, Long>{

	List<BoardImgDTO> findAllByPost_id(Long post_id);

	void deleteByPost_id(Long id);

	

	

}
