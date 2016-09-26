package com.hhfactory.mapper;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.entity.RestaurantInsideImageEntity;

/**
 * RestaurantEntityからRestaurantDtoへのマッピング定義クラス
 *
 */
@Component
public class ToRestaurantDtoMapperConfig {

	/*
	 * RestaurantEntityからRestaurantDtoへのマッピング定義<br>
	 * (ModelMapperで自動マッピングできないものを定義)<br>
	 * 
	 */
	public PropertyMap<RestaurantEntity, RestaurantDto> restaurantEntityToDtoMap() {
		return new PropertyMap<RestaurantEntity, RestaurantDto>() {
			@Override
			protected void configure() {
				using(latLngConverter).map(source).setLatLng(null);
				using(urlListConverter).map(source).setInsideImageUrls(null);
				map().setHoliday(source.getHolidayCode());
				map().setSmokingType(source.getSmokingTypeCode());
			}
		};
	}

	/**
	 * RestaurantEntityの経度緯度をPointクラスに変換するconverter<br>
	 * 
	 */
	private Converter<RestaurantEntity, Point> latLngConverter = new AbstractConverter<RestaurantEntity, Point>() {
		@Override
		protected Point convert(RestaurantEntity source) {
			if ( source != null ) {
				// 経度・緯度情報を格納するために8バイト確保する
				ByteBuffer buffer = ByteBuffer.allocate(8);
				// 緯度をdouble型に変換する
				double lat = ByteConverter.getReversedData(buffer, source.getLatLng(), 9);
				// 経度をdouble型に変換する
				double lng = ByteConverter.getReversedData(buffer, source.getLatLng(), 17);
				return new Point(lat, lng);
			}
			return null;
		}
	};
	
	private Converter<RestaurantEntity, List<String>> urlListConverter = new AbstractConverter<RestaurantEntity, List<String>>() {

		@Override
		protected List<String> convert(RestaurantEntity source) {
			if ( CollectionUtils.isEmpty(source.getInsideImages()) ) {
				return Collections.emptyList();
			}
			List<String> urlList = new ArrayList<>();
			for ( RestaurantInsideImageEntity entity : source.getInsideImages() ) {
				urlList.add(entity.getImgUrl());
			}
			return urlList;
		}
		
	};

}
