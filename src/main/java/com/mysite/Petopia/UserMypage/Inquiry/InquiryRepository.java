package com.mysite.Petopia.UserMypage.Inquiry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mysite.Petopia.Users.UsersDTO;

public interface InquiryRepository extends JpaRepository<InquiryDTO, Long> {

	List<InquiryDTO> findByUser(UsersDTO user);

	void deleteByUser_email(String user_email);

	int countByUserEmail(String UserEmail);
}
