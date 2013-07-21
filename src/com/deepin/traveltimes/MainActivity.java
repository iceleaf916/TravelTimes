package com.deepin.traveltimes;
 
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
 
public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	WebView wv;
	ProgressDialog pd;
	Handler handler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}

