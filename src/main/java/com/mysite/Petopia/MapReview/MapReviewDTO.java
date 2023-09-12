package com.mysite.Petopia.MapReview;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.Petopia.Map.MapDTO;
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
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class MapReviewDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "writer_email", referencedColumnName = "email", foreignKey = @ForeignKey(name = "FK_reviews_writer_email"))
    private UsersDTO writer;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "content")
    private String content;
    
    @Column(name = "medical_cost")
    private Integer medicalCost;
    
    @Column(name = "surgery_cost")
    private Integer surgeryCost;

    @Column(name = "cost", nullable = false)
    private Integer cost;
    
    @Column(name = "report_count", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int reportCount;
    
    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_reviews_location_id"))
    private MapDTO location;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_level", nullable = false)
    private PriceLevel priceLevel;
    
    @Column(name = "report_status", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int reportStatus;
    
	@Transient
	private String username;
	
	@Transient
	private Long mapid;
	
    
    public enum PriceLevel {
        CHEAP,
        MODERATE,
        EXPENSIVE
    }
}