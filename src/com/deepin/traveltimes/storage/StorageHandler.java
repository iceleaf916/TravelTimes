package com.deepin.traveltimes.storage;

public class StorageHandler {
	
	private static final LBSCloudStorage storage = new LBSCloudStorage();
	private static final String databox_id = "travel_times";
	
	public static LBSCloudStorage getLbsCloudStorage() throws Exception {
		return storage;
	}
		
}
