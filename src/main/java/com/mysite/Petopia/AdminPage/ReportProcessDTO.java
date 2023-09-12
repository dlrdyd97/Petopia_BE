package com.mysite.Petopia.AdminPage;

import java.sql.Timestamp;

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
@Table(name = "report_processing")
public class ReportProcessDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_report_processing_report_id"))
    private BoardReportDTO report;

    @Column(name = "processing_date", nullable = false)
    private Timestamp processingDate;

    @Column(name = "processing_comment_title", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'This post has been reported'")
    private String processingCommentTitle;

    @Column(name = "processing_comment_text", nullable = false)
    private String processingCommentText;

    @ManyToOne
    @JoinColumn(name = "processed_by_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_report_processing_processed_by_email"))
    private UsersDTO processedBy;
}
