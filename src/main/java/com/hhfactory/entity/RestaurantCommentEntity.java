package com.hhfactory.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class RestaurantCommentEntity extends AbstractEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** コメント内容 */
	@Column(nullable = false)
	private String comment;

	/** コメント画像リスト */
	@OneToMany
	@JoinColumn(name = "restaurant_comment_id", referencedColumnName = "id")
	private List<RestaurantImageEntity> images;	
}
