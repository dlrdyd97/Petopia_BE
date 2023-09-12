package com.mysite.Petopia.UserMypage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface UserRecommendRepository extends JpaRepository<UserRecommendDTO, Long> {

	public Boolean existsByPost_idAndUser_email(Long post_id, String user_email);

	public void deleteByPost_idAndUser_email(Long post_id, String user_email);

	public void deleteByPost_id(Long id);

	public void deleteByUser_email(String email);

	public void deleteAllByUser_email(String email);

	public List<UserRecommendDTO> findAllByUser_email(String user_email);
}
