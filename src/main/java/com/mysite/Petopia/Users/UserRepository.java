package com.mysite.Petopia.Users;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;

public interface UserRepository extends JpaRepository<UsersDTO, String> {

	@Modifying
	@Transactional
	@Query(value = "insert into users(email, provider, password, name, nickname, birthday, profileImage) values(:email, :provider, :password, :name, :nickname, :birthday, :profileImage)", nativeQuery = true)
	void insertUserPetopia(String email, String provider, String password, String name, String nickname,
			LocalDate birthday, String profileImage);

	@Modifying
	@Transactional
	@Query(value = "insert into users(email, provider, password, nickname, profileImage) values(:email, :provider, :password, :nickname, :profileImage)", nativeQuery = true)
	void insertUserKakao(String email, String provider, String password, String nickname, String profileImage);

	@Modifying
	@Transactional
	@Query(value = "insert into users(email, provider, password, name, nickname, profileImage) values(:email, :provider, :password, :name, :nickname, :profileImage)", nativeQuery = true)
	void insertUserNaver(String email, String provider, String password, String name, String nickname,
			String profileImage);

	UsersDTO findByEmailAndPassword(String email, String password);

	List<UsersDTO> findByNameAndPasswordAndBirthday(String name, String password, LocalDate birthday);

	UsersDTO findByEmailAndNameAndBirthday(String email, String name, LocalDate birthday);

	@Modifying
	@Transactional
	@Query(value = "update users set name = :name, nickname = :nickname, password = :password where email = :email", nativeQuery = true)
	void updateUserInfo(String email, String name, String nickname, String password);

	@Modifying
	@Transactional
	@Query(value = "update users set nickname = :nickname where email = :email", nativeQuery = true)
	void updateNickname(String email, String nickname);
	
	@Modifying
	@Transactional
	@Query(value = "update users set profileImage = :profileImage where email = :email", nativeQuery = true)
	void updateProFileImg(String email, String profileImage);
	
	@Modifying
	@Transactional
	void deleteByEmail(String email);
}
