package com.hhfactory.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * メニューEntity
 *
 */
@Data
@ToString( exclude = {"images", "comments"})
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "menus")
@SuppressWarnings("serial")
public class MenuEntity extends AbstractEntityIdOnly implements Serializable {

	/** メニュー名 */
	@Column(nullable = false,columnDefinition = "VARCHAR(50)")
	private String name;
	
	/** 料理タイプ */
	@Column(nullable = false, columnDefinition = "int(2)")
	private Integer foodType;

	/** 価格 */
	@Column(nullable = false,columnDefinition = "int(5)")
	private Integer price;

	/** メニュー画像リスト */
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "menu")
	private List<MenuImageEntity> images;

	/** メニューコメントリスト */
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "menu")
	private List<MenuCommentEntity> comments;

	/** 対象レストランエンティティ */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id",referencedColumnName = "id",insertable = true,nullable = false,updatable = false)
	private RestaurantEntity restaurant;
}
