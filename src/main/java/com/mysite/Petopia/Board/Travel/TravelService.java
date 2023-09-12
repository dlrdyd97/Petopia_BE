package com.mysite.Petopia.Board.Travel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Board.Travel.TravelBoardDTO.Category;

import jakarta.transaction.Transactional;


@Service
public class TravelService {

	private TravelRepository repository;

	public TravelService(TravelRepository repository) {
		this.repository = repository;
	}

	public void insertTravelBoardInfo(BoardDTO boardDTO, String placename, Category category,
			String petProvisions) {
		TravelBoardDTO travelboard = new TravelBoardDTO();
		travelboard.setPost(boardDTO);
		travelboard.setPlaceName(placename);
		travelboard.setCategory(category);
		travelboard.setPetProvisions(petProvisions);
		repository.save(travelboard);
	}
	
	public TravelBoardDTO selectTravelBoardInfo(Long post_id) {
		return repository.findByPost_id(Long.valueOf(post_id));
	}
	
	@Transactional
	public void updateTravelBoardInfo (BoardDTO boardDTO, String placename, Category category,
			String petProvisions) {
		repository.deleteByPost_id(boardDTO.getId());
		TravelBoardDTO travelboard = new TravelBoardDTO();
		travelboard.setPost(boardDTO);
		travelboard.setPlaceName(placename);
		travelboard.setCategory(category);
		travelboard.setPetProvisions(petProvisions);
		repository.save(travelboard);
	}
	
}
