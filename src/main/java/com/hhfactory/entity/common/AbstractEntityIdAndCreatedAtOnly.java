package com.hhfactory.entity.common;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Entityの共通フィールドを定義した抽象クラス
 * 
 * EntityのフィールドにID、を設定する場合に、
 * このクラスを継承すること。
 * 
 */
@Data
@MappedSuperclass
abstract public class AbstractEntityIdAndCreatedAtOnly {
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// 登録日時
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
}
