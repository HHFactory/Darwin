package com.hhfactory.dto;

import java.util.List;

import lombok.Data;
import lombok.ToString;

/**
 * メニュー返却用DTO
 *
 */
@Data
@ToString
public class MenuDto {
	private Long id;
	private String name;
	private Integer price;
	private Long restaurantId;
	private String imageUrl;
	private List<Long> menuCommentIdList;
}
