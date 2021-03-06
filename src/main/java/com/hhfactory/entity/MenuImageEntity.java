package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * メニュー画像Entity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "menu_images")
@SuppressWarnings("serial")
public class MenuImageEntity extends AbstractEntityIdOnly implements Serializable {

	/** 画像URL */
	@Column(nullable = false)
	private String imgUrl;

	/** 対象メニューエンティティ */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn( name = "menu_id", referencedColumnName = "id", insertable = false, updatable = false, nullable = false)
	private MenuEntity menu;
}
