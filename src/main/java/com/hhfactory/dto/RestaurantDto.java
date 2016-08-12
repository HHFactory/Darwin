package com.hhfactory.dto;

import java.util.List;

import org.springframework.data.geo.Point;
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
	private String lunchTimeFrom;
	private String lunchTimeTo;
	private String dinnerTimeFrom;
	private String dinnerTimeTo;
	private Point latLng; //test用フィールド
	private String holiday;
	private String seatCounts;
	private String smokingType;
	private String hasWifiType;
	private List<RestaurantImageDto> images;
	private List<RestaurantCommentDto> comments;
}
