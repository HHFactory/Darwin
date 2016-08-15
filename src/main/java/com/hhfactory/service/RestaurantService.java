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
	public RestaurantEntity findRestaurantById(Long id);
	// レストラン情報を登録する
	public RestaurantEntity createRestaurant(RestaurantEntity insertTarget);
	// レストラン情報を削除する
	public void deleteRestaurant(Long id);
	// レストラン情報を更新する
	public RestaurantEntity updateRestaurant(Long id, RestaurantEntity alterEntity);
	// レストランにコメントを登録する
	public void commentOnRestaurant(Long restaurantId, RestaurantCommentEntity comment);
	// 近くのレストラン情報を取得する
	public List<RestaurantEntity> findNearbyRestaurants(double lat, double lng);
	// お気に入りのレストラン一覧を取得する
	// レストランにコメントを登録する

}
