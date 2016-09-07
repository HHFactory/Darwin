package com.hhfactory.geocode;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.GeocoderStatus;
import com.google.code.geocoder.model.LatLng;

import lombok.NonNull;

/**
 * GoogleGeocoderAPI関連クラス
 *
 */
@Component
public class GeoCoderService {
	@Autowired
	private Geocoder geocoder;
	@Autowired
	private GeocoderRequest geocoderRequest;

	/**
	 * 住所から経度緯度を取得する
	 * 
	 * @param address
	 *            [String]:住所
	 * @return 経度,緯度
	 * @throws RuntimeException
	 *             TODO 適切な例外を投げる
	 * 
	 */
	public LatLng getLatLngByAddress(@NonNull String address) {
		List<GeocoderResult> geocoderResults = getGeocoderResults(address);
		if ( CollectionUtils.isEmpty(geocoderResults) ) {
			throw new RuntimeException();
		}
		return getLatLng(geocoderResults);
	}

	/**
	 * 住所からGoogleGeocodeAPIのレスポンスを取得する<br>
	 * 結果が取得できなかった場合は、空リストを返す
	 * 
	 * @param address
	 *            [String]:住所
	 * @return Geocode取得結果
	 * @throws RuntimeException
	 *             TODO' 適切な例外を投げる
	 * 
	 */
	private List<GeocoderResult> getGeocoderResults(String address) {
		geocoderRequest.setAddress(address);
		GeocodeResponse response;
		try {
			response = geocoder.geocode(geocoderRequest);
			GeocoderStatus status = response.getStatus();
			// ステータスがOK以外の場合は空リストを返す
			if ( !GeocoderStatus.OK.equals(status) ) {
				return Collections.emptyList();
			}
			return response.getResults();
		}
		catch (IOException e) {
			throw new RuntimeException(e.toString());
		}
	}

	/**
	 * geocoderResultsから経度、緯度を取得する<br>
	 * 
	 * @param geocoderResults
	 *            [List<GeocoderResult>]:Geocodeレスポンス
	 * @return 経度緯度
	 */
	private LatLng getLatLng(List<GeocoderResult> geocoderResults) {
		// Geocoder配列の最初の要素から経度緯度が取得可能
		GeocoderResult result = geocoderResults.get(0);
		return result.getGeometry().getLocation();
	}

}
