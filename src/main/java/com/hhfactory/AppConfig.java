package com.hhfactory;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;
import com.hhfactory.mapper.RestaurantDtoToEntityMapperConfig;
import com.hhfactory.mapper.RestaurantEntityToDtoMapperConfig;

@Configuration
public class AppConfig {
	@Autowired
	private RestaurantEntityToDtoMapperConfig restaurantMapperConfig;
	@Autowired
	private RestaurantDtoToEntityMapperConfig restaurantDtoToEntityMapperConfig;
	
	/**
	 * EntityからDtoにマッピングするクラス
	 * 各controllerでautowiredして使う想定
	 * @return
	 */
	@Bean
	ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(restaurantMapperConfig.restaurantEntityToDtoMap());
		mapper.addMappings(restaurantDtoToEntityMapperConfig.restaurantEntityToDtoMap());
		return mapper;
	}
	
	/**
	 * GoogleGeocoderAPI用Geocoderクラス
	 * @return
	 */
	@Bean
	Geocoder geoCoder(){
		return new Geocoder();
	}
	
	/**
	 * GoogleGeocoderAPI用GeocoderRequestクラス
	 * 言語を日本語に設定済み
	 * @return
	 */
	@Bean
	GeocoderRequest geocoderRequest() {
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder().getGeocoderRequest();
		geocoderRequest.setLanguage("ja");
		return geocoderRequest;
	}
	
}
