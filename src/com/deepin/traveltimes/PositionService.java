package com.deepin.traveltimes;

import android.app.Service;
import android.content.Intent;
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
import com.deepin.traveltimes.db.DatabaseHandler;
import com.deepin.traveltimes.db.Position;

public class PositionService extends Service{

	  private Looper mServiceLooper;
	  private ServiceHandler mServiceHandler;
	  
	  public static String TAG = "PositionService";
	  public static Boolean running = true;
	  
	  public LocationClient mLocationClient = null;
	  public BDLocationListener myListener = new MyLocationListener();
	  public LocationClientOption option = new LocationClientOption();
	  
	  public DatabaseHandler db = new DatabaseHandler(this);
	  
	  public class MyLocationListener implements BDLocationListener {
		  	
		  	public void updatePositionInfo(BDLocation location){
		  		String datetime = location.getTime(); 
		  		String latitude = Double.toString(location.getLatitude());
		  		String longtitude = Double.toString(location.getLongitude());
		  		Position position = new Position(datetime, latitude, longtitude);
		  		db.addPosition(position);
		  	}
		  	
		  	public void locationReceived(BDLocation location){
		  		if (location == null)
					return ;
				if (location.getLocType() == BDLocation.TypeGpsLocation){
					updatePositionInfo(location);
					Log.e(TAG, "Get GPS location results");
				} else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
					updatePositionInfo(location);
					Log.e(TAG, "Get network location results");
				} else if (location.getLocType() == BDLocation.TypeOffLineLocation){
					updatePositionInfo(location);
					Log.e(TAG, "Get offline location results");				
				}else{
					int code = location.getLocType();
					Log.e(TAG, "Get location failed! Code:" + Integer.toString(code));
				}
				stopLocation();
		  	}
		  	
			@Override
			public void onReceiveLocation(BDLocation location) {
				locationReceived(location);	
			}
		
			@Override
			public void onReceivePoi(BDLocation poiLocation) {
				locationReceived(poiLocation);
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
	                		  wait(retry_interval);
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
