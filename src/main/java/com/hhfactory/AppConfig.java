package com.hhfactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;
import com.hhfactory.mapper.ToMenuDtoMapperConfig;
import com.hhfactory.mapper.ToMenuEntityMapperConfig;
import com.hhfactory.mapper.ToRestaurantCommentEntityMapperConfig;
import com.hhfactory.mapper.ToRestaurantDtoMapperConfig;
import com.hhfactory.mapper.ToRestaurantEntityMapperConfig;

@Configuration
public class AppConfig {
	@Autowired
	private ToRestaurantDtoMapperConfig restaurantMapperConfig;
	@Autowired
	private ToRestaurantEntityMapperConfig restaurantDtoToEntityMapperConfig;
	@Autowired
	private ToRestaurantCommentEntityMapperConfig toRestaurantCommentEntityMapperConfig;
	@Autowired
	private ToMenuDtoMapperConfig toMenuDtoMapperConfig;
	@Autowired
	private ToMenuEntityMapperConfig toMenuEntityMapperConfig;

	/**
	 * EntityからDtoにマッピングするクラス
	 * 各controllerでautowiredして使う想定
	 * 
	 */
	@Bean
	ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(restaurantMapperConfig.restaurantEntityToDtoMap());
		mapper.addMappings(restaurantDtoToEntityMapperConfig.restaurantEntityToDtoMap());
		mapper.addMappings(toRestaurantCommentEntityMapperConfig.toRestaurantCommentEntityMap());
		mapper.addMappings(toMenuDtoMapperConfig.menuEntityToDtoMap());
		mapper.addMappings(toMenuEntityMapperConfig.toMenuEntityPropertyMap());
		return mapper;
	}

	/**
	 * GoogleGeocoderAPI用Geocoderクラス
	 * 
	 */
	@Bean
	Geocoder geoCoder() {
		return new Geocoder();
	}

	/**
	 * GoogleGeocoderAPI用GeocoderRequestクラス
	 * 言語を日本語に設定済み
	 * 
	 */
	@Bean
	GeocoderRequest geocoderRequest() {
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().getGeocoderRequest();
		geocoderRequest.setLanguage("ja");
		return geocoderRequest;
	}
}
