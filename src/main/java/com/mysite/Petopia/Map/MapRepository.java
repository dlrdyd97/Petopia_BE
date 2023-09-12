package com.mysite.Petopia.Map;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MapRepository extends JpaRepository<MapDTO, Long> {

	public Optional<MapDTO> findByLatAndLng(Double lat, Double lng);
	
}
