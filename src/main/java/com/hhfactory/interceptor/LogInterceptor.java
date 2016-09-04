package com.hhfactory.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * spring AOPを使用して、メソッド実行前後でログ出力処理を挟む
 * 
 *
 */
@Aspect
@Component
public class LogInterceptor {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * serviceパッケージ以下のメソッド実行前後でログを出力する
	 * 
	 * 実行前ログとして、メソッド名、クラス名を出力
	 * 実行後ログとして、メソッドの戻り値を出力
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(* com.hhfactory.service.*.*(..) )")
	public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
		StringBuilder sb = new StringBuilder();
		Object result = null;
		// メソッド実行前
		try {
			sb.append("Before invoke: parameters = ");
			sb.append(joinPoint.getSignature());
			sb.append(", by ");
			sb.append(joinPoint.getTarget().getClass());
			sb.append("#");
			sb.append(joinPoint.getSignature().getName());
			logger.debug(sb.toString());
			result = joinPoint.proceed();
		}
		// メソッド実行後エラー時
		catch (Throwable e) {
			sb.append(e.getMessage());
			throw e;
		}
		// メソッド実行後
		finally {
			sb.append("After invoke: result = ");
			sb.append(result);
		}
		logger.debug(sb.toString());
		return result;
	}

}
