package com.hhfactory.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private List<MenuImageEntity> images;
	
	/** メニューコメントリスト */
	@OneToMany
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private List<MenuCommentEntity> comments;
	
	/** お気に入りメニューリスト */
	@OneToMany
	@JoinColumn(name = "menu_id", referencedColumnName = "id")
	private List<FavoriteMenuEntity> favorites;
}
