package com.hhfactory.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * メニュー返却用DTO
 *
 */

@Data
@Component
public class MenuDto {
	private String name;
}
