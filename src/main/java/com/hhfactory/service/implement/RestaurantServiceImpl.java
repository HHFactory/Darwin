package com.hhfactory.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhfactory.entity.RestaurantCommentEntity;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.repository.RestaurantCommentRepository;
import com.hhfactory.repository.RestaurantRepository;
import com.hhfactory.repository.custom.RestaurantCustomRepository;
import com.hhfactory.service.RestaurantService;

/**
 * レストラン系処理クラス
 *
 */
@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	@Autowired
	private RestaurantCustomRepository customRepository;
	@Autowired
	private RestaurantCommentRepository commentRepository;
	
	/**
	 * 指定されたIDからレストラン情報を取得する
	 * @param id:取得対象ID
	 * @return 対象レストラン情報
	 * 
	 */
	@Transactional(readOnly = true)
	public RestaurantEntity findRestaurantById(Long restaurantId) {
		return restaurantRepository.findOne(restaurantId);
	}

	/**
	 * レストラン情報を登録する
	 * @param insertTarget:登録対象RestaurantEntity
	 * @return 登録結果RestaurantEntity
	 * 
	 */
	public RestaurantEntity createRestaurant(RestaurantEntity insertTarget) {
		return restaurantRepository.save(insertTarget);
	}
	
	/**
	 * レストラン情報を削除する
	 * @param id:削除対象データID
	 * 
	 */
	public void deleteRestaurant(Long restaurantId) {
		restaurantRepository.delete(restaurantId);
	}
	
	/**
	 * レストランへのコメント登録処理
	 * @param restaurantId[Long]:コメント対象レストランID
	 * @param comment[RestaurantCommentEntity]:コメント内容
	 * 
	 */
	public void commentOnRestaurant(Long restaurantId, RestaurantCommentEntity comment) {
		// コメント対象レストランの取得
		RestaurantEntity targetRestaurant = restaurantRepository.findOne(restaurantId);
		if( targetRestaurant != null ) {
			comment.setRestaurant(targetRestaurant);
			commentRepository.save(comment);			
		}
	}
	
	/**
	 * 現在地から近くの店舗情報を取得する
	 * @param lat[double]:緯度
	 * @param lng[double]:経度
	 * @return 取得結果店舗情報リスト
	 * 
	 */
	public List<RestaurantEntity> findNearbyRestaurants(double lat, double lng) {
		return customRepository.findNearbyRestaurants(lat, lng);
	}

}
