package com.mysite.Petopia.MapReview;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysite.Petopia.Map.MapDTO;

import jakarta.transaction.Transactional;

public interface MapReviewRepository extends JpaRepository<MapReviewDTO, Long> {

	List<MapReviewDTO> findByLocation(MapDTO mapdto);

	List<MapReviewDTO> findByLocationOrderByUpdatedAtDesc(MapDTO mapdto);

	List<MapReviewDTO> findByLocationOrderByRatingDescUpdatedAtDesc(MapDTO mapdto);
	
	List<MapReviewDTO> findByLocationOrderByRatingAscUpdatedAtDesc(MapDTO mapdto);

	@Modifying
	@Transactional
	@Query(value="update reviews set report_count = report_count + 1 where id = :id", nativeQuery=true)
	public void reportReview(Long id);

	void deleteByWriter_email(String writer_email);


	@Modifying
	@Transactional
	@Query(value="update reviews set report_count = report_count - 1 where id = :id", nativeQuery=true)
	void deleteReportReview(Long id);

	List<MapReviewDTO> getByWriter_email(String writer_email);
	
	int countByWriterEmail(String writerEmail);
	
	@Modifying
	@Transactional
    @Query(value="UPDATE reviews SET report_status = report_status + 1 WHERE id = :reviewId", nativeQuery=true)
    void incrementReportStatus(@Param("reviewId") Long reviewId);

}