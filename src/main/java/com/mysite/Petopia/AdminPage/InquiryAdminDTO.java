package com.mysite.Petopia.AdminPage;

import com.mysite.Petopia.UserMypage.Inquiry.InquiryDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inquiry_management")
public class InquiryAdminDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "inquiry_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_inquiry_management_inquiry_id"))
    private InquiryDTO inquiry;

    @Column(name = "answer_content")
    private String answerContent;
}
