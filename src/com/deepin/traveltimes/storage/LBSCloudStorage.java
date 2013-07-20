package com.deepin.traveltimes.storage;

import java.io.InputStream;
import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class LBSCloudStorage {

	// private static final String ak = "54b391822609195547115cf72df54479";
	private static final String ak = "9d0caaa9caf79a3eab0db881339bf04c";

	private static int TIME_OUT = 12000;

	public static int createDatabox(String name) throws Exception {
		int databox_id = 0;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox").setParameter("method", "create")
				.setParameter("name", name).setParameter("ak", ak);
		URI uri = builder.build();

		System.out.println("create data box uri:" + uri);

		HttpClient httpclient = new DefaultHttpClient();
		/*
		 * httpclient.getParams().setParameter(
		 * CoreConnectionPNames.CONNECTION_TIMEOUT, TIME_OUT);
		 * httpclient.getParams().setParameter( CoreConnectionPNames.SO_TIMEOUT,
		 * TIME_OUT);
		 * HttpProtocolParams.setUseExpectContinue(httpclient.getParams(),
		 * false);
		 */
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				databox_id = json.getInt("id");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return databox_id;
	}

	public static int updateDatabox(int id, String name) throws Exception {
		int status = -1;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox" + id)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				status = json.getInt("status");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return status;

	}

	public static int deleteDatabox(int id) throws Exception {
		int status = -1;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox" + id)
				.setParameter("method", "delete").setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				status = json.getInt("status");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return status;
	}

	public static JSONObject queryDatabox(int id, String scope)
			throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox/" + id)
				.setParameter("ak", ak).setParameter("scope", scope);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				json_result = new JSONObject(EntityUtils.toString(entity));
			}
			
			//EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;
	}

	public static JSONObject conditionQueryDatabox(String page_index, String page_size) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databox/")
				.setParameter("method", "list")
				.setParameter("ak", ak)
				.setParameter("page_index", page_index)
				.setParameter("page_size", page_size);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				json_result = new JSONObject(EntityUtils.toString(entity));
			}
			
			//EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;
	}

	public static JSONObject createDataboxMeta(String name, String key, String type, String databox, String magic) throws Exception {
		JSONObject json_result = null;
		
		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta").setParameter("method", "create")
				.setParameter("name", name).setParameter("property_key", key)
				.setParameter("property_type", type).setParameter("databox_id", databox)
				.setParameter("if_magic_field", magic).setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				json_result = new JSONObject(EntityUtils.toString(entity));
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}
		
		return json_result;
	}

	public static boolean creatDataboxMetaMulti(String name) throws Exception {
		return true;
	}

	public static int updateDataboxMeta(String meta_id, String name) throws Exception {
		int status = -1;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta" + meta_id)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				status = json.getInt("status");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return status;
	}

	public static JSONObject queryDataboxMeta(String meta_id) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/databoxmeta/" + meta_id)
				.setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				json_result = new JSONObject(EntityUtils.toString(entity));
			}
			
			//EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;
	}

	public static boolean conditionQueryDataboxMeta(String meta_id) throws Exception {
		return true;
	}

	public static int createPoi(String name, String orig_lat, String orig_lon, String coord_type, String databox_id) throws Exception {
		int poi_id = 0;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi"+databox_id).setParameter("method", "create")
				.setParameter("original_lat", orig_lat).setParameter("original_lon", orig_lon)
				.setParameter("original_coord_type", coord_type).setParameter("ak", ak);
		URI uri = builder.build();
		HttpClient httpclient = new DefaultHttpClient();
	
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				poi_id = json.getInt("id");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return poi_id;
	}

	public static int updatePoi(String poi_id, String name) throws Exception {
		int status = -1;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id)
				.setParameter("method", "update").setParameter("name", name)
				.setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				status = json.getInt("status");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return status;
	}

	public static int deletePoi(String poi_id) throws Exception {
		int status = -1;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id)
				.setParameter("method", "delete").setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(uri);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				JSONObject json = new JSONObject(EntityUtils.toString(entity));
				status = json.getInt("status");
			}

			//EntityUtils.consume(entity);

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

			httpclient.getConnectionManager().shutdown();
		}

		return status;
	}

	public static boolean deletePoiMulti(String name) throws Exception {
		return true;
	}

	public static JSONObject queryPoi(String poi_id, String scope) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geodata/poi/" + poi_id)
				.setParameter("ak", ak).setParameter("scope", scope);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				json_result = new JSONObject(EntityUtils.toString(entity));
			}
			
			//EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;
	}

	public static boolean conditionQueryPoi(String name) throws Exception {
		return true;
	}

	public static boolean createPoiExt(String name) throws Exception {
		return true;
	}

	public static boolean updatePoiExt(String name) throws Exception {
		return true;
	}

	public static boolean deletePoiExt(String name) throws Exception {
		return true;
	}

	public static boolean queryPoiExt(String name) throws Exception {
		return true;
	}

	public static boolean uploadPoi(String name) throws Exception {
		return true;
	}
}
