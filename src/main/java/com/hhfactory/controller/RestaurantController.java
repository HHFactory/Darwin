package com.hhfactory.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hhfactory.dto.ResponseDto;
import com.hhfactory.dto.RestaurantCommentDto;
import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.dto.common.message.ApiResultErrorMessage;
import com.hhfactory.entity.RestaurantCommentEntity;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.service.implement.RestaurantServiceImpl;
import com.hhfactory.utils.ErrorMessageUtil;

/**
 * レストラン情報の取得、登録を行うRestContoller 各メソッド内で、サービス処理結果をResultDtoに詰めてクライアントに返す。
 * クライアントへはJSON形式で返す
 * 
 * ResultDtoはbeanFactoryを用いて生成する サービスで取得したEntityはDTOにマッピングしてから、ResultDtoに詰める。
 * マッピングにはModelMapperを用いる
 * 
 * Entity取得メソッドで、Entityが取得できなかった場合はエラーメッセージを生成してクライアントに返す。
 */
@RestController
@RequestMapping(value = "/api/v1/restaurants")
public class RestaurantController {
	@Autowired
	private RestaurantServiceImpl restaurantServiceImpl;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ErrorMessageUtil messageUtil;

	/**
	 * 指定されたレストランIDからレストラン情報を1件取得する
	 * 
	 * @param restaurantId[Long]:レストランID
	 * @return result[ResultDto]:API処理結果
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/{restaurantId}")
	public ResponseDto<RestaurantDto> findRestaurantById(@PathVariable Long restaurantId) {
		// 引数のnullチェック
		Objects.requireNonNull(restaurantId);
				
		ResponseDto<RestaurantDto> responseDto = new ResponseDto<>();
		// RestaurantEntityをサービスから取得する
		RestaurantEntity resultEntity = restaurantServiceImpl.findRestaurantById(restaurantId);
		// Entityを取得できた場合
		if ( resultEntity != null ) {
			RestaurantDto restaurantDto = modelMapper.map(resultEntity, RestaurantDto.class);
			responseDto.setResult(restaurantDto);
		}
		// Entityを取得できなかった場合
		else {
			ApiResultErrorMessage errorMessage = messageUtil.notFoundEntityById(restaurantId.toString());
			responseDto.setErrorMessage(errorMessage);
		}
		return responseDto;
	}

	/**
	 * レストラン情報を登録する
	 * 
	 * 登録済み店舗か判定し、登録済みの場合は登録処理を行わない その際は、登録済みであるメッセージのみをクライアントに返す
	 * クライアントからはJSON形式でデータをもらう サービス処理のため、DTOからEntityにマッピングする
	 * 
	 * @param entity[RestaurantDto]:登録するレストラン情報
	 * @return HttpStatusを返す
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public void createRestaurant(@RequestBody RestaurantDto restaurantDto)  {
		// 引数のnullチェック
		Objects.requireNonNull(restaurantDto);
		
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
	 public ResponseDto<List<RestaurantDto>> findNearbyRestaurants(@RequestParam("lat") double lat,@RequestParam("lng") double lng) {
		 // 引数のnullチェック
		 Objects.requireNonNull(lat);
		 Objects.requireNonNull(lng);
		 ResponseDto<List<RestaurantDto>> responseDto = new ResponseDto<>();
		 
		 // ネイティブSQLを実行しEntityリストを取得する
		 List<RestaurantEntity> nearbyRestaurants = restaurantServiceImpl.findNearbyRestaurants(lat, lng);

		 // 取得したリストをDtoリストにマッピングする		 
		 if ( CollectionUtils.isNotEmpty(nearbyRestaurants) ) {
			 List<RestaurantDto> restaurantDtos = nearbyRestaurants.stream()
					 																										.map(resultEntity -> modelMapper.map(resultEntity, RestaurantDto.class))
					 																										.collect(Collectors.toList());
			 responseDto.setResult(restaurantDtos);
		 } 
		 // 対象店舗情報が取得できなかった場合
		 else {
				ApiResultErrorMessage errorMessage = messageUtil.notFoundNearByRestaurants();
				responseDto.setErrorMessage(errorMessage);
		 }
		 return responseDto;
	 }

	/**
	 * 指定されたカテゴリIDを持つ店舗情報リストを取得する
	 * 
	 * @param categoryId[Long]：対象カテゴリID
	 * @return 店舗情報リスト
	 */
	 @RequestMapping(method = RequestMethod.GET,value =
	 "/categories/{categoryId}")
	 public ResponseDto<List<RestaurantDto>> findRestaurantsByCategoryId(@PathVariable Long categoryId) {
		 // 引数のnullチェック
		 Objects.requireNonNull(categoryId);
		 ResponseDto<List<RestaurantDto>> resultDto = new ResponseDto<>();
		 
		 // 対象のEntityリストを取得する
		 List<RestaurantEntity> resultEntities = restaurantServiceImpl.findRestaurantsByCategory(categoryId);

		 // 実行結果が存在する場合、resultDtoに詰めて返す
		 if ( CollectionUtils.isNotEmpty(resultEntities) ) {
			 List<RestaurantDto> resultDtos = resultEntities.stream().map(resultEntity -> modelMapper.map(resultEntity, RestaurantDto.class))
					 																																										.collect(Collectors.toList());
			resultDto.setResult(resultDtos);
		 }
		 // 対象Entityなかった場合、エラーメッセージを生成する
		 else {
			 ApiResultErrorMessage errorMessage = messageUtil.notFoundEntityById(categoryId.toString());
			 resultDto.setErrorMessage(errorMessage);
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
	public void commentOnRestaurant(@PathVariable Long restaurantId, @RequestBody RestaurantCommentDto commentDto) {
		// DtoからEntityに変換する
		RestaurantCommentEntity commentEntity = modelMapper.map(commentDto, RestaurantCommentEntity.class);
		restaurantServiceImpl.commentOnRestaurant(restaurantId, commentEntity);
	}

}
