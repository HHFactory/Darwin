package com.hhfactory.mapper;

import java.sql.Time;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
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
				map().setStatus(source.getStatus());
				map().setName(source.getName());
				map().setPrefecture(source.getPrefecture());
				map().setCity(source.getCity());
				map().setAddress(source.getAddress());
				map().setBuilding(source.getBuilding());
				using(latLngConverter).map(source).setLatLng(null);
				using(toTimeConverter).map(source.getLunchTimeFrom()).setLunchTimeFrom(null);
				using(toTimeConverter).map(source.getLunchTimeTo()).setLunchTimeTo(null);
				using(toTimeConverter).map(source.getDinnerTimeFrom()).setDinnerTimeFrom(null);
				using(toTimeConverter).map(source.getDinnerTimeTo()).setDinnerTimeTo(null);
				map().setSeatCounts(source.getSeatCounts());
				map().setHolidayCode(source.getHoliday());
				map().setSmokingTypeCode(source.getSmokingType());
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
	
	/**
	 * 日付からsql.Time型に変換する<br>
	 */
	private Converter<String, Time> toTimeConverter = new AbstractConverter<String, Time>() {
		@Override
		protected Time convert(String source) {
			if (StringUtils.isEmpty(source)) {
				return null;
			}
			return new Time(DateTimeFormat.forPattern("HH:mm").parseLocalTime(source).toDateTimeToday().getMillis());				
		}
	};
	
}
