package com.deepin.traveltimes;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.Geometry;
import com.baidu.mapapi.map.Graphic;
import com.baidu.mapapi.map.GraphicsOverlay;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Symbol;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.deepin.traveltimes.util.MapUtils;

public class MapActivity extends Activity {
	private BMapManager mBMapMan = null;
	private MapView mMapView = null;
	private MapController mMapController = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("99D4F50C2453175CB601D6015F6FCEBFFA067C9F", null);
		setContentView(R.layout.activity_map);

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapView.setBuiltInZoomControls(true);
		// 设置启用内置的缩放控件
		mMapController = mMapView.getController();
		// 得到mMapView的控制权,可以用它控制和驱动平移和缩放
		GeoPoint point = new GeoPoint((int) (30.47657 * 1E6),
				(int) (114.40931 * 1E6));

		mMapController.setCenter(point);// 设置地图中心点
		mMapController.setZoom(15);// 设置地图zoom级别

		GeoPoint[] poliLinePoints = new GeoPoint[5];
		poliLinePoints[0] = MapUtils.toGeoPoint(30.4445, 114.4258);
		poliLinePoints[1] = MapUtils.toGeoPoint(30.44015, 114.42149);
		poliLinePoints[2] = MapUtils.toGeoPoint(30.44915, 114.42095);
		poliLinePoints[3] = MapUtils.toGeoPoint(30.4651, 114.4203);
		poliLinePoints[4] = MapUtils.toGeoPoint(30.48540, 114.41042);

		drawPoliLines(poliLinePoints);
	}

	protected void drawPoliLines(GeoPoint[] points) {
		/*
		 * 显示折线
		 */
		Geometry poliLineGeometry = new Geometry();
		poliLineGeometry.setPolyLine(points);

		Symbol poliLineSymbol = new Symbol();// 创建样式
		Symbol.Color poliLineColor = poliLineSymbol.new Color();// 创建颜色
		poliLineColor.red = 255;// 设置颜色的红色分量
		poliLineColor.green = 0;// 设置颜色的绿色分量
		poliLineColor.blue = 0;// 设置颜色的蓝色分量
		poliLineColor.alpha = 126;// 设置颜色的alpha值
		poliLineSymbol.setLineSymbol(poliLineColor, 5);

		Graphic palaceGraphic = new Graphic(poliLineGeometry, poliLineSymbol);

		GraphicsOverlay mGraphicsOverlay = new GraphicsOverlay(mMapView);
		long poliLineID = mGraphicsOverlay.setData(palaceGraphic);

		/*
		 * 显示折点
		 */
		long[] pointsIDs = new long[points.length];
		int index = 0;
		for (GeoPoint geoPoint : points) {
			Geometry pointGeometry = new Geometry();
			pointGeometry.setPoint(geoPoint, 10);

			Symbol pointSymbol = new Symbol();// 创建样式
			Symbol.Color pointColor = pointSymbol.new Color();// 创建颜色
			pointColor.red = 0;// 设置颜色的红色分量
			pointColor.green = 255;// 设置颜色的绿色分量
			pointColor.blue = 0;// 设置颜色的蓝色分量
			pointColor.alpha = 126;// 设置颜色的alpha值
			pointSymbol.setPointSymbol(pointColor);

			Graphic pointGraphic = new Graphic(pointGeometry, pointSymbol);
			pointsIDs[index] = mGraphicsOverlay.setData(pointGraphic);
			index++;
		}

		mMapView.getOverlays().add(mGraphicsOverlay);
		mMapView.refresh();
	}

	@Override
	protected void onDestroy() {
		mMapView.destroy();
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		mMapView.onPause();
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		mMapView.onResume();
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}
}
