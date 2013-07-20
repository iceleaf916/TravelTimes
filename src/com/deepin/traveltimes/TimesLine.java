package com.deepin.traveltimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * @author ycl
 * 
 */
public class TimesLine extends ListActivity {
	// private List<String> data = new ArrayList<String>();
		
	private List<Map<String, Object>> mData;
	private ImageView img_katong;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.timesline,
				new String[]{"title","info","img"},
				new int[]{R.id.title,R.id.info,R.id.img});
		setListAdapter(adapter);
		
		img_katong = (ImageView)this. findViewById(R.id.img_katong);
//		img_katong.setOnClickListener((OnClickListener) this);
	}

//	private void onClick(View v) {
//		if(v==img_katong){
//			Intent intent = new Intent(TimesLine.this, KaTong.class);
//			startActivity(intent);
////			this.finish();
//		}
//		}
	
	JSONObject  dataJson=new JSONObject("{"response":{"data":[{"address":"南京市游乐园","province":"江苏","district":"玄武区","city":"南京"}]},"status":"ok"}");
	public void getdata_JSON(JSONObject js)
	{
		
	}
	
	
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.imgtest1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "2013   年   07   月   15   日");
		map.put("info", "深之度的工作氛围真的很好呀~~~");
		map.put("img", R.drawable.imgtest2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "2013   年   07   月   02   日");
		map.put("info", "深之度的新公司哦~~  高端大气上档次~~~");
		map.put("img", R.drawable.imgtest4);
		list.add(map);
		
		return list;
	}
	
	
	// ListView 中某项被选中后的逻辑
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Log.v("MyListView-click", (String)mData.get(position).get("title"));
		showInfo();
	}
	
	/**
	 * listview中点击按键弹出对话框
	 */
	public void showInfo(){
		Intent intent = new Intent(TimesLine.this, ImgAll.class);
		startActivity(intent);
	}
	
	
	
	
}
