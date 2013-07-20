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
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.timesline, new String[] { "title", "img", "info",
						"img_time", "text_time", "text_coordinates",
						"img_like", "img_share" }, new int[] { R.id.title,
						R.id.img,  R.id.info, R.id.img_time,
						R.id.text_time, R.id.text_coordinates, R.id.img_like,
						R.id.img_share });
		setListAdapter(adapter);

		img_katong = (ImageView) this.findViewById(R.id.img_katong);
		// img_katong.setOnClickListener((OnClickListener) this);
	}

	// private void onClick(View v) {
	// if(v==img_katong){
	// Intent intent = new Intent(TimesLine.this, KaTong.class);
	// startActivity(intent);
	// // this.finish();
	// }
	// }

	// JSONObject dataJson=new
	// JSONObject("{"response":{"data":[{"address":"南京市游乐园","province":"江苏","district":"玄武区","city":"南京"}]},"status":"ok"}");
	public void getdata_JSON(JSONObject js) {

	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "@string/info1");
		map.put("img", R.drawable.img1);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like1);
		map.put("img_share", R.drawable.img_share1);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "0720");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img2);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like2);
		map.put("img_share", R.drawable.img_share2);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img3);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like3);
		map.put("img_share", R.drawable.img_share3);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img4);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like4);
		map.put("img_share", R.drawable.img_share4);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img5);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like5);
		map.put("img_share", R.drawable.img_share5);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img6);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like6);
		map.put("img_share", R.drawable.img_share6);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img7);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like7);
		map.put("img_share", R.drawable.img_share7);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		map.put("title", "2013   年   07   月   20   日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.img8);
		map.put("img_time", R.drawable.img_time);
		map.put("img_like", R.drawable.img_like8);
		map.put("img_share", R.drawable.img_share8);
		map.put("text_time", "11:13");
		map.put("text_coordinates", "E156.23  S63.52");
		list.add(map);

		return list;
	}

	// ListView 中某项被选中后的逻辑
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Log.v("MyListView-click", (String) mData.get(position).get("title"));
		showInfo();
	}

	/**
	 * listview中点击按键弹出对话框
	 */
	public void showInfo() {
		Intent intent = new Intent(TimesLine.this, ImgAll.class);
		startActivity(intent);
	}

}
