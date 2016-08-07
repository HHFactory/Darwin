package com.hhfactory.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
	private ModelMapper modelMapper;
	@Autowired
	private PropertyMap<RestaurantEntity, RestaurantDto> restaurantEntityToDtoMap;
	
	/**
	 * 指定されたIDからレストラン情報を取得する
	 * @param id[Long]:レストランID
	 * @return result[ResultDto]:API処理結果
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResultDto findRestaurantById(@PathVariable Long id) {
		// RestaurantEntityを取得
		RestaurantEntity resultEntity = restaurantServiceImpl.findRestaurantById(id);
		if( resultEntity != null ) {
			// Entity -> DTOにつめかえる
			modelMapper.addMappings(restaurantEntityToDtoMap);
			RestaurantDto restaurantDto = modelMapper.map(resultEntity, RestaurantDto.class);
			resultDto.setResult(restaurantDto);
		}
		return resultDto;
	}
	
	/**
	 * レストラン情報を登録する
	 * @param entity[RestaurantDto]:登録するレストラン情報
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createRestaurant(@RequestBody RestaurantDto restaurantDto) {
		// Dto -> Entityにマッピング
		RestaurantEntity insertTargetEntity = modelMapper.map(restaurantDto, RestaurantEntity.class);
		restaurantServiceImpl.createRestaurant(insertTargetEntity);
	}

}
