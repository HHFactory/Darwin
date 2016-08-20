package com.hhfactory.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hhfactory.dto.RestaurantCommentDto;
import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.dto.ResultDto;
import com.hhfactory.entity.RestaurantCommentEntity;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.service.implement.RestaurantServiceImpl;

/**
 * レストラン系処理を担当するコントローラ
 *
 */
@RestController
@RequestMapping(value = "/api/v1/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	
	/**
	 * 指定されたIDからレストラン情報を取得する
	 * @param restaurantId[Long]:レストランID
	 * @return result[ResultDto]:API処理結果
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResultDto findRestaurantById(@PathVariable Long restaurantId) {
		// RestaurantEntityを取得
		RestaurantEntity resultEntity = restaurantServiceImpl.findRestaurantById(restaurantId);
		ResultDto resultDto = beanFactory.createBean(ResultDto.class);
		if( resultEntity != null ) {
			// Entity -> DTOにつめかえる
			RestaurantDto restaurantDto = modelMapper.map(resultEntity, RestaurantDto.class);
			resultDto.setResult(restaurantDto);
		}
		return resultDto;
	}
	
	/**
	 * レストラン情報を登録する
	 * 登録済み店舗の場合、登録処理は行わない
	 * @param entity[RestaurantDto]:登録するレストラン情報
	 * @return
	 * @throws IOException 
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public void createRestaurant(@RequestBody RestaurantDto restaurantDto) throws IOException {
		// TODO: 登録済み店舗か判定する
		// DTOからEntityにマッピングする
		RestaurantEntity insertTargetEntity = modelMapper.map(restaurantDto, RestaurantEntity.class);
		restaurantServiceImpl.createRestaurant(insertTargetEntity);
	}
	
	/**
	 * 指定された緯度経度から1km以内の店舗情報を取得する
	 * @param lat[double]：緯度
	 * @param lng[double]：経度
	 * @return 取得した店舗情報リスト
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET, value = "")
	public ResultDto findNearbyRestaurants(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
		// ネイティブSQLを実行する
		List<RestaurantEntity> nearbyRestaurants = restaurantServiceImpl.findNearbyRestaurants(lat, lng);
		ResultDto resultDto = beanFactory.createBean(ResultDto.class);
		if( CollectionUtils.isNotEmpty(nearbyRestaurants) ) {
			// 取得したリストをDtoリストにマッピングする
			List<RestaurantDto> restaurantDtos = nearbyRestaurants.stream()
																.map( resultEntity -> modelMapper.map(resultEntity, RestaurantDto.class) )
																.collect(Collectors.toList());
			resultDto.setResult(restaurantDtos);
		}
		return resultDto;
	}
	
	/**
	 * 対象レストランへコメントを登録する
	 * @param restaurantId[Long]:レストランID
	 * @param comment[RestaurantCommentDto]:コメント内容
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value = "/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void commentOnRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantCommentDto commentDto) {
		// DtoからEntityに変換する
		RestaurantCommentEntity commentEntity = modelMapper.map(commentDto, RestaurantCommentEntity.class);
		restaurantServiceImpl.commentOnRestaurant(restaurantId, commentEntity);
	}
	
}
