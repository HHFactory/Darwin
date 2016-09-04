package com.hhfactory.geocode;

import java.io.IOException;
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
	 * @param address[String]:住所
	 * @return 経度,緯度
	 * @throws IOException
	 */
	public LatLng getLatLngByAddress(String address) throws IOException {
		List<GeocoderResult> geocoderResults = getGeocoderResults(address);
		if ( CollectionUtils.isEmpty(geocoderResults) ) {
			return null;
		}
		return getLatLng(geocoderResults);
	}

	/**
	 * 住所からGoogleGeocodeAPIのレスポンスを取得する
	 * 
	 * @param address:住所
	 * @return geocoderResults:Geocode結果
	 * @throws IOException
	 */
	private List<GeocoderResult> getGeocoderResults(String address) throws IOException {
		geocoderRequest.setAddress(address);
		GeocodeResponse response = geocoder.geocode(geocoderRequest);
		GeocoderStatus status = response.getStatus();
		if ( !GeocoderStatus.OK.equals(status) ) {
			throw new RuntimeException(status.value());
		}
		return response.getResults();
	}

	/**
	 * geocoderResultsから経度、緯度を取得する
	 * 
	 * @param geocoderResults:Geocodeレスポンス
	 * @return location:経度、緯度
	 */
	private LatLng getLatLng(List<GeocoderResult> geocoderResults) {
		GeocoderResult result = geocoderResults.get(0);
		return result.getGeometry().getLocation();
	}

}
