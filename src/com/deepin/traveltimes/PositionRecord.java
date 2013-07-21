package com.deepin.traveltimes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_control);
        setupViews();        
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
    
	@Override
	public void onClick(View v) {
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