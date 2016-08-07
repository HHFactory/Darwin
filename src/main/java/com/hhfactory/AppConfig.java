package com.hhfactory;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	/**
	 * EntityからDtoにマッピングするクラス
	 * 各controllerでautowiredして使う想定
	 * @return
	 */
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
}
