package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * カテゴリEntity
 * レストラン、メニューのカテゴリとして使用する
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "food_categories")
public class FoodCategory extends AbstractEntityIdOnly implements Serializable {
	private static final long serialVersionUID = 1L;

	/** カテゴリ名 */
	@Column(nullable = false,columnDefinition = "VARCHAR(20)")
	private String categoryName;

}
