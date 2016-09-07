package com.hhfactory.dto;

import com.hhfactory.dto.common.message.ApiResultErrorMessage;

import lombok.Data;

/**
 * API処理結果返却用DTO
 * 各restControllerでbeanFactoryを使用して生成する
 * 
 */
@Data
public class ResponseDto<T> {
	private T result;
	private ApiResultErrorMessage errorMessage;
}
