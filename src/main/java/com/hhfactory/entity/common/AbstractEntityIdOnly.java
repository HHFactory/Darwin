package com.hhfactory.entity.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/** 
 * 共通カラムクラス
 * （ID)
 */
@Data
@MappedSuperclass
public class AbstractEntityIdOnly {
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
