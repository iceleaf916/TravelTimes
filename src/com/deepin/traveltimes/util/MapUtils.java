package com.deepin.traveltimes.util;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.baidu.platform.comapi.map.Projection;

public class MapUtils {
	public static GeoPoint toGeoPoint(double longitude, double latitude) {
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

	public static Drawable resizeImage(Bitmap bitmap, int w, int h) {

		// load the origial Bitmap
		Bitmap BitmapOrg = bitmap;

		int width = BitmapOrg.getWidth();
		int height = BitmapOrg.getHeight();
		int newWidth = w;
		int newHeight = h;

		// calculate the scale
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;

		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the Bitmap
		matrix.postScale(scaleWidth, scaleHeight);
		// if you want to rotate the Bitmap
		// matrix.postRotate(45);

		// recreate the new Bitmap
		Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0, width,
				height, matrix, true);

		// make a Drawable from Bitmap to allow to set the Bitmap
		// to the ImageView, ImageButton or what ever
		return new BitmapDrawable(resizedBitmap);
	}
}
