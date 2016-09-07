package com.hhfactory.entity.common;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

/** 
 * Entityの共通フィールドを定義した抽象クラス
 * 
 * EntityのフィールドにIDを設定する場合に、
 * このクラスを継承すること。
 */
@Data
@MappedSuperclass
abstract public class AbstractEntityIdOnly {
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
}
