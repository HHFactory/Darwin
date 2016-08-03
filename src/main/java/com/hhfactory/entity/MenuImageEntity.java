package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class MenuImageEntity extends AbstractEntityIdOnly implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 画像URL */
	@Column(nullable = false)
	private String imgUrl;
}
