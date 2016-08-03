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
	private String name;
	private String address;
	private String lunchTime;
	private String dinnerTime;
	private String holiday;
	private String otherInfo;
	private List<RestaurantImageDto> images;
	private List<RestaurantCommentDto> comments;
}
