package com.deepin.traveltimes.storage;

import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.net.Uri;
import android.util.Log;

public class StorageUtil {

	public static JSONObject requestPostResult(String uri) throws Exception {
		JSONObject result = null;

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = new JSONObject(EntityUtils.toString(entity, "UTF-8"));
			}
			// EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return result;
	}

	public static JSONObject requestGetResult(String uri) throws Exception {
		JSONObject result = null;

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = new JSONObject(EntityUtils.toString(entity, "UTF-8"));
			}
			// EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return result;
	}

	public static String buildUri(String path, HashMap<String, String> query)
			throws Exception {
		try {
			Uri.Builder builder = new Uri.Builder();
			builder.scheme("http");
			builder.authority("api.map.baidu.com");
			builder.path(path);

			for (String key : query.keySet()) {
				builder.appendQueryParameter(key, query.get(key));
			}

			return builder.build().toString();

		} catch (Exception e) {
			Log.e("buildUri", "build uri failed");
			return null;
		}

	}

}
