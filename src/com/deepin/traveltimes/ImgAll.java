package com.deepin.traveltimes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
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
public class ImgAll extends ListActivity {
	// private List<String> data = new ArrayList<String>();
	private List<Map<String, Object>> mData;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mData = getData();
		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.timesline, new String[] { "img", "info", "img_time",
						"text_time", "text_coordinates", "img_like",
						"img_share" }, new int[] { R.id.img, R.id.info,
						R.id.img_time, R.id.text_time, R.id.text_coordinates,
						R.id.img_like, R.id.img_share });
		setListAdapter(adapter);

	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String[] infos = {
				"democoffee,百度云编程大赛，真的很精彩，加油哦~~",
				"作为一个人，如果你生活的地方发生战争，那么你很不幸。作为一名记者，如果你在职业生涯中遭遇过一场战争，并且身处其中，那是你的幸运。",
				"虽然我一直说我是因为想成为战地记者才成为记者的，但是当梦想变为现实，我却不得不忐忑。",
				"还没启程，我就已经给自己打包装好了巨大的压力，并且一路上不离不弃。",
				"我猜想，每名战地记者心中都是不安定的。一度想写个关于利比亚的爱情故事。",
				"那些关于爱情的所有过瘾的元素都可以放在这个故事里。可若是自己去经历，就是肝肠寸断。",
				"每个人的生命里，或许都会有这样一段时光——它曾如此美好如此真实，但它逝去以后，却又如梦似幻，每每回忆都觉太过炫目以致无法相信是否为梦境。",
				"其中的每一个瞬间，都在以后的人生中投下漫长的投影，以至成为永恒。",
				"短短四天，远离喧嚣的人世，行走在这颗叫地球的星球上最出世最奇绝的仙境里，造物主似乎把我此生所能见到的所有美好都在那几天呈现了出来。",
				"于是沉醉，于是刻骨。我不知道是否真的有天堂，但如果有，想来也一定比不上这里吧？",
				};
		int[] imgs = {
				R.drawable.img6,
				R.drawable.img4,
				R.drawable.img9,
				R.drawable.img5,
				R.drawable.img2,
				R.drawable.img7,
				R.drawable.img1,
				R.drawable.img8,
				R.drawable.img3,
				R.drawable.img10,
				};
		int img_times =R.drawable.img_time;
		int[] img_likes = {
				R.drawable.img_like1,
				R.drawable.img_like2,
				R.drawable.img_like3,
				R.drawable.img_like4,
				R.drawable.img_like5,
				R.drawable.img_like6,
				R.drawable.img_like7,
				R.drawable.img_like8,
				R.drawable.img_like9,
				R.drawable.img_like10,
				};
		int[] img_shares = { 
				R.drawable.img_share1,
				R.drawable.img_share2,
				R.drawable.img_share3,
				R.drawable.img_share4,
				R.drawable.img_share5,
				R.drawable.img_share6,
				R.drawable.img_share7,
				R.drawable.img_share8,
				R.drawable.img_share9,
				R.drawable.img_share10,
				};
		String[] text_times = {
				"11:13",
				"14:33",
				"21:43",
				"11:23",
				"17:13",
				"20:3",
				"23:30",
				"9:2",
				"10:4",
				"11:13",
				};
		String[] text_coordinateses = {
				"湖北  武汉",
				"湖北  武汉",
				"湖北  宜昌",
				"湖北  宜昌",
				"湖北  孝感",
				"湖北  孝感",
				"湖北  随州",
				"湖北  随州",
				"湖北  襄阳",
				"湖北  襄阳",
				};

		int num = 10;

		for (int i = 0; i < num; i++) {
			Map<String, Object> map;
			map = new HashMap<String, Object>();
			map.put("info", infos[i]);
			map.put("img", imgs[i]);
			map.put("img_time", img_times);
			map.put("img_like", img_likes[i]);
			map.put("img_share", img_shares[i]);
			map.put("text_time", text_times[i]);
			map.put("text_coordinates", text_coordinateses[i]);
			list.add(map);
		}

		return list;
	}

	// ListView 中某项被选中后的逻辑
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		showInfo();
	}

	/**
	 * listview中点击按键弹出对话框
	 */
	public void showInfo() {
		new AlertDialog.Builder(this).setTitle("时光文字")
				.setMessage("完美时光的文字，让回忆更清澈......")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				}).show();

	}

}
