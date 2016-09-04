package com.hhfactory.utils;

import org.springframework.stereotype.Component;

import com.hhfactory.dto.common.message.ApiResultErrorMessage;

import lombok.NonNull;

/**
 * 各種エラーメッセージの生成ユーティリティクラス
 *
 */
@Component
public class ErrorMessageUtil {

	/**
	 * 指定したIDで対象Entityが取得できたなかった場合の、エラーメッセージを生成する
	 * 
	 * @param targetId
	 * @return
	 */
	public ApiResultErrorMessage notFoundEntityById(@NonNull String targetId) {
		ApiResultErrorMessage errorMessage = new ApiResultErrorMessage();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("target id:");
		stringBuilder.append(targetId);
		stringBuilder.append(" is not found");
		errorMessage.setMessage(stringBuilder.toString());
		errorMessage.setUserMessage("指定されたIDが見つかりませんでした");
		return errorMessage;
	}
	
	/**
	 * 近くの店舗情報が取得できなかった場合
	 * @return
	 */
	public ApiResultErrorMessage notFoundNearByRestaurants() {
		ApiResultErrorMessage errorMessage = new ApiResultErrorMessage();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("entity is not found around here");
		errorMessage.setMessage(stringBuilder.toString());
		errorMessage.setUserMessage("この近くに店舗はありません");
		return errorMessage;
	}
}
