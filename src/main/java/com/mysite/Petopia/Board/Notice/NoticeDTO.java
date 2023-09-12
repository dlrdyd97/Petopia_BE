package com.mysite.Petopia.Board.Notice;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.mysite.Petopia.Users.UsersDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notice")
public class NoticeDTO implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_notice_author_email"))
    private UsersDTO admin;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "likes")
    private int likes;

    @Column(name = "recommends")
    private int recommends;

    @Column(name = "views")
    private int views;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
