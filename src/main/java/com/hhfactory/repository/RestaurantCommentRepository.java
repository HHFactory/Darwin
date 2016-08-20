package com.hhfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhfactory.entity.RestaurantCommentEntity;

/**
 * レストランコメントEntity用repository
 *
 */
public interface RestaurantCommentRepository extends JpaRepository<RestaurantCommentEntity, Long>{

}
