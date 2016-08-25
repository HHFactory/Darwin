package com.hhfactory.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * レストランコメント用DTO
 *
 */
@Data
@ToString
public class RestaurantCommentDto {
	private String comment;
	private List<String> imageUrls;
	private long userId;
}
