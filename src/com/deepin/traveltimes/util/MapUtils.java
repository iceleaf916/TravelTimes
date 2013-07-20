package com.deepin.traveltimes.util;

import com.baidu.platform.comapi.basestruct.GeoPoint;

public class MapUtils {
	public static GeoPoint toGeoPoint(double latitude, double longitude) {
		return new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
	}
}
