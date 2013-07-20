package com.deepin.traveltimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

/**
 * @author ycl
 * 
 */
public class TimesLine extends ListActivity {
	// private List<String> data = new ArrayList<String>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.timesline,
				new String[]{"title","info","img"},
				new int[]{R.id.title,R.id.info,R.id.img});
		setListAdapter(adapter);
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "2013年 07月 20日");
		map.put("info", "democoffee,百度云编程大赛，真的很精彩，加油哦~~");
		map.put("img", R.drawable.imgtest1);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "2013年 07月 15日");
		map.put("info", "深之度的工作氛围真的很好呀~~~");
		map.put("img", R.drawable.imgtest2);
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("title", "2013年 07月 02日");
		map.put("info", "深之度的新公司哦~~  高端大气上档次~~~");
		map.put("img", R.drawable.imgtest4);
		list.add(map);
		
		return list;
	}
}
