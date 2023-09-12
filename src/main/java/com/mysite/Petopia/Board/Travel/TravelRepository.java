package com.mysite.Petopia.Board.Travel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysite.Petopia.Board.Travel.TravelBoardDTO.Category;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.transaction.Transactional;

public interface TravelRepository extends JpaRepository<TravelBoardDTO, Integer>{
	
	public TravelBoardDTO findByPost_id(Long post_id);

	public void deleteByPost_id(Long id);
	
}
