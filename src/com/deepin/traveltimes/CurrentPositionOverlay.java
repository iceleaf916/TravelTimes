package com.deepin.traveltimes;

import android.graphics.drawable.Drawable;

import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;

public class CurrentPositionOverlay extends ItemizedOverlay<OverlayItem> {

	public CurrentPositionOverlay(Drawable arg0, MapView arg1) {
		super(arg0, arg1);
	}
}
