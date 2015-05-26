package com.example.familyfun;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.example.server.Search;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SearchScreen extends Activity{


	RadioGroup radioGroup3;
	RadioButton r1,r2,r3,r4;
	SeekBar sb_yc,sb_oc;
	Button tv_indoor,tv_outdoor;
	ImageView img_eat,img_play,img_stay;
	EditText edt_zipcode;
	
	
	public static String door="";
	public static String searchType="";
	public static String younger_child_age;
	public static String older_child_age;
	public static String distance="";
	public static String zipcode;
	LinearLayout ll_yc,ll_oc;
	TextView v;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_screen1);
		
		
		radioGroup3=(RadioGroup)findViewById(R.id.radioGroup3);
	
		edt_zipcode=(EditText)findViewById(R.id.edt_zip);
		r1=(RadioButton)findViewById(R.id.r1);
		r2=(RadioButton)findViewById(R.id.r2);
		r3=(RadioButton)findViewById(R.id.r3);
		r4=(RadioButton)findViewById(R.id.r4);
		sb_yc=(SeekBar)findViewById(R.id.sb_yc);
		ll_yc=(LinearLayout)findViewById(R.id.ll_yc);
		sb_oc=(SeekBar)findViewById(R.id.sb_oc);
		ll_oc=(LinearLayout)findViewById(R.id.ll_oc);
		sb_oc.incrementProgressBy(2);
		img_eat=(ImageView)findViewById(R.id.img_eat);
		img_play=(ImageView)findViewById(R.id.img_play);
		img_stay=(ImageView)findViewById(R.id.img_stay);
		tv_indoor=(Button)findViewById(R.id.tv_indoor);
		tv_indoor.setBackgroundColor(SearchScreen.this.getResources().getColor(R.color.red));
		tv_outdoor=(Button)findViewById(R.id.tv_outdoor);
		tv_outdoor.setBackgroundColor(SearchScreen.this.getResources().getColor(R.color.red));
		tv_indoor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_indoor.setBackgroundResource(R.drawable.indoor_selected);
				tv_outdoor.setBackgroundResource(R.drawable.outdoor);
				door="indoor";
			}
		});
		tv_outdoor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				tv_indoor.setBackgroundResource(R.drawable.indoor);
				tv_outdoor.setBackgroundResource(R.drawable.outdoor_selected);
				door="outdoor";
			}
		});
		img_eat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			img_eat.setBackgroundResource(R.drawable.eat_btn_selected);
			img_play.setBackgroundResource(R.drawable.play_btn);
			img_stay.setBackgroundResource(R.drawable.stay_btn);
			searchType="eat";
			}
		});
		img_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				img_play.setBackgroundResource(R.drawable.play_btn_selected);
				img_eat.setBackgroundResource(R.drawable.eat_btn);
				img_stay.setBackgroundResource(R.drawable.stay_btn);
				searchType="play";
			}
		});
		img_stay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				img_stay.setBackgroundResource(R.drawable.stay_btn_selected);
				img_eat.setBackgroundResource(R.drawable.eat_btn);
				img_play.setBackgroundResource(R.drawable.play_btn);
				searchType="stay";
			}
		});
		
		sb_yc.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
			}
		});
		
        sb_oc.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				
			}
		});
        for(int i=0;i<=10;i++)
        {
        	 v=new TextView(this);
        	 LinearLayout .LayoutParams layoutParams1= new LinearLayout.LayoutParams(1,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        	 v.setLayoutParams(layoutParams1);
        	 if(i==10)
        	 v.setText(String.valueOf(i)+"+");
        	 else
        	 v.setText(String.valueOf(i)); 
        	 v.setTag(i);
        	 v.setGravity(Gravity.CENTER);
        	 v.setTextSize(10);
        	 ll_yc.addView(v);
        }
        for(int i=0;i<=10;i++)
        {
        	 v=new TextView(this);
        	 LinearLayout .LayoutParams layoutParams1= new LinearLayout.LayoutParams(1,LinearLayout.LayoutParams.MATCH_PARENT,1f);
        	 v.setLayoutParams(layoutParams1);
        	 if(i==10)
             v.setText(String.valueOf(i)+"+");
             else
             v.setText(String.valueOf(i)); 
        	 v.setTag(i);
        	 v.setGravity(Gravity.CENTER);
        	 v.setTextSize(10);
        	 ll_oc.addView(v);
        }
        
        
        edt_zipcode.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count>0)
				{
					radioGroup3.clearCheck();
					for (int i = 0; i < radioGroup3.getChildCount(); i++) {
						radioGroup3.getChildAt(i).setEnabled(false);
						}
					distance="";
				}else{
					for (int i = 0; i < radioGroup3.getChildCount(); i++) {
						radioGroup3.getChildAt(i).setEnabled(true);
						}
				}
				
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
        
	}
	@Override
	public void onResume() {
	    super.onResume(); 
	    door="";
		searchType="";
		distance="";
		tv_indoor.setBackgroundResource(R.drawable.indoor);
		tv_outdoor.setBackgroundResource(R.drawable.outdoor);
		img_play.setBackgroundResource(R.drawable.play_btn);
		img_eat.setBackgroundResource(R.drawable.eat_btn);
		img_stay.setBackgroundResource(R.drawable.stay_btn);
		sb_yc.setProgress(0);
		sb_oc.setProgress(0);
	}
	
	
	@SuppressWarnings("deprecation")
	public void showDialog(String msg)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(SearchScreen.this).create();
		alertDialog.setTitle("Family Fun");
		alertDialog.setMessage(msg);
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Write your code here to execute after dialog closed

			}
		});
		alertDialog.show();
	}
	
	
	
