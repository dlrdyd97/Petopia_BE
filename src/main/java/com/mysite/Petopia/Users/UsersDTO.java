package com.mysite.Petopia.Users;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UsersDTO {
    @Id
    @Column(name = "email", length = 255)
    private String email;

    @Column(length = 10, nullable = false)
    private String provider;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50)
    private String name;

    @Column(length = 15, nullable = false)
    private String nickname;

    @Column
    private LocalDate birthday;

    @Column(length = 255, nullable = false)
    private String profileImage;

    @Column(name = "joined_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime joinedAt;

    @Column(length = 255)
    private String adminMemo;

}
