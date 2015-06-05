package com.ishan.family;

import org.apache.cordova.DroidGap;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.JavascriptInterface;

@SuppressWarnings("deprecation")
public class MainActivity extends DroidGap {

	private ProgressDialog mProgressDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        super.init(); // Calling this is necessary to make this work
	    appView.addJavascriptInterface(this, "toaster");
	    appView.getSettings().setJavaScriptEnabled(true);
		//super.setIntegerProperty("splashscreen", R.drawable.splash);
	    appView.loadUrl("file:///android_asset/www/home.html");
    }
    
    
    
    
    
    @JavascriptInterface
    public void showDialog() {
    	
    	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mProgressDialog =  new ProgressDialog(MainActivity.this);
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.show();
				mProgressDialog.setCancelable(false);
			}
		});
		
	}

    @JavascriptInterface
	public void dismissDialog() {
    	
    	runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (mProgressDialog != null && mProgressDialog.isShowing()) {
					mProgressDialog.dismiss();
				}
			}
		});
    	
		
	}
    
}
