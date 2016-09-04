package com.hhfactory.dto;

import java.util.List;

import lombok.Data;

/**
 * レストランコメント用DTO
 *
 */
@Data
public class RestaurantCommentDto {
	private String comment;
	private List<String> imageUrls;
	private long userId;
}
