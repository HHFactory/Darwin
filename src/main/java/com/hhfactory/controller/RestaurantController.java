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
 * レストラン情報の取得、登録を行うRestContoller 各メソッド内で、サービス処理結果をResultDtoに詰めてクライアントに返す。
 * クライアントへはJSON形式で返す
 * 
 * ResultDtoはbeanFactoryを用いて生成する サービスで取得したEntityはDTOにマッピングしてから、ResultDtoに詰めている。
 * マッピングにはModelMapperを用いる
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
	 * 指定されたレストランIDからレストラン情報を1件取得する
	 * 
	 * @param restaurantId[Long]:レストランID
	 * @return result[ResultDto]:API処理結果
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/{restaurantId}")
	public ResultDto findRestaurantById(@PathVariable Long restaurantId) {
		// RestaurantEntityをサービスから取得する
		RestaurantEntity resultEntity = restaurantServiceImpl.findRestaurantById(restaurantId);
		ResultDto resultDto = beanFactory.createBean(ResultDto.class);
		if ( resultEntity != null ) {
			// EntityからDTOにつめかえる
			RestaurantDto restaurantDto = modelMapper.map(resultEntity, RestaurantDto.class);
			resultDto.setResult(restaurantDto);
		}
		return resultDto;
	}

	/**
	 * レストラン情報を登録する
	 * 
	 * 登録済み店舗か判定し、登録済みの場合は登録処理を行わない その際は、登録済みであるメッセージのみをクライアントに返す
	 * クライアントからはJSON形式でデータをもらう サービス処理のため、DTOからEntityにマッピングする
	 * 
	 * @param entity[RestaurantDto]:登録するレストラン情報
	 * @return HttpStatusを返す
	 * @throws IOException
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public void createRestaurant(@RequestBody RestaurantDto restaurantDto) throws IOException {
		// DTOからEntityにマッピングする
		RestaurantEntity insertTargetEntity = modelMapper.map(restaurantDto, RestaurantEntity.class);
		restaurantServiceImpl.createRestaurant(insertTargetEntity);
	}

	/**
	 * 指定された緯度経度から1km以内の店舗情報を複数件取得する
	 * 
	 * 経度緯度が指定されていない場合は、エラーメッセージを返す。 MySqlのGeometory型を取得するSQL実行し、データを取得。
	 * 
	 * @param lat[double]：緯度
	 * @param lng[double]：経度
	 * @return 取得した店舗情報リスト
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "")
	public ResultDto findNearbyRestaurants(@RequestParam("lat") double lat,@RequestParam("lng") double lng) {
		// ネイティブSQLを実行しEntityリストを取得する
		List<RestaurantEntity> nearbyRestaurants = restaurantServiceImpl.findNearbyRestaurants(lat, lng);
		ResultDto resultDto = beanFactory.createBean(ResultDto.class);
		if ( CollectionUtils.isNotEmpty(nearbyRestaurants) ) {
			// 取得したリストをDtoリストにマッピングする
			List<RestaurantDto> restaurantDtos = nearbyRestaurants.stream()
			        .map(resultEntity -> modelMapper.map(resultEntity, RestaurantDto.class))
			        .collect(Collectors.toList());
			resultDto.setResult(restaurantDtos);
		}
		return resultDto;
	}

	/**
	 * 指定されたカテゴリIDを持つ店舗情報リストを取得する
	 * 
	 * @param categoryId[Long]：対象カテゴリID
	 * @return 店舗情報リスト
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/categories/{categoryId}")
	public ResultDto findRestaurantsByCategoryId(@PathVariable Long categoryId) {
		// 対象のEntityリストを取得する
		List<RestaurantEntity> resultEntities = restaurantServiceImpl.findRestaurantsByCategory(categoryId);
		ResultDto resultDto = beanFactory.createBean(ResultDto.class);
		// 実行結果が存在する場合、resultDtoに詰めて返す
		if ( CollectionUtils.isNotEmpty(resultEntities) ) {
			List<RestaurantDto> resultDtos = resultEntities.stream()
			        .map(resultEntity -> modelMapper.map(resultEntity, RestaurantDto.class))
			        .collect(Collectors.toList());
			resultDto.setResult(resultDtos);
		}
		return resultDto;
	}

	/**
	 * 対象レストランへコメントを登録する
	 * 
	 * @param restaurantId[Long]:レストランID
	 * @param comment[RestaurantCommentDto]:コメント内容
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,value = "/{restaurantId}")
	@ResponseStatus(HttpStatus.CREATED)
	public void commentOnRestaurant(@PathVariable Long restaurantId,@RequestBody RestaurantCommentDto commentDto) {
		// DtoからEntityに変換する
		RestaurantCommentEntity commentEntity = modelMapper.map(commentDto, RestaurantCommentEntity.class);
		restaurantServiceImpl.commentOnRestaurant(restaurantId, commentEntity);
	}

}
