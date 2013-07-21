package com.deepin.traveltimes;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

	private Bitmap getBitmap() {
		Bitmap[] bitmaps = new Bitmap[6];
		bitmaps[0] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.img14);
		bitmaps[1] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.img15);
		bitmaps[2] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.img16);
		bitmaps[3] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.img5);
		bitmaps[4] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.img6);
		bitmaps[5] = BitmapFactory.decodeResource(activity.getResources(),
				R.drawable.img7);

		return bitmaps[new Random().nextInt(6)];
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
					// Intent intent = new Intent(
					// MotionAnimationListener.this.activity,
					// MapDetailActivity.class);
					// MotionAnimationListener.this.activity.startActivity(intent);
				}
			});
			recordPic.setImageBitmap(getBitmap());
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
