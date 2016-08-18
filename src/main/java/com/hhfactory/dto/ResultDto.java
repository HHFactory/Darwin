package com.hhfactory.dto;

import com.hhfactory.dto.common.message.ResultMessages;

import lombok.Data;

/**
 * API処理結果返却用DTO
 * 各restControllerでbeanFactoryを使用して生成する
 * 
 */
@Data
public class ResultDto {

	/** 各API処理内容格納フィールド */
	private Object result;
	
	/** API処理結果メッセージ格納フィールド */
	private ResultMessages resultMessages;
}
