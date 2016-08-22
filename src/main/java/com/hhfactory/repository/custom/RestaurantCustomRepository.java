package com.hhfactory.repository.custom;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.hhfactory.entity.RestaurantEntity;

/**
 * レストランのカスタムSQL定義クラス
 *
 */
@Component
public class RestaurantCustomRepository {
	/** ネイティブSQLを実行する必要があるため、entityManagerを生成 */
	@PersistenceContext 
	protected EntityManager entityManager;
	
	/**
	 * 指定された緯度経度から半径1km以内の店舗情報を取得する
	 * @param lat:緯度
	 * @param lng:経度
	 * @return　SQL実行結果
	 */
	@SuppressWarnings("unchecked")
	public List<RestaurantEntity> findNearbyRestaurants(double lat, double lng) {
		String latLngParam = lat + " " + lng;
		Query query = entityManager.createNativeQuery(
				"select * ,"
				+ "	round("
					+ "GLength("
						+ "GeomFromText("
							+ "CONCAT("
							+ " 'LineString( " + latLngParam + ",'" + ",X(lat_lng)," + "' ' " + ",Y(lat_lng), ')' "
							+ ")"
						+ ")"
					+ ") * 111000"
				+ ") AS len FROM Restaurants group by id having len <= 1000 "
				+ "order by len"
				, RestaurantEntity.class);
		return query.getResultList();
	}
}
