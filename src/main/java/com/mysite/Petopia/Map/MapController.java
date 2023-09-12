package com.mysite.Petopia.Map;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MapController {

	private MapServiceImpl mapServiceImpl;

	public MapController(MapServiceImpl mapServiceImpl) {
		this.mapServiceImpl = mapServiceImpl;
	}

	@RequestMapping("/maplist")
	public List<MapDTO> mapServiceList() {
		return mapServiceImpl.getmaplist();
	}
//
//	@RequestMapping("/getplace")
//	public MapDTO mapPlace(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
//		return mapServiceImpl.findByLatAndLng(lat, lng);
//	}
	
	@RequestMapping("/getplace")
	public MapDTO mapPlace(@RequestParam("id") Long id) {
		return mapServiceImpl.findplace(id);
	}

}
