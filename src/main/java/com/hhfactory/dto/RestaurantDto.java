package com.hhfactory.dto;

import java.util.List;

import org.springframework.data.geo.Point;

import lombok.Data;

/**
 * レストランEntity返却用DTO
 *
 */
@Data
public class RestaurantDto {
	private Long restaurantId;
	private String name;
	private String status;
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
	private String foreingMenuType;
	private boolean hasWifi;
	private boolean isCreditUse;
	private List<String> creditCartList;
	private List<String> insideImageUrls;
	private List<String> outsideImageUrls;
	private List<RestaurantCommentDto> comments;
	private List<MenuDto> menus; 
}
