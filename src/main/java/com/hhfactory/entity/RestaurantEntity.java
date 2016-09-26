package com.hhfactory.entity;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hhfactory.entity.common.AbstractEntityIdOnly;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * レストランEntity
 *
 */
@Data
@ToString(exclude = {"insideImages", "outsideImages", "comments", "menus",} )
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "restaurants")
@SuppressWarnings("serial")
public class RestaurantEntity extends AbstractEntityIdOnly implements Serializable {
	/** ステータス */
	@Column(nullable = false,columnDefinition = "VARCHAR(5) DEFAULT 'valid' ")
	private String status;

	/** 店舗名 */
	@Column(nullable = false,columnDefinition = "VARCHAR(20)")
	private String name;

	/** 都道府県 */
	@Column(nullable = false,columnDefinition = "VARCHAR(5)")
	private String prefecture;

	/** 市区町村 */
	@Column(nullable = false,columnDefinition = "VARCHAR(50) DEFAULT '' ")
	private String city;

	/** 住所 */
	@Column(nullable = false,columnDefinition = "VARCHAR(50) DEFAULT '' ")
	private String address;

	/** ビル名・その他住所 */
	@Column(nullable = false,columnDefinition = "VARCHAR(50) DEFAULT '' ")
	private String building;

	/** 座標 */
	@Column(nullable = false,columnDefinition = "GEOMETRY")
	private byte[] latLng;

	/** ランチタイムfrom */
	@Column(columnDefinition = "TIME")
	private Time lunchTimeFrom;

	/** ランチタイムto */
	@Column(columnDefinition = "TIME ")
	private Time lunchTimeTo;

	/** ディナータイムfrom */
	@Column(columnDefinition = "TIME")
	private Time dinnerTimeFrom;

	/** ディナータイムto */
	@Column(columnDefinition = "TIME")
	private Time dinnerTimeTo;

	/** 席数 */
	@Column(nullable = false,columnDefinition = "VARCHAR(5)")
	private String seatCounts;

	/** 休日コード */
	@Column(nullable = false,columnDefinition = "VARCHAR(5)")
	private String holidayCode;

	/** 喫煙タイプコード */
	@Column(nullable = false,columnDefinition = "VARCHAR(5)")
	private String smokingTypeCode;

//	/** 外国語メニュータイプ */
//	@Column(nullable = false,columnDefinition = "VARCHAR(5)")
//	private String foreignMenuType;

	/** wifi有無コード */
	@Column(nullable = false,columnDefinition = "BIT(1)")
	private boolean hasWifi;

	/** クレジット決済可否フラグ */
	@Column(nullable = false,columnDefinition = "BIT(1)")
	private boolean isCreaditUse;

//	/** 利用可能クレジットリスト */
//	@ElementCollection
//	private List<String> creditCardList;

	/** 内観画像リスト */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")	
	private List<RestaurantInsideImageEntity> insideImages;

	/** 外観画像リスト */
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
	private List<RestaurantOutsideImageEntity> outsideImages;

	/** コメントリスト */
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
	private List<RestaurantCommentEntity> comments;

	/** メニューリスト */
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "restaurant")
	private List<MenuEntity> menus;
}
