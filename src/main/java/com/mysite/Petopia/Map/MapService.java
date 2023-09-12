package com.mysite.Petopia.Map;

import java.util.List;

public interface MapService {

	public List<MapDTO> getmaplist();

	public MapDTO findByLatAndLng(Double lat, Double lng);
	
	public MapDTO findplace(Long id);
	
}