public void search(View v)
{
	younger_child_age=String.valueOf(sb_yc.getProgress());
	older_child_age=String.valueOf(sb_oc.getProgress());
	zipcode=edt_zipcode.getText().toString();
	
	int selected3=radioGroup3.getCheckedRadioButtonId();
	if(selected3==r1.getId())
		distance="2";
	else if(selected3==r2.getId())
		distance="5";
	else if(selected3==r3.getId())
		distance="10";
	else if(selected3==r4.getId())
		distance="20";
	else
		distance="";
	
	if(door.equals(""))
	{
		showDialog("Please select Indoor or Outdoor");
	}else if(searchType.equals(""))
	{
		showDialog("Please select Eat, Play or Stay");
	}else if(younger_child_age.equals(""))
	{
		showDialog("Please select age of younger child");
	}else if(older_child_age.equals(""))
	{
		showDialog("Please select age of older child");
	}else if(distance.equals("")&&zipcode.equals(""))
	{
		showDialog("Please select distance or enter zipcode");
	}else
	{
	try {
		String url="http://www.familyfunplanner.com.au/demo/iphone/family_fun_search.php";
		if(!url.endsWith("?"))
		    url += "?";
		    List<NameValuePair> params = new LinkedList<NameValuePair>();
		    params.add(new BasicNameValuePair("requestSendFrom","android"));
		    params.add(new BasicNameValuePair("ageYounger",younger_child_age));
		    params.add(new BasicNameValuePair("ageOlder",older_child_age));
		    params.add(new BasicNameValuePair("searchType", searchType));
		    if(zipcode.length()>0)
		     params.add(new BasicNameValuePair("myPlace",zipcode));
		    else
		    params.add(new BasicNameValuePair("distance",distance));
		    params.add(new BasicNameValuePair("locationType",door));
		    params.add(new BasicNameValuePair("myLatitude","-33.7646874"));
		    params.add(new BasicNameValuePair("myLongitude","150.9901097"));
		   /* params.add(new BasicNameValuePair("myLatitude",MainActivity.lat));
		    params.add(new BasicNameValuePair("myLongitude",MainActivity.lng));*/
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url += paramString;
		new Search(SearchScreen.this,"1").execute(url);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
}
}
