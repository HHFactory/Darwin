package com.hhfactory.mapper;

import java.nio.ByteBuffer;

import com.google.code.geocoder.model.LatLng;

import lombok.NonNull;

/**
 * 経度・緯度情報をMysqlのGeometory型として扱えるように、byte配列に変換するクラス
 *
 */
public class ByteConverter {

	/**
	 * MYSQLのGeometry型の先頭9バイトを表すbyte配列
	 * 情報 経度・緯度をbyte配列に変換する際に、変換後byte配列の先頭に付与する
	 * 
	 */
	private static final byte[] PREFIX_BYTES = { 0, 0, 0, 0, 0x01, 0x01, 0, 0, 0 };

	/**
	 * LatLng型をGeometory型に適応させたbyte配列に変換する
	 * Geometory型の仕様にあわせて、緯度、経度の順にbyte配列に格納させること。
	 * 
	 * 
	 * @param targetLocation[LatLng]:経度緯度情報, notnull
	 * @return Mysql Geometory型byte配列
	 */
	public static byte[] convertToGeometoryData(@NonNull LatLng targetLocation) {
		// Geometry型byte配列として25バイトのメモリを確保し、Geometryの頭9バイトを格納しておく
		ByteBuffer resultBuffer = ByteBuffer.allocate(25).put(PREFIX_BYTES);
		// double型の経度・緯度のbyte配列として8バイトのメモリを確保
		ByteBuffer doubleBuffer = ByteBuffer.allocate(8);
		// 緯度をdouble型に変換 -> byte配列に変換 -> Geometry型byte配列に格納（XXX:緯度から先に格納すること）
		putReversedData(resultBuffer, doubleBuffer, targetLocation.getLat().floatValue());
		// 経度をdouble型に変換 -> byte配列に変換 -> Geometry型byte配列に格納
		putReversedData(resultBuffer, doubleBuffer, targetLocation.getLng().floatValue());

		return resultBuffer.array();
	}

	/**
	 * 引数で渡された経度・緯度をMYSQLのGeometry型のbyte配列に変換する
	 * 
	 * @param geometroyByteBuffer[ByteBuffer]:geometory型byteデータを格納するbytebuffer
	 * @param doubleByteBuffer[ByteBuffer]:経度緯度を格納するbytebuffer
	 * @param value[double]:double型に変換した経度緯度データ
	 */
	private static void putReversedData(ByteBuffer geometoryByteBuffer, ByteBuffer doubleByteBuffer, double value) {
		// doubleByteに引数の経度・緯度のデータを格納し、反転させてgeometoryByteに格納
		doubleByteBuffer.position(0);
		byte[] data = doubleByteBuffer.putDouble(value).array();
		for ( int i = 0; i < 8; i++ ) {
			geometoryByteBuffer.put(data[7 - i]);
		}
	}

	/**
	 * 指定された位置から7バイト分の配列を反転させて、double型にして返す
	 * 
	 * @param buffer[ByteBuffer]:経度緯度のbytebuffer
	 * @param data[byte]:byte配列に変換した経度緯度情報
	 * @param position:反転開始位置
	 * @return byte配列反転後のdouble型データ
	 */
	public static double getReversedData(ByteBuffer buffer, byte[] data, int position) {
		buffer.position(0);
		for ( int i = position + 7; i >= position; i-- ) {
			buffer.put(data[i]);
		}

		buffer.position(0);
		return buffer.getDouble();
	}

}
