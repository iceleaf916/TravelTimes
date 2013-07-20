package com.deepin.traveltimes.social;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class TravelTimesPcs {
	private final static String appKey = "mt1PUrn51Zvj86n3ySgrvT0I";
	private final static String AUTHORIZE_URL = "https://openapi.baidu.com/social/oauth/2.0/authorize";
	private final static String INFO_URL = "https://openapi.baidu.com/social/api/2.0/user/info";
	private final static String BIND_URL = "https://openapi.baidu.com/social/api/2.0/user/bind";
	private final static String UNBIND_URL = "https://openapi.baidu.com/social/api/2.0/user/unbind";
	private final static String BIND_STATUS_URL = "https://openapi.baidu.com/social/api/2.0/user/bind_status";
	private final static String SHARE_URL = "https://openapi.baidu.com/social/api/2.0/user/share";
	private final static String SHARE_BATCH_URL = "https://openapi.baidu.com/social/api/2.0/user/share_batch";

	public TravelTimesPcs (String platform) throws JSONException {
		String access_token;
		try {
			access_token = getAccessToken(platform);
		} catch (JSONException e) {
			e.printStackTrace();
			access_token = "";
		}
		
	}
	
	public String getAccessToken(String platform) throws JSONException {
		HttpGet httpGet = new HttpGet(AUTHORIZE_URL);

		HttpClient client = new DefaultHttpClient(new BasicHttpParams()
				.setParameter("client_id", appKey)
				.setParameter("response_type", "token")
				.setParameter("redirect_url", "oob")
				.setParameter("state", "anticsrfstring")
				.setParameter("display", "mobile")
				.setParameter("client_type", "android")
				.setParameter("media_type", platform));
		HttpResponse response;
		try {
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String json_string = EntityUtils.toString(entity, "utf-8");
			JSONObject json = new JSONObject(json_string);

			if (json.has("access_token")) {
				return json.getString("access_token");
			} else {
				/* throw new JSONException(); */
				return null;
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	public JSONObject quotaPcs(String method, String access_token) {
		HttpGet httpGet = new HttpGet("www.baidu.com");
		httpGet.addHeader("Pragma", "no-cache");
		httpGet.addHeader("Host", "openapi.baidu.com");
		httpGet.addHeader("User-Agent", "Client of Baidu Open Platform");
		httpGet.addHeader("Accept", "*/*");
		httpGet.addHeader("Accept-Encoding", "gzip,defate");
		httpGet.addHeader("Accept-Charset", "utf-8");
		httpGet.addHeader("Connection", "close");

		HttpClient client = new DefaultHttpClient(
				new BasicHttpParams()
						.setParameter("method", method)
						.setParameter("access_token", access_token));

		HttpResponse response;
		try {
			response = client.execute(httpGet);
			String json_string = EntityUtils.toString(response.getEntity(),
					"utf-8");
			return new JSONObject(json_string);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	public JSONObject updateSigleFile(String method, String access_token, String path, String[] file, String ondup) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject uploadSplitFile(String method, String access_token, String type, String[] file) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject uploadMergeFile(String method, String access_token, String path, String param, String ondup) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public JSONObject downloadSigleFile(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	public JSONObject mkdirPcs(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject getFileDirMeta(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject getFileDirMetaBatch(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject listDir(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject moveFileDir(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject moveFileDirBatch(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject copyFileDir(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject copyFileDirBatch(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject deleteFileDir(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject deleteFileDirBatch(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject searchFile(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public JSONObject thumbnailFile(String method, String access_token, String path) {
		try {
			return new JSONObject("signle file");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
