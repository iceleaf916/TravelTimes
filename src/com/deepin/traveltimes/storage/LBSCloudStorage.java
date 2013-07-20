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

public class LBSCloudStorage {

	private static final String ak = "9d0caaa9caf79a3eab0db881339bf04c";

	private static HttpClient httpclient = new DefaultHttpClient();
	
	public void shutdownHttpClient() throws Exception {
		try {
			httpclient.getConnectionManager().shutdown();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private static JSONObject requestPostResult(String uri) throws Exception {
		JSONObject result = null;

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

		} 
	
		return result;		
	}

	private static JSONObject requestGetResult(String uri) throws Exception {
		JSONObject result = null;

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

		} 
		return result;
	}

	private static String buildUri(String path, HashMap<String, String> query) throws Exception {
		Uri.Builder builder = new Uri.Builder();
		builder.scheme("http");
		builder.authority("api.map.baidu.com");
		builder.path(path);
		
		for (String key : query.keySet()) {
			builder.appendQueryParameter(key, query.get(key));
		}

		return builder.build().toString();
	}

	public static int createDatabox(String name) throws Exception {
/*
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox").setParameter("method", "create")
				.setParameter("name", name).setParameter("ak", ak);
		URI uri = builder.build();
		
*/
		String path = "/geodata/databox";
		HashMap<String, String> query = new HashMap<String, String>();
		query.put("method", "create");
		query.put("name", name);
		query.put("ak", ak);
		String uri = buildUri(path, query);

		System.out.println("uri:" + uri);

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
}
/*
	
	public static int updateDatabox(String id, String name) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox" + id)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();

		JSONObject result = requestPostResult(uri);
		try {
			return result.getInt("status");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public static int deleteDatabox(String id) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox" + id)
				.setParameter("method", "delete").setParameter("ak", ak);
		URI uri = builder.build();

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

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox/" + id).setParameter("ak", ak)
				.setParameter("scope", scope);
		URI uri = builder.build();

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static JSONObject conditionQueryDatabox(String page_index,
			String page_size) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox/").setParameter("method", "list")
				.setParameter("ak", ak).setParameter("page_index", page_index)
				.setParameter("page_size", page_size);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static JSONObject createDataboxMeta(String name, String key,
			String type, String databox, String magic) throws Exception {

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta")
				.setParameter("method", "create").setParameter("name", name)
				.setParameter("property_key", key)
				.setParameter("property_type", type)
				.setParameter("databox_id", databox)
				.setParameter("if_magic_field", magic).setParameter("ak", ak);
		URI uri = builder.build();
		try {
			return requestPostResult(uri);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return null;
	}

	public static JSONObject creatDataboxMetaMulti(String name) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta")
				.setParameter("method", "create").setParameter("name", name);

		URI uri = builder.build();
		try {
			return requestPostResult(uri);
			
		} catch (Exception e) {
			e.printStackTrace();

		}
		
		return null;
	}

	public static int updateDataboxMeta(String meta_id, String name, boolean magic)
			throws Exception {
		int status = -1;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta" + meta_id)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();

		try {
			JSONObject result = requestPostResult(uri);
			
			return result.getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return -1;
	}

	public static JSONObject queryDataboxMeta(String meta_id) throws Exception {
		
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta/" + meta_id)
				.setParameter("ak", ak);
		URI uri = builder.build();

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return null;
	}

	public static JSONObject conditionQueryDataboxMeta(String meta_id)
			throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta/" + meta_id)
				.setParameter("ak", ak);
		URI uri = builder.build();

		try {
			return requestGetResult(uri);

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return null;
	}

	public static int createPoi(String name, String orig_lat, String orig_lon,
			String coord_type, String databox_id) throws Exception {

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi" + databox_id)
				.setParameter("method", "create")
				.setParameter("original_lat", orig_lat)
				.setParameter("original_lon", orig_lon)
				.setParameter("original_coord_type", coord_type)
				.setParameter("ak", ak);
		URI uri = builder.build();
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

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();
		
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return -1;
	}

	public static int deletePoi(String poi_id) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id)
				.setParameter("method", "delete").setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return -1;
	}

	public static int deletePoiMulti(String poi_id) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id)
				.setParameter("method", "delete").setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return -1;
	}

	public static JSONObject queryPoi(String poi_id, String scope)
			throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id).setParameter("ak", ak)
				.setParameter("scope", scope);
		URI uri = builder.build();

		try {
			return requestGetResult(uri);
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return null;
	}

	public static JSONObject conditionQueryPoi(String poi_id, String scope) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id).setParameter("ak", ak)
				.setParameter("scope", scope);
		URI uri = builder.build();

		try {
			return requestGetResult(uri);
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return null;
	}

	public static int createPoiExt(String name) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi" + name)
				.setParameter("method", "create")
				.setParameter("ak", ak);
		URI uri = builder.build();
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
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + name)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();
		
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return -1;
	}

	public static int deletePoiExt(String name) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + name)
				.setParameter("method", "delete").setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			return requestPostResult(uri).getInt("status");

		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return -1;
	}

	public static JSONObject queryPoiExt(String name) throws Exception {
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + name).setParameter("ak", ak);
		URI uri = builder.build();

		try {
			return requestGetResult(uri);
			
		} catch (Exception e) {
			e.printStackTrace();

		} 
		
		return null;
	}

	public static boolean uploadPoi(String name) throws Exception {
		return true;
	}
}

*/
