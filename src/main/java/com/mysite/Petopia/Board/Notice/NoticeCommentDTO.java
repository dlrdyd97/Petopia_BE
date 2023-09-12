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
@Table(name = "notice_comments")
public class NoticeCommentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "notice_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_comments_notice_id"))
    private NoticeDTO notice;

    @ManyToOne
    @JoinColumn(name = "author_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_comments_notice_author_email"))
    private UsersDTO admin;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
