package com.mysite.Petopia.Board.Notice;

import java.time.LocalDateTime;

import com.mysite.Petopia.Users.UsersDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notice_interests")
public class NoticeInterestDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
	@JoinColumn(name = "user_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_notice_interests_user_email"))
    private UsersDTO admin;

    @ManyToOne
    @JoinColumn(name = "notice_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_notice_image_id"))
    private NoticeDTO noticeId;

    @Column(name = "clicked_at", nullable = false)
    private LocalDateTime clickedAt;
}