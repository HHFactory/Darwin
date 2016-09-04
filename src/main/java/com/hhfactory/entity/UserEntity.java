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
 * ユーザEntity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class UserEntity extends AbstractEntityIdOnly implements Serializable {
	private static final long serialVersionUID = 1L;

	/** ユーザ名 */
	@Column(nullable = false)
	private String name;

	/** メールアドレス */
	@Column(nullable = false)
	private String mailAddress;

	/** パスワード */
	@Column(nullable = false)
	private String password;

	/** 性別コード値 */
	private Integer genderCode;

	/** 年齢 */
	private Integer age;

	/** ユーザレベル */
	@Column(nullable = false)
	private Integer rating;

	/** アイコン画像URL */
	private String iconUrl;

	/** 居住地域 */
	private String liveArea;

	/** 自己紹介文 */
	private String selfIntro;

	/** お気に入りレストラン */
	@OneToMany
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private List<FavoriteRestaurantEntity> favoriteRestaurants;

	/** お気に入りメニューリスト */
	@OneToMany
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private List<FavoriteMenuEntity> favoriteMenus;

	/** レストランコメントリスト */
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<RestaurantCommentEntity> restaurantComments;

	/** メニューコメントリスト */
	@OneToMany
	@JoinColumn(name = "user_id")
	private List<MenuCommentEntity> menuComments;

}
