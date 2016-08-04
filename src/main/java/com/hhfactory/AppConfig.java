package com.hhfactory;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.entity.RestaurantEntity;

@Configuration
public class AppConfig {
	
	/**
	 * EntityからDtoにマッピングするクラス
	 * 各controllerでautowiredして使う想定
	 * @return
	 */
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	/*
	 * RestaurantEntityからRestaurantDtoへのマッピング定義
	 * (ModelMapperで自動マッピングできないものを定義)
	 * 各controllerでautowiredして使う想定
	 */
	@Bean
	PropertyMap<RestaurantEntity, RestaurantDto> restaurantMap() {
		return new PropertyMap<RestaurantEntity, RestaurantDto>() {			
			@Override
			protected void configure() {
				map().setLunchTime(source.getLunchTimeFrom());
				map().setDinnerTime(source.getDinnerTimeFrom());
				map().setHoliday(source.getHolidayCode());
				map().setSmokingType(source.getSmokingTypeCode());
			}
		};
	}


}
