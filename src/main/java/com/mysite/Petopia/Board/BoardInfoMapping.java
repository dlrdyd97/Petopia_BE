package com.mysite.Petopia.Board;

import java.time.LocalDateTime;

import com.mysite.Petopia.Users.UsersDTO;

public interface BoardInfoMapping {
	Long getId();
    String getTitle();
    LocalDateTime getCreatedAt();
    UsersDTO getAuthor();
}
