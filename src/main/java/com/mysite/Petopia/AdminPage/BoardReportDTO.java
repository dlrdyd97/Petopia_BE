package com.mysite.Petopia.AdminPage;

import java.time.LocalDateTime;

import com.mysite.Petopia.Board.BoardDTO;
import com.mysite.Petopia.MapReview.MapReviewDTO;
import com.mysite.Petopia.Users.UsersDTO;

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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "post_reports")
public class BoardReportDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_post_reports_post_id"))
    private BoardDTO post;
    
    @ManyToOne
    @JoinColumn(name = "map_review_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_post_reports_map_review_id"))
    private MapReviewDTO review;

    @Column(name = "report_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime reportDate;

    @ManyToOne
    @JoinColumn(name = "reporter_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_post_reports_reporter_email"))
    private UsersDTO reporter;

    @Enumerated(EnumType.STRING)
    @Column(name = "reason")
    private ReportReason reason;

    @Column(name = "other_reason")
    private String otherReason;

    @Enumerated(EnumType.STRING)
    @Column(name = "processing_status")
    private ProcessingStatus processingStatus;

    public enum ReportReason {
        DISGUST,
        ADVERTISEMENT,
        INAPPROPRIATE_NICKNAME,
        COPYRIGHT,
        OTHER
    }

    public enum ProcessingStatus {
        PROCEEDING,
        PROGRESS_COMPLETE
    }
}
