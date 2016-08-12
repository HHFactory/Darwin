package com.hhfactory.mapper;

import java.io.IOException;

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
public class RestaurantDtoToEntityMapperConfig {
	@Autowired
	private GeoCoderService coderService;
	
	/**
	 * RestaurantDtoからRestaurantEntityへのマッピング定義
	 * 
	 * @return
	 */
	public PropertyMap<RestaurantDto, RestaurantEntity> restaurantEntityToDtoMap() {
		return new PropertyMap<RestaurantDto, RestaurantEntity>() {			
			@Override
			protected void configure() {
				using(latLngConverter).map(source).setLatLng(null);
				map().setHasWifiCode(source.getHasWifiType());// TODO:コード定義から取得する		
				map().setHolidayCode(source.getHoliday());// TODO:コード定義から取得する
				map().setSmokingTypeCode(source.getSmokingType());// TODO:コード定義から取得する
				map().setPrefectureCode(source.getPrefecture());// TODO:コード定義から取得する
				map().setStatus("open");// TODO:コード定義から取得する
			}
		};
	}
	
	/**
	 * 住所から取得した経度、緯度をbyte型配列に変換するconverter
	 * TODO:適切なエラー処理にする
	 */
	private Converter<RestaurantDto, byte[]> latLngConverter = new AbstractConverter<RestaurantDto, byte[]>() {
		@Override
		protected byte[] convert(RestaurantDto source) {
			LatLng targetLocation;
			try {
				// 住所から経度・緯度を取得する
				targetLocation = coderService.getLatLngByAddress(source.getCity() + source.getAddress());
				// 取得した経度・緯度をMysql登録用のbyte配列に変換する
				return ByteConverter.convertToGeometoryData(targetLocation);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	};
		
}
