package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdAndCreatedAtOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * お気に入りメニューテーブルEntity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "favorite_menus")
public class FavoriteMenuEntity extends AbstractEntityIdAndCreatedAtOnly implements Serializable {
	private static final long serialVersionUID = 1L;

}
