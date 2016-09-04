package com.hhfactory.mapper;

import java.nio.ByteBuffer;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;

import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.entity.RestaurantEntity;

/**
 * RestaurantEntityからRestaurantDtoへのマッピング定義クラス
 *
 */
@Component
public class ToRestaurantDtoMapperConfig {

	/*
	 * RestaurantEntityからRestaurantDtoへのマッピング定義
	 * (ModelMapperで自動マッピングできないものを定義)
	 * 
	 */
	public PropertyMap<RestaurantEntity, RestaurantDto> restaurantEntityToDtoMap() {
		return new PropertyMap<RestaurantEntity, RestaurantDto>() {
			@Override
			protected void configure() {
				using(latLngConverter).map(source).setLatLng(null);
				map().setHoliday(source.getHolidayCode());// TODO:コード定義を正しく
				map().setSmokingType(source.getSmokingTypeCode());// TODO:コード定義を正しく
			}
		};
	}

	/**
	 * RestaurantEntityの経度緯度をPointクラスに変換するconverter
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

}
