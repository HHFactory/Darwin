package com.hhfactory.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.google.code.geocoder.model.LatLng;
import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.geocode.GeoCoderService;

/**
 * RestaurantDtoからRestaurantEntityへのマッピング定義クラス
 *
 */
@Configuration
public class ToRestaurantEntityMapperConfig {
	@Autowired
	private GeoCoderService coderService;

	/**
	 * RestaurantDtoからRestaurantEntityへのマッピング定義
	 * 
	 */
	public PropertyMap<RestaurantDto, RestaurantEntity> restaurantEntityToDtoMap() {
		return new PropertyMap<RestaurantDto, RestaurantEntity>() {
			@Override
			protected void configure() {
				using(latLngConverter).map(source).setLatLng(null);
				map().setHasWifiCode(source.getHasWifiType());
				map().setHolidayCode(source.getHoliday());
				map().setSmokingTypeCode(source.getSmokingType());
				map().setPrefectureCode(source.getPrefecture());
				map().setStatus(source.getStatus());
			}
		};
	}

	/**
	 * 住所から取得した経度、緯度をbyte型配列に変換するconverter<br>
	 * 
	 */
	private Converter<RestaurantDto, byte[]> latLngConverter = new AbstractConverter<RestaurantDto, byte[]>() {
		@Override
		protected byte[] convert(RestaurantDto source) {
			// google geocoderから緯度経度情報を取得する
			LatLng targetLocation = coderService.getLatLngByAddress(source.getCity() + source.getAddress());
			// 取得した経度・緯度をMysql登録用のbyte配列に変換する
			return ByteConverter.convertToGeometoryData(targetLocation);
		}
	};
	
}
