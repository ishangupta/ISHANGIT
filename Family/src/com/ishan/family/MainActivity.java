package com.ishan.family;

import org.apache.cordova.DroidGap;

import android.os.Bundle;

@SuppressWarnings("deprecation")
public class MainActivity extends DroidGap {

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
    
}
