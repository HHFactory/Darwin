package com.hhfactory.service.implement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hhfactory.entity.RestaurantCommentEntity;
import com.hhfactory.entity.RestaurantEntity;
import com.hhfactory.repository.RestaurantCommentRepository;
import com.hhfactory.repository.RestaurantRepository;
import com.hhfactory.repository.custom.RestaurantCustomRepository;
import com.hhfactory.service.RestaurantService;

import lombok.NonNull;

/**
 * レストラン系処理クラス
 * publicメソッドの場合、引数のnullチェックを@nonnullで行う。
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

	// リポジトリ対象外Entityを取得するために、entityManagerを使用
	@PersistenceContext
	protected EntityManager entityManager;

	/**
	 * 指定されたIDからレストラン情報を取得する<br>
	 * 対象のレストラン情報がなかった場合、EntityNotFoundExceptionを返す<br>
	 * 
	 * @param restaurantId
	 *            [Long]:取得対象ID,notnull
	 * @return 対象レストラン情報
	 * @throws IllegalArgumentException
	 *             非チェック例外
	 */
	@Transactional(readOnly = true)
	public RestaurantEntity findRestaurantById(@NonNull Long restaurantId) {
		return restaurantRepository.findOne(restaurantId);
	}

	/**
	 * レストラン情報を登録する<br>
	 * 対象のEntityが既に登録されていた場合は、更新される<br>
	 * 
	 * @param insertTarget
	 *            [RestaurantEntity]:登録対象RestaurantEntity
	 * @return 登録結果RestaurantEntity
	 * 
	 */
	public RestaurantEntity createRestaurant(@NonNull RestaurantEntity insertTarget) {
		return restaurantRepository.save(insertTarget);
	}

	/**
	 * レストラン情報を削除する
	 * 
	 * @param id:削除対象データID
	 * 
	 */
	public void deleteRestaurant(@NonNull Long restaurantId) {
		restaurantRepository.delete(restaurantId);
	}

	/**
	 * レストランへのコメント登録処理
	 * 
	 * @param restaurantId
	 *            [Long]:コメント対象レストランID
	 * @param comment
	 *            [RestaurantCommentEntity]:コメント内容
	 * 
	 */
	public void commentOnRestaurant(@NonNull Long restaurantId, @NonNull RestaurantCommentEntity comment) {
		// コメント対象レストランの取得
		RestaurantEntity targetRestaurant = restaurantRepository.findOne(restaurantId);
		if ( targetRestaurant != null ) {
			comment.setRestaurant(targetRestaurant);
			commentRepository.save(comment);
		}
	}

	/**
	 * 現在地から近くの店舗情報を取得する
	 * 
	 * @param lat
	 *            [double]:緯度
	 * @param lng
	 *            [double]:経度
	 * @return 取得結果店舗情報リスト
	 * 
	 */
	public List<RestaurantEntity> findNearbyRestaurants(double lat, double lng) {
		return customRepository.findNearbyRestaurants(lat, lng);
	}
}
