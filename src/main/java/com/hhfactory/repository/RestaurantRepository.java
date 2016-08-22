package com.hhfactory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhfactory.entity.FoodCategory;
import com.hhfactory.entity.RestaurantEntity;

/**
 * レストランEntity用Repository
 *
 */
@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>{
	
	/** 対象のカテゴリIDを持つEntityリストを取得する */
	public List<RestaurantEntity> findByFoodCategory(FoodCategory foodCategory);
	
}
