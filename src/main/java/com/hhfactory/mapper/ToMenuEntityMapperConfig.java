package com.hhfactory.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhfactory.dto.MenuDto;
import com.hhfactory.entity.MenuEntity;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.service.implement.RestaurantServiceImpl;

import lombok.NonNull;

/**
 * 
 *
 */
@Component
public class ToMenuEntityMapperConfig {
	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;
	
	
	/**
	 * 
	 * @return
	 */
	public PropertyMap<MenuDto, MenuEntity> toMenuEntityPropertyMap() {
		return new PropertyMap<MenuDto, MenuEntity>() {
			@Override
			protected void configure() {
				skip().setId(null);
				using(restaurantEntityConveter).map(source).setRestaurant(null);
			}
		};
	}
	
	
	/**
	 * 
	 */
	private Converter<MenuDto, RestaurantEntity> restaurantEntityConveter = new AbstractConverter<MenuDto, RestaurantEntity>() {
		@Override
		protected RestaurantEntity convert(@NonNull MenuDto source) {
			return restaurantServiceImpl.findRestaurantById(source.getRestaurantId());
		}
	};
	
	
	
	
}
