package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * レストラン画像Entity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurant_images")
public class RestaurantImageEntity extends AbstractEntityIdOnly implements Serializable{
	private static final long serialVersionUID = 1L;

	/** 画像種別コード */
	@Column(nullable = false, columnDefinition = "VARCHAR(10)")
	private String imgCategoryCode;
	
	/** 画像URL */
	@Column(nullable = false)
	private String imgUrl;
			
}
