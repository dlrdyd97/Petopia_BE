package com.mysite.Petopia.Map;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {

	private MapRepository mapRepository;

	public MapServiceImpl(MapRepository mapRepository) {
		this.mapRepository = mapRepository;
	}

	@Override
	public List<MapDTO> getmaplist() {
		return mapRepository.findAll();
	}
	

	@Override
	public MapDTO findByLatAndLng(Double lat, Double lng) {
		MapDTO mapDTO=new MapDTO();
		Optional<MapDTO> dto = mapRepository.findByLatAndLng(lat, lng);
		if (dto.isPresent()) {
			mapDTO=dto.get();
		}
		return mapDTO;
	}
	
	@Override
	public MapDTO findplace(Long id) {
		MapDTO mapDTO=new MapDTO();
		Optional<MapDTO> dto = mapRepository.findById(id);
		if (dto.isPresent()) {
			mapDTO=dto.get();
		}
		return mapDTO;
	}

}
