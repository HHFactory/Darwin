package com.hhfactory.dto;

import lombok.Data;

/**
 * レストランコメント返却用DTO
 *
 */
@Data
public class RestaurantCommentDto {
	private String comment;
	private String userName;
}
