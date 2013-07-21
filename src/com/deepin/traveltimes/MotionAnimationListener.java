package com.deepin.traveltimes;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.baidu.mapapi.map.MapView;
import com.baidu.platform.comapi.basestruct.GeoPoint;

public class MotionAnimationListener implements AnimationListener {
	private GeoPoint point;
	private Activity activity;
	private ImageView imageView;
	private MapView mapView;

	private static ImageView recordPic = null;

	public MotionAnimationListener(Activity activity, MapView mapView,
			GeoPoint point, ImageView imageView) {
		this.point = point;
		this.activity = activity;
		this.imageView = imageView;
		this.mapView = mapView;
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		synchronized (this.activity) {
			mapView.getController().setCenter(this.point);

			MapView.LayoutParams markerParams = new MapView.LayoutParams(100,
					100, this.point, MapView.LayoutParams.BOTTOM_CENTER);
			imageView.setLayoutParams(markerParams);

			MapView.LayoutParams picParams = new MapView.LayoutParams(320, 240,
					this.point, 0, -80, MapView.LayoutParams.BOTTOM_CENTER);
			recordPic = new ImageView(this.activity);
			recordPic.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(
							MotionAnimationListener.this.activity,
							MapDetailActivity.class);
					MotionAnimationListener.this.activity.startActivity(intent);
				}
			});
			recordPic.setImageResource(R.drawable.pic);
			mapView.addView(recordPic, picParams);

			this.activity.notifyAll();
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
	}

	@Override
	public void onAnimationStart(Animation animation) {
		if (null != recordPic) {
			mapView.removeView(recordPic);
		}
	}

}
