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

/**
 * メニューEntity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name ="menus")
public class MenuEntity extends AbstractEntityIdOnly implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** メニュー名 */
	@Column(nullable = false)
	private String name;
	
	/** 価格 */
	@Column(nullable = false)
	private Integer price;
		
	/** メニュー画像リスト */
	@OneToMany
	@JoinColumn(name = "menu_image_id", referencedColumnName = "id")
	private List<MenuImageEntity> images;
	
	/** メニューコメントリスト */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menu")
	private List<MenuCommentEntity> comments;
		
	/** 対象レストランエンティティ */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  = "restaurant_id", referencedColumnName = "id", insertable = true, nullable = false, updatable = false)
	private RestaurantEntity restaurant;
}
