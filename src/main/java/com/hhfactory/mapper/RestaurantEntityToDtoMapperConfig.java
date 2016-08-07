package com.hhfactory.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.entity.RestaurantEntity;

/**
 * RestaurantEntityからRestaurantDtoへのマッピング定義クラス
 *
 */
@Configuration
public class RestaurantEntityToDtoMapperConfig {
	
	/*
	 * RestaurantEntityからRestaurantDtoへのマッピング定義
	 * (ModelMapperで自動マッピングできないものを定義)
	 * 各controllerでautowiredして使う想定
	 */
	@Bean
	PropertyMap<RestaurantEntity, RestaurantDto> restaurantEntityToDtoMap() {
		return new PropertyMap<RestaurantEntity, RestaurantDto>() {			
			@Override
			protected void configure() {
				using(lunchTimeConverter).map(source).setLunchTime(null);
				using(dinnerTimeConverter).map(source).setDinnerTime(null);
				map().setHoliday(source.getHolidayCode());
				map().setSmokingType(source.getSmokingTypeCode());
			}
		};
	}
	
	/**
	 * RestaurantEntityのランチタイムを結合するConverter
	 */
	Converter<RestaurantEntity, String> lunchTimeConverter = new AbstractConverter<RestaurantEntity, String>() {
		@Override
		protected String convert(RestaurantEntity source) {
			return source == null? null : source.getLunchTimeFrom() + "~" + source.getLunchTimeTo();
		}
	};
	
	/**
	 * RestaurantEntityのディナータイムを結合するConverter
	 */
	Converter<RestaurantEntity, String> dinnerTimeConverter = new AbstractConverter<RestaurantEntity, String>() {
		@Override
		protected String convert(RestaurantEntity source) {
			return source == null? null : source.getDinnerTimeFrom() + "~" + source.getDinnerTimeTo();
		}
	};
}
