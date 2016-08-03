package com.hhfactory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hhfactory.entity.CodeGroupEntity;

/**
 * コードグループEntity用Repository
 *
 */
@Repository
public interface CodeRepository extends JpaRepository<CodeGroupEntity, Long>{

}
