package com.mysite.Petopia.UserMypage.Review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mysite.Petopia.MapReview.MapReviewDTO;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.transaction.Transactional;

public interface ReviewRepository extends JpaRepository<MapReviewDTO, Long> {
	
	List<MapReviewDTO> findByWriterOrderByUpdatedAtDesc(UsersDTO writer);
	
	@Modifying
	@Transactional
    @Query(value = "delete from reviews where id IN :id", nativeQuery = true)
    void deleteById(@Param("id") List<Long> id);
	
	void deleteByWriter_email(String writer_email);

}
