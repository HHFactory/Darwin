package com.hhfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhfactory.entity.RestaurantEntity;

/**
 * レストランEntity用Repository
 *
 */
@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long>{

}
