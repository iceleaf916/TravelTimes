package com.deepin.traveltimes;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

public class MapDetailActivity extends Activity {
	private ImageView picView;
	private ImageView avatar;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_detail);

		Bitmap origin = BitmapFactory.decodeResource(getResources(),
				R.drawable.pic);
		int picWidth = origin.getWidth();
		int picHeight = origin.getHeight();

		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;
		int height = metric.heightPixels;

		picView = (ImageView) findViewById(R.id.map_detail_pic);
	}
}
