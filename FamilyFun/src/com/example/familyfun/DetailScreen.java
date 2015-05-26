package com.example.familyfun;

import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ImageAdapter;
import com.example.server.Search;


public class DetailScreen extends Activity{
	
	String id;
	ImageView img_detail,img_phone,img_mouse,img_funAgain,img_back,imag_next_image,img_back_image;
	TextView tv_name,tv_desc,tv_address;
	String image[]=new String[3];
	int image_position=0;
	String contact_no;
	String website_url;
	String fbpage_url;
	ViewPager viewPager;
   
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.item_detail);
		id=getIntent().getStringExtra("id");
		viewPager = (ViewPager) findViewById(R.id.view_pager);
		img_back=(ImageView)findViewById(R.id.img_back);
		//imag_next_image=(ImageView)findViewById(R.id.imag_next_image);
	//	img_back_image=(ImageView)findViewById(R.id.img_back_image);
		img_funAgain=(ImageView)findViewById(R.id.img_funAgain);
		img_mouse=(ImageView)findViewById(R.id.img_mouse);
		img_phone=(ImageView)findViewById(R.id.img_phone);
		//img_detail=(ImageView)findViewById(R.id.img_detail);
		tv_name=(TextView)findViewById(R.id.tv_name);
		tv_desc=(TextView)findViewById(R.id.tv_desc);
		tv_address=(TextView)findViewById(R.id.tv_address);
		
		try {
			String url="http://www.familyfunplanner.com.au/demo/iphone/family_fun_search_details.php";
			if(!url.endsWith("?"))
			    url += "?";

			List<NameValuePair> params = new LinkedList<NameValuePair>();
			    params.add(new BasicNameValuePair("requestSendFrom","android"));
			    params.add(new BasicNameValuePair("searchId",id));
			   
			String paramString = URLEncodedUtils.format(params, "utf-8");
			url += paramString;
			
			new Search(DetailScreen.this,"3").execute(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	img_back.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});	
	img_funAgain.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent i=new Intent(DetailScreen.this,SearchScreen.class);
			startActivity(i);
		}
	});	
	/*img_back_image.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			image_position=image_position-1;
			if(image_position<0)
				image_position=image.length-1;
			Ion.with(img_detail).load(image[image_position]);
			
		}
	});
	imag_next_image.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			image_position=image_position+1;
			if(image_position==image.length)
				image_position=0;
			Ion.with(img_detail).load(image[image_position]);
			
		}
	});*/
	img_phone.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(contact_no.equals(""))
			{
				Toast.makeText(getApplicationContext(), "No contact number is given", Toast.LENGTH_SHORT).show();
			}
			else
			{
				 Intent phoneIntent = new Intent(Intent.ACTION_CALL);
			      phoneIntent.setData(Uri.parse("tel:"+contact_no));
			      try {
			         startActivity(phoneIntent);
			      } catch (android.content.ActivityNotFoundException ex) {
			        
			      }
			}
			
		}
	});
	img_mouse.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if(website_url.equals(""))
			{
				Toast.makeText(getApplicationContext(), "No website url  is given", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Intent i=new Intent(DetailScreen.this,OpenPage.class);
				i.putExtra("bookPath",website_url);
				startActivity(i);
			}
			
		}
	});
		
	}
	
	public void setDetailData(String data)
	{
		try{
			JSONArray jsonArray=new JSONArray(data);
			JSONObject jsonObject=jsonArray.getJSONObject(0);
			tv_name.setText(jsonObject.optString("nameOfList"));
			tv_desc.setText(jsonObject.optString("descOfList"));
			String address=jsonObject.optString("addressOfList")+","+jsonObject.optString("cityOfList")+" "+jsonObject.optString("stateOfList")+"-"+jsonObject.optString("postcodeOfList")+","+jsonObject.optString("countryOfList");
			tv_address.setText(address);
			image[0]=jsonObject.optString("ImageOfList").replaceAll(" ","%20");
			image[1]=jsonObject.optString("ImageOfList2").replaceAll(" ","%20");
			image[2]=jsonObject.optString("ImageOfList3").replaceAll(" ","%20");
			//Ion.with(img_detail).load(image[image_position]);
			contact_no=jsonObject.optString("phoneOfList");
			website_url=jsonObject.optString("websiteOfList");
			fbpage_url=jsonObject.optString("facebookOfList");
			ImageAdapter adapter = new ImageAdapter(DetailScreen.this,image);
		    viewPager.setAdapter(adapter);
			
		}catch(Exception e)
		{
			
		}
	}
	
	
	

}
