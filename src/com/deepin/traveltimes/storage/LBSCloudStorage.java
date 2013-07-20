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

public class LBSCloudStorage {

	// private static final String ak = "54b391822609195547115cf72df54479";
	private static final String ak = "9d0caaa9caf79a3eab0db881339bf04c";

	private static JSONObject requestPostResult(String uri) throws Exception {
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

	private static JSONObject requestGetResult(String uri) throws Exception {
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

	private static String buildUri(String path, HashMap<String, String> query)
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

	public static int createDatabox(String name) throws Exception {
		String path = "/geodata/databox";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "create");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("create databox uri", uri);

		JSONObject result = requestPostResult(uri);
		try {
			if (result.getInt("status") == 0) {
				return result.getInt("id");
			} else {
				throw new Exception(
						"create data box response status not equals zero");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static int updateDatabox(String id, String name) throws Exception {
		String path = "/geodata/databox/" + id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "update");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("update databox uri", uri);

		JSONObject result = requestPostResult(uri);
		try {
			return result.getInt("status");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int deleteDatabox(String id) throws Exception {
		String path = "/geodata/databox/" + id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "delete");
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("delete databox uri", uri);
		
		JSONObject result = requestPostResult(uri);
		try {
			return result.getInt("status");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return -1;
	}

	public static JSONObject queryDatabox(String id, String scope)
			throws Exception {

		String path = "/geodata/databox/" + id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("query databox uri", uri);
		
		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static JSONObject conditionQueryDatabox(String page_index,
			String page_size) throws Exception {
		String path = "/geodata/databox";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "list");
		query.put("page_index", page_index);
		query.put("page_size", page_size);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("condition query databox uri", uri);

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject createDataboxMeta(String name, String key,
			String type, String databox, String magic) throws Exception {
		
		String path = "/geodata/databoxmeta";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "create");
		query.put("property_name", name);
		query.put("property_key", key);
		query.put("property_type", type);
		query.put("databox_id", databox);
		query.put("if_magic_field", magic);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("create databox meta uri", uri);
		
		try {
			return requestPostResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public static JSONObject creatDataboxMetaMulti(String name)
			throws Exception {
		String path = "/geodata/databoxmeta";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "create");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("create databox meta multi uri", uri);
		try {
			return requestPostResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public static int updateDataboxMeta(String meta_id, String name,
			boolean magic) throws Exception {
		String path = "/geodata/databoxmeta/" + meta_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "update");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("update databox meta  uri", uri);

		try {
			JSONObject result = requestPostResult(uri);

			return result.getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return -1;
	}

	public static JSONObject queryDataboxMeta(String meta_id) throws Exception {
		String path = "/geodata/databoxmeta/" + meta_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("query databox meta  uri", uri);
		
		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public static JSONObject conditionQueryDataboxMeta(String meta_id)
			throws Exception {
		
		String path = "/geodata/databoxmeta/" + meta_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("condition query databox meta  uri", uri);
		
		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public static int createPoi(String name, String orig_lat, String orig_lon,
			String coord_type, String databox_id) throws Exception {
	
		String path = "/geodata/poi/" + databox_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "create");
		query.put("original_lat", orig_lat);
		query.put("original_lon", orig_lon);
		query.put("original_coord_type", coord_type);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("create poi uri", uri);
		
		try {
			JSONObject result = requestPostResult(uri);
			if (result.getInt("status") == 0) {
				return result.getInt("id");
			} else {
				new Exception("create poi status not equals 0");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return 0;
	}

	public static int updatePoi(String poi_id, String name) throws Exception {
		String path = "/geodata/poi/" + poi_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "update");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("update poi uri", uri);		

		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return -1;
	}

	public static int deletePoi(String poi_id) throws Exception {
		String path = "/geodata/poi/" + poi_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "delete");
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("delete poi uri", uri);	
		
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return -1;
	}

	public static int deletePoiMulti(String poi_id) throws Exception {
		String path = "/geodata/poi/" + poi_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "delete");
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("delete poi multi uri", uri);	
		
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return -1;
	}

	public static JSONObject queryPoi(String poi_id, String scope) throws Exception{
		String path = "/geodata/poi/" + poi_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("query poi  uri", uri);	

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public static JSONObject conditionQueryPoi(String poi_id, String scope)
			throws Exception {
			String path = "/geodata/poi/" + poi_id;
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("scope", scope);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("condition query poi  uri", uri);	

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

	public static int createPoiExt(String name) throws Exception {
		String path = "/geodata/poi/";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("condition  poi  ext uri", uri);	
		
		try {
			JSONObject result = requestPostResult(uri);
			if (result.getInt("status") == 0) {
				return result.getInt("id");
			} else {
				throw new Exception("create poi status not equals 0");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return 0;
	}

	public static int updatePoiExt(String name) throws Exception {
		String path = "/geodata/poi/";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "update");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("update poi  ext uri", uri);	

		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return -1;
	}

	public static int deletePoiExt(String name) throws Exception {
		String path = "/geodata/poi";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "delete");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("delete poi  ext uri", uri);	
		
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		}

		return -1;
	}

	public static JSONObject queryPoiExt(String name) throws Exception {
		String path = "/geodata/poi";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "list");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		Log.e("query poi  ext uri", uri);	
		
		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}	
}
