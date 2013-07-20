package com.deepin.traveltimes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class PositionRecord extends Activity implements OnClickListener{
   
	private PositionService mMyService;
	private TextView mTextView;
	private Button startServiceButton;
	private Button stopServiceButton;
	private Button bindServiceButton;
	private Button unbindServiceButton;
	private Context mContext;
	
	public static String TAG = "PositionRecord";
	
	public void writeFile() throws Exception{
		String FILENAME = "hello_file";
		String string = "hello world!dsfsdfsdfsd";

		FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
		fos.write(string.getBytes());
		fos.close();
	}
	
	public String getFile() throws Exception{
		String FILENAME = "hello_file";

		FileInputStream fis = openFileInput(FILENAME);
		int n = 8;   
        byte buffer[] = new byte[n];
        String results = "";
        while ((fis.read(buffer) != -1) && (n > 0)) {
            results += new String(buffer);
        }
		fis.close();
		return results;
	}
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_control);
        setupViews();
        
        /*LBSCloudStorage s = new LBSCloudStorage ();
  	 
  	  	for (int i=0 ; i < 3; i++) {
  	  		try {
  	  			Log.e("storage", "id" + LBSCloudStorage.createDatabox("dejk" + i));
  	  		} catch (Exception e) {
  	  			// TODO Auto-generated catch block
  	  			e.printStackTrace();
  	  		}
  	  	}*/
        try {
			writeFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			String r = getFile();
			Log.e(TAG, r);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void setupViews(){
    
    	mContext = PositionRecord.this;
    	mTextView = (TextView)findViewById(R.id.text);    	
    	
    	startServiceButton = (Button)findViewById(R.id.startservice);
    	stopServiceButton = (Button)findViewById(R.id.stopservice);
    	bindServiceButton = (Button)findViewById(R.id.bindservice);
    	unbindServiceButton = (Button)findViewById(R.id.unbindservice);
    	
    	startServiceButton.setOnClickListener(this);
    	stopServiceButton.setOnClickListener(this);
    	bindServiceButton.setOnClickListener(this);
    	unbindServiceButton.setOnClickListener(this);
    }
    
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v == startServiceButton){
			Intent i  = new Intent();
			i.setClass(PositionRecord.this, PositionService.class);
			mContext.startService(i);
		}else if(v == stopServiceButton){
			Intent i  = new Intent();
			i.setClass(PositionRecord.this, PositionService.class);
			mContext.stopService(i);
		}
	}
}