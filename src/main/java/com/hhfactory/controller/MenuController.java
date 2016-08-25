package com.hhfactory.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hhfactory.dto.MenuDto;
import com.hhfactory.dto.ResultDto;
import com.hhfactory.entity.MenuEntity;
import com.hhfactory.service.implement.MenuServiceImpl;

/**
 * メニュー系処理を担当するコントローラ
 *
 */
@RestController
@RequestMapping(value = "/api/v1/menus")
public class MenuController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@Autowired
	private AutowireCapableBeanFactory beanFactory;
	@Autowired
	private ModelMapper modelmapper;

	/**
	 * 指定されたIDからメニュー情報を取得する
	 * 
	 * @param menuId[Long]:メニューID
	 * @return result[ResultDto]:API処理結果
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/{menuId}")
	public ResultDto findMenu(@PathVariable Long menuId) {
		MenuEntity resultEntity = menuServiceImpl.findMenuById(menuId);
		ResultDto resultDto = beanFactory.createBean(ResultDto.class);
		if ( resultEntity != null ) {
			MenuDto menuDto = modelmapper.map(resultEntity, MenuDto.class);
			resultDto.setResult(menuDto);
		}
		return resultDto;
	}

	/**
	 * メニュー情報を登録する
	 * 
	 * @param entity
	 * @return
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createMenu(@RequestBody MenuDto menuDto) {
		MenuEntity insertTargetEntity = modelmapper.map(menuDto, MenuEntity.class);
		menuServiceImpl.createMenu(insertTargetEntity);
	}

}
