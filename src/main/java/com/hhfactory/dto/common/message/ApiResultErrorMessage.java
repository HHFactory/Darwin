package com.hhfactory.dto.common.message;

import lombok.Data;

/**
 * API処理時のエラーメッセージ格納クラス
 *
 */
@Data
public class ApiResultErrorMessage {
	// エラーコード
	private String code;
	// エラータイプ
	private String type;
	// エラーメッセージ
	private String message;
	/**
	 * クライアント用エラーメッセージ<br>
	 * クライアントでそのまま表示すれば良い
	 */
	private String userMessage;
}
