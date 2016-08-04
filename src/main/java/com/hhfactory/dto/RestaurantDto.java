package com.hhfactory.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * レストランEntity返却用DTO
 *
 */
@Data
@Component
public class RestaurantDto {
	private Long restaurantId;
	private String name;
	private String postNum;
	private String prefecture;
	private String city;
	private String address;
	private String building;
	private String lunchTime;
	private String dinnerTime;
	private String holiday;
	private String seatCounts;
	private String smokingType;
	private String hasWiFi;
	private List<RestaurantImageDto> images;
	private List<RestaurantCommentDto> comments;
}
