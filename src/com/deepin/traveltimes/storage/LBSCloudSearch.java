package com.deepin.traveltimes.storage;

import java.util.HashMap;

import org.json.JSONObject;

import android.util.Log;

public class LBSCloudSearch {

	private static final String ak = "9d0caaa9caf79a3eab0db881339bf04c";

<<<<<<< HEAD
	public JSONObject searchRegion(String filter, String databox_id,
=======
	public static JSONObject searchRegion(String filter, String databox_id,
>>>>>>> origin/develop
			String location, String scope) throws Exception {

		String path = "/geosearch/poi";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("location", location);
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = StorageUtil.buildUri(path, query);

		Log.e("search region uri", uri);

		try {
			return StorageUtil.requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

<<<<<<< HEAD
	public JSONObject searchNearby(String region, String filter,
=======
	public static JSONObject searchNearby(String region, String filter,
>>>>>>> origin/develop
			String scope) throws Exception {

		String path = "/geosearch/poi";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("region", region);
		query.put("filter", filter);
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = StorageUtil.buildUri(path, query);

		Log.e("search nearby uri", uri);

		try {
			return StorageUtil.requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

<<<<<<< HEAD
	public JSONObject searchBounds(String bounds, String filter,
=======
	public static JSONObject searchBounds(String bounds, String filter,
>>>>>>> origin/develop
			String databox, String scope) throws Exception {
		String path = "/geosearch/poi";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("databox", databox);
		query.put("filter", filter);
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = StorageUtil.buildUri(path, query);

		Log.e("search bounds uri", uri);

		try {
			return StorageUtil.requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

<<<<<<< HEAD
	public JSONObject searchDetail(String poi_id, String scope)
=======
	public static JSONObject searchDetail(String poi_id, String scope)
>>>>>>> origin/develop
			throws Exception {

		String path = "/geosearch/detail";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("id", poi_id);
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = StorageUtil.buildUri(path, query);

		try {
			return StorageUtil.requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

}
