package com.hhfactory.dto.common.message;

import lombok.Data;

/**
 * API処理時のエラーメッセージ格納クラス
 *
 */
@Data
public class ApiResultErrorMessage {
	private String code;
	private String type;
	private String message;
	private String userMessage;
}
