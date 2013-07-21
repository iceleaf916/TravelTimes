package com.deepin.traveltimes;

import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

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

	GeoPoint[] poliLinePoints = null;
	private Button backBtn = null;

	private Handler mHandler = null;

	private ImageView currentPositionMarker;
	private TranslateAnimation ta;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("99D4F50C2453175CB601D6015F6FCEBFFA067C9F", null);
		setContentView(R.layout.activity_map);

		mHandler = new MotionHandler();
		currentPositionMarker = new ImageView(this);
		currentPositionMarker.setImageResource(R.drawable.marker3);

		mMapView = (MapView) findViewById(R.id.bmapsView);
		mMapController = mMapView.getController();

		setupPoliLinePoints();
		drawPoliLines(poliLinePoints);

		mMapController.setCenter(poliLinePoints[0]);// 设置地图中心点
		mMapController.setZoom(12);// 设置地图zoom级别

		MapView.LayoutParams params = new MapView.LayoutParams(100, 100,
				poliLinePoints[0], MapView.LayoutParams.BOTTOM_CENTER);
		mMapView.addView(currentPositionMarker, params);

		backBtn = (Button) findViewById(R.id.map_title_back);
		backBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				MapActivity.this.finish();
			}
		});
		new MotionThread(mHandler).start();
	}

	private void setupPoliLinePoints() {
		poliLinePoints = new GeoPoint[10];
		poliLinePoints[0] = MapUtils.toGeoPoint(114.29027, 30.611909);
		poliLinePoints[1] = MapUtils.toGeoPoint(114.213231, 30.624838);
		poliLinePoints[2] = MapUtils.toGeoPoint(114.174137, 30.606936);
		poliLinePoints[3] = MapUtils.toGeoPoint(114.182186, 30.539277);
		poliLinePoints[4] = MapUtils.toGeoPoint(114.312117, 30.460614);
		poliLinePoints[5] = MapUtils.toGeoPoint(114.460445, 30.475555);
		poliLinePoints[6] = MapUtils.toGeoPoint(114.456995, 30.583062);
		poliLinePoints[7] = MapUtils.toGeoPoint(114.415601, 30.663613);
		poliLinePoints[8] = MapUtils.toGeoPoint(114.314416, 30.678523);
		poliLinePoints[9] = MapUtils.toGeoPoint(114.29027, 30.611909);
	}

	private void P2P(GeoPoint start, GeoPoint end) {
		Point startPoint = mMapView.getProjection().toPixels(start, null);
		Point endPoint = mMapView.getProjection().toPixels(end, null);

		ta = new TranslateAnimation(0, endPoint.x - startPoint.x, 0, endPoint.y
				- startPoint.y);
		ta.setAnimationListener(new MotionAnimationListener(this, mMapView,
				end, this.currentPositionMarker));
		ta.setFillBefore(true);
		ta.setFillEnabled(true);
		ta.setDuration(2000);
		ta.setInterpolator(new LinearInterpolator());
		currentPositionMarker.startAnimation(ta);
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

	@SuppressLint("HandlerLeak")
	class MotionThread extends Thread {
		private Handler handler = null;

		public MotionThread(Handler mHandler) {
			this.handler = mHandler;
		}

		@Override
		public void run() {
			for (int i = 0; i < poliLinePoints.length - 1; i++) {
				try {
					TimeUnit.MILLISECONDS.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				handler.sendEmptyMessage(i);

				synchronized (MapActivity.this) {
					try {
						MapActivity.this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}
	}

	class MotionHandler extends Handler {
		@SuppressLint("HandlerLeak")
		@Override
		public void handleMessage(Message msg) {
			P2P(poliLinePoints[msg.what], poliLinePoints[msg.what + 1]);
		}
	}
}
