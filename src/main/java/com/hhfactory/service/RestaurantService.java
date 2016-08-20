package com.hhfactory.service;

import java.util.List;

import com.hhfactory.entity.RestaurantCommentEntity;
import com.hhfactory.entity.RestaurantEntity;

/**
 * レストラン系処理定義インターフェース
 *
 */
public interface RestaurantService {	
	// 指定したIDからレストラン情報を取得する
	public RestaurantEntity findRestaurantById(Long restaurantId);
	// 近くのレストラン情報を取得する
	public List<RestaurantEntity> findNearbyRestaurants(double lat, double lng);
	// レストラン情報を登録する
	public RestaurantEntity createRestaurant(RestaurantEntity insertTargetEntity);
	// レストラン情報を削除する
	public void deleteRestaurant(Long restaurantId);
	// レストランにコメントを登録する
	public void commentOnRestaurant(Long restaurantId, RestaurantCommentEntity insertTargetEntity);
}
