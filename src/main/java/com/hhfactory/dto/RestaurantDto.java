package com.hhfactory.dto;

import java.util.List;

import org.springframework.data.geo.Point;

import com.hhfactory.entity.FoodCategory;

import lombok.Data;

/**
 * レストランEntity返却用DTO
 *
 */
@Data
public class RestaurantDto {
	private Long restaurantId;
	private FoodCategory foodCategory;
	private String name;
	private String postNum;
	private String prefecture;
	private String city;
	private String address;	
	private String building;
	private String lunchTimeFrom;
	private String lunchTimeTo;
	private String dinnerTimeFrom;
	private String dinnerTimeTo;
	private Point latLng;
	private String holiday;
	private String seatCounts;
	private String smokingType;
	private String hasWifiType;
	private List<RestaurantImageDto> images;
	private List<RestaurantCommentDto> comments;
}
