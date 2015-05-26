package com.example.familyfun;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

@SuppressLint("SetJavaScriptEnabled")
public class OpenPage extends Activity{
	WebView wv_book;
	ProgressBar progressDialog;
	public String mCurrentUrl;
	int status=1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		 setContentView(R.layout.book);
		 wv_book=(WebView)findViewById(R.id.wv_book);
		 progressDialog=(ProgressBar)findViewById(R.id.progressBar1);
		 String googleDocsURL = "";
		  googleDocsURL=googleDocsURL+getIntent().getStringExtra("bookPath");
		 WebSettings settings = wv_book.getSettings();
	        settings.setJavaScriptEnabled(true);
	        settings.setJavaScriptCanOpenWindowsAutomatically(true);
	        wv_book.getSettings().setLoadWithOverviewMode(true);
	        wv_book.getSettings().setUseWideViewPort(true);
	        wv_book.getSettings().setSupportZoom(true);
	        wv_book.getSettings().setBuiltInZoomControls(true);
	        wv_book.setInitialScale(1);
	        wv_book.requestFocus();
	        wv_book.requestFocusFromTouch();
	        wv_book.loadUrl(googleDocsURL);
	        
	       wv_book.setWebViewClient(new WebViewClient(){
	    	   
	    	   @Override
	           public void onPageStarted(WebView view, String url, Bitmap favicon) 
	           {
	    		   progressDialog.setVisibility(View.VISIBLE);
	           }

	    	    @Override
	    	    public boolean shouldOverrideUrlLoading(WebView view, String url){
	    	    	System.out.println(url);
	    	    		view.loadUrl(url);
	    	           return true;
	    	    }
	    	    
	    	    @Override
	    	    public void onPageFinished (WebView view, String url)
	    	    {
	    	    	/*wv_book.loadUrl("javascript:document.getElementsByClassName('fg-clip')[0].style.display=\"none\";");*/
	    	    	progressDialog.setVisibility(View.GONE);
	    	    }
	    	});
	       
	}
	@Override
	public void onBackPressed() {
	    // your code.
		if(wv_book.canGoBack())
		{
			wv_book.goBack();
		}
		else
		{
			finish();
		}
	}
	
	 

}
