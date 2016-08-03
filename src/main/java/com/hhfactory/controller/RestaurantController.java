package com.hhfactory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hhfactory.dto.RestaurantDto;
import com.hhfactory.dto.ResultDto;
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
	private ResultDto resultDto;
	@Autowired
	private RestaurantDto restaurantDto;
	
	/**
	 * 指定されたIDからレストラン情報を取得する
	 * @param id[Long]:レストランID
	 * @return result[ResultDto]:API処理結果
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResultDto findRestaurant(@PathVariable Long id) {
		RestaurantEntity resultEntity = restaurantServiceImpl.findRestaurantById(id);
		if( resultEntity != null ) {
			restaurantDto.setName(resultEntity.getName());
			resultDto.setResult(restaurantDto);
		}
		return resultDto;
	}
	
	/**
	 * レストラン情報を登録する
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createRestaurant(@RequestBody RestaurantDto restaurantDto) {
		// TODO:引数->restaurantEntityに変換
//		RestaurantEntity resultEntity = restaurantServiceImpl.createRestaurant(entity);
		System.out.println(restaurantDto.getName());
	}
	
	

}
