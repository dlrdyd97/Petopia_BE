package com.mysite.Petopia.Board.Notice;

import java.time.LocalDateTime;

import com.mysite.Petopia.Users.UsersDTO;

public interface NoticeBoardInfoMapping {
	Long getId();
    String getTitle();
    LocalDateTime getCreatedAt();
    UsersDTO getAuthor();
}
