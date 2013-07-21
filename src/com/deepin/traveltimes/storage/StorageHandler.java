package com.deepin.traveltimes.storage;

import org.json.JSONArray;
import org.json.JSONObject;

public class StorageHandler {

	private static final LBSCloudStorage storage = new LBSCloudStorage();

	private static final String databox_name = "travel_times";
	private static String databox_id = null;	
	private static String datetime_meta_id = null;

	private static JSONObject datetime_meta_json = null;
	
	public static LBSCloudStorage getLbsCloudStorage() throws Exception {
		initPositionDatabox();
		
		return storage;
	}

	private static void initPositionDatabox () {
		if (databox_id == null) {
			try {
				databox_id = storage.createDatabox(databox_name);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (datetime_meta_json == null) {
			try {
				datetime_meta_json = storage.createDataboxMeta("定位时间",
						"locate_time", 10, databox_id, false);
				datetime_meta_id = datetime_meta_json.getJSONArray("ids").getString(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// use database record id as poiname
	public static void addPosition (String poiname, String datetime, String latitude, String lontitude) {
		try {
			int	poi_id = storage.createPoi(poiname, latitude, lontitude, 1, databox_id);
			int poiext_status = storage.createPoiExt("locate_time", datetime);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static String getPoiIdByName (String poiname) {
		try {
			JSONArray pois = storage.conditionQueryPoi(databox_id).getJSONArray("pois");
			for (int i = 0; i < pois.length(); i++) {
				if (pois.getJSONObject(i).getString("name").equals(poiname)) {
					return pois.getJSONObject(i).getString("id");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// database id is poi name
	public static String getDatetime (String database_id) {
		String poi_id = getPoiIdByName(database_id);
	
		try {
			return storage.queryPoiExt(poi_id).getJSONObject("poi_ext_info").getString("locate_time");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	public static String getLatitude (String database_id) {
		
		String poi_id = getPoiIdByName(database_id);
		try {
			return storage.queryPoi(poi_id, 1).getJSONObject("poi").getString("original_lat");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
	public static String getLontitude (String database_id) {
		
		String poi_id = getPoiIdByName(database_id);
		try {
			return storage.queryPoi(poi_id, 1).getJSONObject("poi").getString("original_lon");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	
}
