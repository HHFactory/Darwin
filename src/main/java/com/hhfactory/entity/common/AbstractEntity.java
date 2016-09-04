package com.hhfactory.entity.common;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * Entityの共通フィールドを定義した抽象クラス
 * 
 * EntityのフィールドにID、登録日時、更新日時を設定する場合に
 * このクラスを継承すること。
 * 
 */
@Data
@MappedSuperclass
abstract public class AbstractEntity {
	// ID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 登録日時
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT 0")
	private Date createdAt;

	// 更新日時
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date updatedAt;

	/**
	 * 登録前処理（登録前処理として登録日時を設定する）
	 * 
	 * MySqlだとcurrent_timestampを1テーブル1カラムにしか設定できないため
	 * 登録日時をフィールドに現在日時を設定する。
	 * 
	 */
	@PrePersist
	private void prePersist() {
		setCreatedAt(new Date(Calendar.getInstance().getTime().getTime()));
	}
}
