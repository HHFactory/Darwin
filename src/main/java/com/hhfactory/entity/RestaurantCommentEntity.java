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
public class RestaurantCommentEntity extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/** コメント内容 */
	@Column(nullable = false,columnDefinition = "VARCHAR(1000)")
	private String comment;

	/** コメント画像リスト */
	@OneToMany
	private List<RestaurantImageEntity> images;

	/** コメント対象レストランエンティティ */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "restaurant_id",referencedColumnName = "id",insertable = true,updatable = false,nullable = false)
	private RestaurantEntity restaurant;
}
