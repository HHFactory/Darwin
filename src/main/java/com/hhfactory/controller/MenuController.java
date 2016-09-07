package com.hhfactory.controller;

import java.util.Objects;

import org.modelmapper.ModelMapper;
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
import com.hhfactory.dto.ResponseDto;
import com.hhfactory.dto.common.message.ApiResultErrorMessage;
import com.hhfactory.entity.MenuEntity;
import com.hhfactory.service.implement.MenuServiceImpl;
import com.hhfactory.utils.ErrorMessageUtil;

/**
 * メニュー情報の取得、登録を行うRestContoller<br>
 * 各メソッド内で、サービス処理結果をResultDtoに詰めてクライアントに返す。<br>
 * クライアントへはJSON形式で返す<br>
 * 
 * ResultDtoはbeanFactoryを用いて生成する サービスで取得したEntityはDTOにマッピングしてから、ResultDtoに詰める。<br>
 * マッピングにはModelMapperを用いる<br>
 * 
 * Entity取得メソッドで、Entityが取得できなかった場合はエラーメッセージを生成してクライアントに返す。<br>
 */
@RestController
@RequestMapping(value = "/api/v1/menus")
public class MenuController {
	@Autowired
	private MenuServiceImpl menuServiceImpl;
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private ErrorMessageUtil messageUtil;

	/**
	 * 指定されたIDからメニュー情報を取得する
	 * 
	 * @param menuId
	 *            [Long]:メニューID
	 * @return API実行結果
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET,value = "/{menuId}")
	public ResponseDto<MenuDto> findMenuById(@PathVariable Long menuId) {
		// 引数のnullチェック
		Objects.requireNonNull(menuId);
		ResponseDto<MenuDto> resultDto = new ResponseDto<>();
		
		MenuEntity resultEntity = menuServiceImpl.findMenuById(menuId);
		// Entityを取得できた場合
		if ( resultEntity != null ) {
			MenuDto menuDto = modelmapper.map(resultEntity, MenuDto.class);
			resultDto.setResult(menuDto);
		} 
		// Entityを取得できなかった場合
		else {
			ApiResultErrorMessage errorMessage = messageUtil.notFoundEntityById(menuId.toString());
			resultDto.setErrorMessage(errorMessage);
		}
		return resultDto;
	}

	/**
	 * メニュー情報を登録する
	 * 
	 * @param menuDto
	 *            [MenuDto]:メニュー登録内容
	 * @return HttpStatus
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createMenu(@RequestBody MenuDto menuDto) {
		// 引数のnullチェック
		Objects.requireNonNull(menuDto);
		
		MenuEntity insertTargetEntity = modelmapper.map(menuDto, MenuEntity.class);
		menuServiceImpl.createMenu(insertTargetEntity);
	}

}
