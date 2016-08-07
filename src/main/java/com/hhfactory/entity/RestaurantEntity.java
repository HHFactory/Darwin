package com.hhfactory.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * レストランEntity
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurants")
public class RestaurantEntity extends AbstractEntityIdOnly implements Serializable{
	private static final long serialVersionUID = 1L;
	/** ステータス */
	@Column(nullable = false, columnDefinition = "VARCHAR(5)")
	private String status;
	
	/** 店舗名 */
	@Column(nullable = false, columnDefinition = "VARCHAR(20)")
	private String name;
	
	/** 郵便番号 */
	@Column(nullable = false, columnDefinition = "VARCHAR(7)")
	private Integer postNum;
	
	/** 都道府県コード */
	@Column(nullable = false, columnDefinition = "VARCHAR(5)")
	private String prefectureCode;
	
	/** 市区町村 */
	@Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '' ")
	private String city;
	
	/** 住所 */
	@Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '' ")
	private String address;
	
	/** ビル名・その他住所 */
	@Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT '' ")
	private String building;
	
	/** 座標 TODO:後から実装する*/
//	@Column(nullable = false, columnDefinition = "GEOMETRY")
//	private Point location;
	
	/** ランチタイムfrom */	
	@Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '' ")
	private String lunchTimeFrom;
	
	/** ランチタイムto */
	@Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '' ")
	private String lunchTimeTo;
	
	/** ディナータイムfrom */
	@Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '' ")
	private String dinnerTimeFrom;
	
	/** ディナータイムto */
	@Column(nullable = false, columnDefinition = "VARCHAR(10) DEFAULT '' ")
	private String dinnerTimeTo;
	
	/** 席数 */
	@Column(nullable = false, columnDefinition = "VARCHAR(5)")
	private String seatCounts;
	
	/** 休日コード */
	@Column(nullable = false, columnDefinition = "VARCHAR(5)")
	private String holidayCode;
	
	/** 喫煙タイプコード */
	@Column(nullable = false, columnDefinition = "VARCHAR(5)")
	private String smokingTypeCode;
	
	/** wifi有無コード */
	@Column(nullable = false, columnDefinition = "VARCHAR(5)")
	private String hasWifiCode;
	
	/** 画像リスト */
	@OneToMany
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<RestaurantImageEntity> images;
	
	/** コメントリスト */
	@OneToMany
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<RestaurantCommentEntity> comments;
	
	/** メニューリスト */
	@OneToMany
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<MenuEntity> menus;
	
	/** お気に入り */
	@OneToMany
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<FavoriteRestaurantEntity> favorites;
}
