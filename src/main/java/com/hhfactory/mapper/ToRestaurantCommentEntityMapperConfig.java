package com.hhfactory.mapper;

import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import com.hhfactory.dto.RestaurantCommentDto;
import com.hhfactory.entity.RestaurantCommentEntity;

/**
 * RestaurantCommentEntityへのマッピング定義クラス
 *
 */
@Component
public class ToRestaurantCommentEntityMapperConfig {

	/**
	 * RestaurantCommentDtoからRestaurantCommentEntityへのマッピング定義
	 * 
	 * @return properyMap
	 */
	public PropertyMap<RestaurantCommentDto, RestaurantCommentEntity> toRestaurantCommentEntityMap() {
		return new PropertyMap<RestaurantCommentDto, RestaurantCommentEntity>() {
			@Override
			protected void configure() {
			}
		};
	}

}
