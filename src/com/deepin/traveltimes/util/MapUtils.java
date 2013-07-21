package com.deepin.traveltimes.util;

import java.util.ArrayList;

import android.graphics.Point;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;

public class MapUtils {
	public static GeoPoint toGeoPoint(double latitude, double longitude) {
		return new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
	}

	public static long getPointDistance(Point start, Point end) {
		return (long) Math.sqrt(Math.pow(start.x - end.x, 2)
				+ Math.pow(start.y - end.y, 2));
	}

	public static ArrayList<GeoPoint> getGeoPath(MapView map, GeoPoint start,
			GeoPoint end) {
		ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();

		Projection mProjection = map.getProjection();
		Point startPoint = mProjection.toPixels(start, null);
		Point endPoint = mProjection.toPixels(end, null);

		System.out.println("start : " + startPoint.x + ", " + startPoint.y);
		System.out.println("start : " + endPoint.x + ", " + endPoint.y);

		long pointDistance = getPointDistance(startPoint, endPoint);
		System.out.println("pingfang" + pointDistance);
		int distanceX = endPoint.x - startPoint.x;
		int distanceY = endPoint.y - startPoint.y;

		for (int i = 0; i < pointDistance / 5; i++) {
			double percent = 5.0 * (i + 1) / pointDistance;
			int tempX = (int) (startPoint.x + percent * distanceX);
			int tempY = (int) (startPoint.y + percent * distanceY);

			points.add(mProjection.fromPixels(tempX, tempY));
		}

		return points;
	}
}
