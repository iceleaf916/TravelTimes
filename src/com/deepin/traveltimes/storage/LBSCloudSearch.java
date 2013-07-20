package com.deepin.traveltimes.storage;

import java.util.HashMap;

import org.json.JSONObject;

import android.util.Log;

public class LBSCloudSearch {

	private static final String ak = "9d0caaa9caf79a3eab0db881339bf04c";

	public JSONObject searchRegion(String filter, String databox_id,
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

	public JSONObject searchNearby(String region, String filter,
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

	public JSONObject searchBounds(String bounds, String filter,
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

	public JSONObject searchDetail(String poi_id, String scope)
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
