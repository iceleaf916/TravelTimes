package com.deepin.traveltimes.storage;

public class LBSCloudSearch {

	private static final String ak = "9d0caaa9caf79a3eab0db881339bf04c";
/*
	public static JSONObject searchRegion(String filter, String databox_id,
			String location, String scope) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geosearch/poi").setParameter("location", location)
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

			// EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;

	}

	public static JSONObject searchNearby(String region, String filter,
			String scope) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geosearch/poi").setParameter("region", region)
				.setParameter("filter", filter).setParameter("scope", scope)
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

			// EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;

	}

	public static JSONObject searchBounds(String bounds, String filter,
			String databox, String scope) throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geosearch/poi").setParameter("databox", databox)
				.setParameter("filter", filter).setParameter("scope", scope)
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

			// EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;

	}

	public static JSONObject searchDetail(String poi_id, String scope)
			throws Exception {
		JSONObject json_result = null;

		URIBuilder builder = new URIBuilder();
		builder.setScheme("http").setHost("api.map.baidu.com")
				.setPath("/geosearch/detail").setParameter("id", poi_id)
				.setParameter("scope", scope).setParameter("ak", ak);
		URI uri = builder.build();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			HttpGet httpget = new HttpGet(uri);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				json_result = new JSONObject(EntityUtils.toString(entity));
			}

			// EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return json_result;

	}
	*/
}

