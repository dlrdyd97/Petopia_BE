package com.mysite.Petopia.AdminPage;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class StatisticsDTO {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private StatisticCategory category;

    @Column(name = "views", nullable = false)
    private int views;

    @Column(name = "registrations", nullable = false)
    private int registrations;

    @Column(name = "post_uploads", nullable = false)
    private int postUploads;

    @Column(name = "comment_uploads", nullable = false)
    private int commentUploads;

    @Column(name = "date_recorded", nullable = false)
    private Date dateRecorded;
    
    public enum StatisticCategory {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }
}
