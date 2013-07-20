package com.deepin.traveltimes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class PositionService extends Service{

	  private Looper mServiceLooper;
	  private ServiceHandler mServiceHandler;
	  
	  public static String TAG = "PositionService";
	  public static Boolean running = true;
	  
	  public LocationClient mLocationClient = null;
	  public BDLocationListener myListener = new MyLocationListener();
	  public LocationClientOption option = new LocationClientOption();

	  public boolean checkExternalStorage(){
		  boolean mExternalStorageAvailable = false;
		  boolean mExternalStorageWriteable = false;
		  String state = Environment.getExternalStorageState();

		  if (Environment.MEDIA_MOUNTED.equals(state)) {
		      // We can read and write the media
		      mExternalStorageAvailable = mExternalStorageWriteable = true;
		  } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
		      // We can only read the media
		      mExternalStorageAvailable = true;
		      mExternalStorageWriteable = false;
		  } else {
		      // Something else is wrong. It may be one of many other states, but all we need
		      //  to know is we can neither read nor write
		      mExternalStorageAvailable = mExternalStorageWriteable = false;
		  }
		  return mExternalStorageAvailable && mExternalStorageWriteable;
	  }
	  
	  public class MyLocationListener implements BDLocationListener {
		  	
			@Override
			public void onReceiveLocation(BDLocation location) {
				if (location == null)
					return ;
				StringBuffer sb = new StringBuffer(256);
				sb.append("time : ");
				sb.append(location.getTime());
				sb.append("\nerror code : ");
				sb.append(location.getLocType());
				sb.append("\nlatitude : ");
				sb.append(location.getLatitude());
				sb.append("\nlontitude : ");
				sb.append(location.getLongitude());
				sb.append("\nradius : ");
				sb.append(location.getRadius());
				if (location.getLocType() == BDLocation.TypeGpsLocation){
					Log.e(TAG, "Get GPS location results");
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
					/*sb.append("\naddr : ");
					sb.append(location.getAddrStr());*/
					Log.e(TAG, "Get network location results");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation){
					Log.e(TAG, "Get offline location results");				
				}else{
					Log.e(TAG, "Get location failed");
				}
				stopLocation();
	
			}
		
			public void onReceivePoi(BDLocation poiLocation) {
					if (poiLocation == null){
						return ;
					}
					StringBuffer sb = new StringBuffer(256);
					sb.append("Poi time : ");
					sb.append(poiLocation.getTime());
					sb.append("\nerror code : ");
					sb.append(poiLocation.getLocType());
					sb.append("\nlatitude : ");
					sb.append(poiLocation.getLatitude());
					sb.append("\nlontitude : ");
					sb.append(poiLocation.getLongitude());
					sb.append("\nradius : ");
					sb.append(poiLocation.getRadius());
					if (poiLocation.getLocType() == BDLocation.TypeNetWorkLocation){
						sb.append("\naddr : ");
						sb.append(poiLocation.getAddrStr());
					} 
					if(poiLocation.hasPoi()){
						sb.append("\nPoi:");
						sb.append(poiLocation.getPoi());
					}else{				
						sb.append("noPoi information");
					}
					//logMsg(sb.toString());
					Log.e(TAG, sb.toString());
					
					try {
					    
					    JSONObject person = new JSONObject();  
					    
					    JSONArray phone = new JSONArray();  
					    phone.put("12345678").put("87654321");  
					    person.put("phone", phone);  
					  
					    person.put("name", "yuanzhifei89");  
					    person.put("age", 100);  
					    
					    JSONObject address = new JSONObject();  
					    address.put("country", "china");  
					    address.put("province", "jiangsu");  
					    person.put("address", address);    
					    person.put("married", false);  
					} catch (JSONException ex) {
					     
					    throw new RuntimeException(ex);  
					}  
			}
	  }
	  
	  public void initLocationClient(){
			mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
			mLocationClient.registerLocationListener( myListener );    //注册监听函数
			
			option.setOpenGps(true);
			option.setAddrType("all");//返回的定位结果包含地址信息
			option.setCoorType("bd09ll");//返回的定位结果是百度经纬度,默认值gcj02
			//option.setScanSpan(5000);//设置发起定位请求的间隔时间为5000ms
			option.disableCache(true);//禁止启用缓存定位
			option.setPoiNumber(5);	//最多返回POI个数	
			option.setPoiDistance(1000); //POI查询距离		
			option.setPoiExtraInfo(true); //是否需要POI的电话和地址等详细信息		
			mLocationClient.setLocOption(option);	
	  }
	  
	  public Boolean startLocation(){
		  mLocationClient.start();
		  if (mLocationClient.requestLocation() == 0 ||
    		  mLocationClient.requestOfflineLocation() == 0 ){
        	  return true;
          }
		  return false;
	  }
	  
	  public void stopLocation(){
		  mLocationClient.stop();
	  }
	  	  
	  // Handler that receives messages from the thread
	  private final class ServiceHandler extends Handler {
	      public ServiceHandler(Looper looper) {
	          super(looper);
	      }
	      
	      public long position_interval = 60 * 1000;
	      public long retry_interval = 60 * 1000;
	      
	      @Override
	      public void handleMessage(Message msg) {
	    	  
	          while (running) {
	              synchronized (this) {
	                  try {
	                	  if (startLocation()){
	                		  wait(position_interval);
	                		  Log.e(TAG, "start position OK!");
	                	  }
	                	  else{
	                		  wait(position_interval);
	                		  Log.e(TAG, "start position FAILED!");
	                	  }
	                  } catch (Exception e) {
	                	  
	                  }
	              }
	          }
		      
	          stopSelf(msg.arg1);
	      }
	  }

	  @Override
	  public void onCreate() {
	    
	    HandlerThread thread = new HandlerThread("ServiceStartArguments",
	            Process.THREAD_PRIORITY_BACKGROUND);
	    thread.start();
	    
	    // Get the HandlerThread's Looper and use it for our Handler 
	    mServiceLooper = thread.getLooper();
	    mServiceHandler = new ServiceHandler(mServiceLooper);
	  }

	  @Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		  initLocationClient();
	      Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

	      // For each start request, send a message to start a job and deliver the
	      // start ID so we know which request we're stopping when we finish the job
	      running = true;
	      Message msg = mServiceHandler.obtainMessage();
	      msg.arg1 = startId;
	      mServiceHandler.sendMessage(msg);
	      
	      // If we get killed, after returning from here, restart
	      return START_STICKY;
	  }

	  @Override
	  public IBinder onBind(Intent intent) {
	      // We don't provide binding, so return null
	      return null;
	  }
	  
	  @Override
	  public void onDestroy() {
		mLocationClient.stop();
		running = false;
	    Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show(); 
	  }
}
