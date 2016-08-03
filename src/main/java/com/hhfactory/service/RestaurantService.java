package com.hhfactory.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.hhfactory.entity.RestaurantCommentEntity;
import com.hhfactory.entity.RestaurantEntity;

/**
 * レストラン系処理定義インターフェース
 *
 */
public interface RestaurantService {	
	// 指定したIDからレストラン情報を取得する
	public RestaurantEntity findRestaurantById(Long id);
	// レストラン情報を登録する
	public RestaurantEntity createRestaurant(@RequestBody RestaurantEntity insertTarget);
	// レストラン情報を削除する
	public void deleteRestaurant(Long id);
	// レストラン情報を更新する
	public RestaurantEntity updateRestaurant(Long id, RestaurantEntity alterEntity);
	// レストランにコメントを登録する
	public void commentOnRestaurant(Long restaurantId, RestaurantCommentEntity comment);

	// 近くのレストラン情報を取得する
	// お気に入りのレストラン一覧を取得する
	// レストランにコメントを登録する
}
