package com.example.familyfun;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Model.Events;
import com.example.adapter.EventAdapter;
import com.example.server.Search;

public class SearchResult extends Activity{
	
	TextView tv_left,tv_right;
	ImageView img_select;
	public static ListView lvListView;
	public static ArrayList<Events> aList=new ArrayList<Events>();
	Events events;
	LinearLayout ll_header;
	String left="eat";
	String right="stay";
	String selected="play";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.search_result);
		ll_header=(LinearLayout)findViewById(R.id.ll_header);
		tv_left=(TextView)findViewById(R.id.tv_left);
		tv_right=(TextView)findViewById(R.id.tv_right);
		img_select=(ImageView)findViewById(R.id.img_select);
		lvListView=(ListView)findViewById(R.id.lv_result);
		tv_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeIcons(left);
			}
		});
		tv_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeIcons(right);
			}
		});
		
		
		String data=getIntent().getExtras().getString("data");
		setData(data);
		lvListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				Intent i=new Intent(SearchResult.this,DetailScreen.class);
				i.putExtra("id", aList.get(position).getId());
				startActivity(i);
				
			}
		});
	}
	
public void findfun(View v)
{
	finish();
}
public void setData(String result)
{
	try {
		JSONArray jsonArray=new JSONArray(result);
		aList.clear();
		for(int i=0;i<jsonArray.length();i++)
		{
			JSONObject jsonObject=jsonArray.getJSONObject(i);
			events=new Events();
			events.setId(jsonObject.getString("idOfList"));
			events.setName(jsonObject.getString("nameOfList"));
			events.setCatg(jsonObject.getString("catOfList"));
			events.setDesc(jsonObject.getString("descOfList"));
			events.setImage(jsonObject.getString("thumbImageOfList"));
			events.setLat(jsonObject.getString("latitudeOfList"));
			events.setLng(jsonObject.getString("longitudeOfList"));
			events.setAddress(jsonObject.getString("addressOfList"));
			events.setDistance(jsonObject.getString("distance"));
			events.setRating("Not rated yet");
			aList.add(events);
		}
		
		lvListView.setAdapter(new EventAdapter(this,aList));
		
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	
public void changeIcons(String left_right)
{
	if(left_right.equals("eat"))
	{
		img_select.setImageResource(R.drawable.eat);
		ll_header.setBackgroundResource(R.drawable.play_stay);
		left="play";
		right="stay";
		selected="eat";
		searchAgain("eat");
	}
	if(left_right.equals("stay"))
	{
		img_select.setImageResource(R.drawable.stay);
		ll_header.setBackgroundResource(R.drawable.eat_play);
		left="eat";
		right="play";
		selected="stay";
		searchAgain("stay");
	}
	if(left_right.equals("play"))
	{
		img_select.setImageResource(R.drawable.play);
		ll_header.setBackgroundResource(R.drawable.eat_stay);
		left="eat";
		right="stay";
		selected="play";
		searchAgain("play");
	}
}

public void searchAgain(String searchType)
{
	try {
		String url="http://www.familyfunplanner.com.au/demo/iphone/family_fun_search.php";
		if(!url.endsWith("?"))
		    url += "?";

		List<NameValuePair> params = new LinkedList<NameValuePair>();
		    params.add(new BasicNameValuePair("requestSendFrom","android"));
		    params.add(new BasicNameValuePair("ageYounger",SearchScreen.younger_child_age));
		    params.add(new BasicNameValuePair("ageOlder",SearchScreen.older_child_age));
		    params.add(new BasicNameValuePair("searchType", searchType));
		    if(SearchScreen.zipcode.length()>0)
		     params.add(new BasicNameValuePair("myPlace",SearchScreen.zipcode));
		    else
		    params.add(new BasicNameValuePair("distance",SearchScreen.distance));
		    params.add(new BasicNameValuePair("locationType",SearchScreen.door));
		    /*params.add(new BasicNameValuePair("myLatitude",MainActivity.lat));
		    params.add(new BasicNameValuePair("myLongitude",MainActivity.lng));*/
		    params.add(new BasicNameValuePair("myLatitude","-33.7646874"));
		    params.add(new BasicNameValuePair("myLongitude","150.9901097"));
		String paramString = URLEncodedUtils.format(params, "utf-8");
		url += paramString;
		
		new Search(SearchResult.this,"2").execute(url);
	} catch (Exception e) {
		// TODO: handle exception
	}
}
	
}
