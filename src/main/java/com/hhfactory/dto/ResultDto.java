package com.hhfactory.dto;

import org.springframework.stereotype.Component;

import com.hhfactory.dto.common.message.ResultMessages;

import lombok.Data;

/**
 * API処理結果返却用DTO
 *
 */
@Data
@Component
public class ResultDto {

	/** 各API処理内容格納フィールド */
	private Object result;
	
	/** API処理結果メッセージ格納フィールド */
	private ResultMessages resultMessages;
}
