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
 * 共通カラムクラス
 * （ID、登録日時）
 */
@Data
@MappedSuperclass
public class AbstractEntityIdAndCreatedAtOnly {
	/** ID */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/** 登録日 */
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date createdAt;
}
