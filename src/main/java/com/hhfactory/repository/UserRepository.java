package com.hhfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhfactory.entity.UserEntity;

/**
 * ユーザEntity用Repository
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

}
