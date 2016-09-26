package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * レストランコメントEntity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurant_comments")
@SuppressWarnings("serial")
public class RestaurantCommentEntity extends AbstractEntity implements Serializable {

	/** コメント内容 */
	@Column(nullable = false,columnDefinition = "VARCHAR(1000)")
	private String comment;

	/** コメント対象レストランエンティティ */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id",referencedColumnName = "id",insertable = true,updatable = false,nullable = false)
	private RestaurantEntity restaurant;
}
