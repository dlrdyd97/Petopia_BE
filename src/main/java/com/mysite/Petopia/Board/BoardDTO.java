package com.mysite.Petopia.Board;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.mysite.Petopia.Board.Travel.TravelBoardDTO;
import com.mysite.Petopia.Users.UsersDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class BoardDTO implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_posts_author_email"))
    private UsersDTO author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "likes", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int likes;

    @Column(name = "recommends", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int recommends;

    @Column(name = "views", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int views;

    @Column(name = "thumbnail_image")
    private String thumbnailImage;

    @Column(name = "report_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int reportCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private BoardCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "sub_category")
    private BoardSubCategory subCategory;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;
    
    public enum BoardCategory {
    	FREE,
    	LOCAL,
    	TRAVEL
    }
    
    public enum BoardSubCategory {
    	SEOUL,
        GYEONGGI_N,
        GYEONGGI_S,
        INCHEON,
        GANGWON,
        CHUNGBUK,
        CHUNGNAM,
        GYEONGBUK,
        GYEONGNAM,
        JEONBUK,
        JEONNAM,
        GWANGJU,
        DAEGU,
        DAEJEON,
        SEJONG,
        BUSAN,
        ULSAN,
        JEJU
    }
    
}
