package com.hhfactory.dto;

import java.util.List;

import lombok.Data;

/**
 * メニュー返却用DTO
 *
 */
@Data
public class MenuDto {
	private Long id;
	private String name;
	private Integer foodType;
	private Integer price;
	private Long restaurantId;
	private String imageUrl;
	private List<Long> menuCommentIdList;
}
