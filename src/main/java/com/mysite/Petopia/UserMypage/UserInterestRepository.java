package com.mysite.Petopia.UserMypage;

import java.text.AttributedString;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.Users.UsersDTO;

public interface UserInterestRepository extends JpaRepository<UserInterestDTO, Long> {

	public Boolean existsByPost_idAndUser_email(Long post_id, String user_email);

	public void deleteByPost_idAndUser_email(Long post_id, String user_email);

	public void deleteByPost_id(Long id);

	public int deleteByUser_email(String user_email);

	public List<UserInterestDTO> findAllByUser_email(String user_email);

	public List<UserInterestDTO> findByUser(UsersDTO userdto);

}
