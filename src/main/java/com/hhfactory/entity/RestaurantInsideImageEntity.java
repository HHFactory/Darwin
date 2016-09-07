package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdAndCreatedAtOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurant_inside_images")
@SuppressWarnings("serial")
public class RestaurantInsideImageEntity extends AbstractEntityIdAndCreatedAtOnly implements Serializable{
	/** 画像URL */
	@Column(nullable = false, columnDefinition = "VARCHAR(250)")
	private String imgUrl;
}
