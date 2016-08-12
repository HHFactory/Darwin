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

import com.hhfactory.dto.MenuDto;
import com.hhfactory.dto.ResultDto;
import com.hhfactory.entity.MenuEntity;
import com.hhfactory.service.implement.MenuServiceImpl;

/**
 * メニュー系処理を担当するコントローラ
 *
 */
@RestController
@RequestMapping(value = "/api/v1/Menus")
public class MenuController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@Autowired
	private ResultDto resultDto;
	@Autowired
	private MenuDto menuDto;
	
	/**
	 * 指定されたIDからメニュー情報を取得する
	 * @param id[Long]メニューID
	 * @return result[ResultDto]:API処理結果
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResultDto findMenu(@PathVariable Long id) {
		MenuEntity resultEntity = menuServiceImpl.findMenuById(id);
		if (resultEntity != null) {
			menuDto.setName(resultEntity.getName());
			resultDto.setResult(menuDto);
		}
		return resultDto;
	}
	/**
	 * メニュー情報を登録する
	 * @param entity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createMenu(@RequestBody MenuDto menuDto) {
		// TODO:引数->menuEntityに変換
//		MenuEntity menuEntity = menuServiceImpl.createMenu(entity);
		System.out.println(menuDto.getName());
	}
	
}
