package com.mysite.Petopia.Board.Travel;

import com.mysite.Petopia.Board.BoardDTO;

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
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "travel_info")
public class TravelBoardDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_travel_info_posts"))
    private BoardDTO post;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Enumerated(EnumType.STRING)
    @Column(name = "category" ,nullable = false)
    private Category category;

    @Column(name = "description")
    private String description;

    //@Enumerated(EnumType.STRING)
    @Column(name = "pet_provisions", nullable = false)
    //private PetProvisions petProvisions;
    private String petProvisions;

    @Column(name = "pet_provisions_etc")
    private String petProvisionsEtc;


    public enum Category {
        RESTAURANT,
        CAFE,
        PARK,
        ACCOMMODATION,
        ETC
    }

//    public enum PetProvisions {
//        PET_SUPPLIES_PROVIDED,
//        PET_SNACK,
//        PET_MANNER_BELT,
//        NO_LARGE_DOG_ALLOWED,
//        ETC
//    }
}