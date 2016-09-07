package com.hhfactory.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdAndCreatedAtOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * お気に入りレストランEntity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "favorite_restaurants")
@SuppressWarnings("serial")
public class FavoriteRestaurantEntity extends AbstractEntityIdAndCreatedAtOnly implements Serializable {

}