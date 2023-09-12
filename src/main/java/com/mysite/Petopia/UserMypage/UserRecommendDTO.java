package com.mysite.Petopia.UserMypage;

import java.time.LocalDateTime;

import com.mysite.Petopia.Board.BoardDTO;
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
@Table(name = "recommends")
public class UserRecommendDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_recommends_user_email"))
    private UsersDTO user;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_recommends_post_id"))
    private BoardDTO post;

    @Column(name = "clicked_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime clickedAt;
}
