package com.hhfactory;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocoderRequest;

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
