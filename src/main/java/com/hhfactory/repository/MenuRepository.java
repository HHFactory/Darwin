package com.hhfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhfactory.entity.MenuEntity;

/**
 * メニューEntity用Repository
 *
 */
@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long>{

}
