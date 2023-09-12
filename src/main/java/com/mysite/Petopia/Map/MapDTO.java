package com.mysite.Petopia.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "place_info")
public class MapDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "facility_name")
    private String facility_name;

    @Column(name = "category1")
    private String category1;

    @Column(name = "category2")
    private String category2;

    @Column(name = "category3")
    private String category3;

    @Column(name = "city_name")
    private String city_name;

    @Column(name = "county_name")
    private String county_name;

    @Column(name = "town_name")
    private String town_name;

    @Column(name = "village_name")
    private String village_name;

    @Column(name = "address_number")
    private String address_number;

    @Column(name = "road_name")
    private String road_name;

    @Column(name = "building_number")
    private String building_number;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "postal_code")
    private Integer postal_code;

    @Column(name = "road_address")
    private String road_address;

    @Column(name = "lot_address")
    private String lot_address;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "holiday")
    private String holiday;

    @Column(name = "operation_time")
    private String operation_time;

    @Column(name = "parking_info")
    private String parking_info;

    @Column(name = "admission_price_info")
    private String admission_price_info;

    @Column(name = "pet_companion_info")
    private String pet_companion_info;

    @Column(name = "pet_only_info")
    private String pet_only_info;

    @Column(name = "animal_size_info")
    private String animal_size_info;

    @Column(name = "pet_restriction_info")
    private String pet_restriction_info;

    @Column(name = "indoor_facility_info")
    private String indoor_facility_info;

    @Column(name = "outdoor_facility_info")
    private String outdoor_facility_info;

    @Column(name = "place_description")
    private String place_description;

    @Column(name = "pet_companion_additional_fee")
    private String pet_companion_additional_fee;

    @Column(name = "last_updated_date")
    private String last_updated_date;
}
