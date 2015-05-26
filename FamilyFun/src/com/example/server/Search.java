package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.adapter.EventAdapter;
import com.example.familyfun.DetailScreen;
import com.example.familyfun.SearchResult;

public class Search extends AsyncTask<String,Void,String>{

	ProgressDialog progressDialog;
	Context con;

	String result = null;
	String value;
	
	public Search(Context context,String value)
	{
		this.con=context;
		this.value=value;
	}
	
	
	@Override
	public void onPreExecute()
	{
		progressDialog=ProgressDialog.show(con,"", "loading...");
	}
	
	@Override
	protected String doInBackground(String... params) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(params[0]);
		String line = "";
		try {
		    HttpResponse response = httpclient.execute(httpget);
		    if(response != null) {
		        
		        InputStream inputstream = response.getEntity().getContent();
		        line = convertStreamToString(inputstream);
		    } else {
		    }
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		} catch (Exception e) {
		}
		return line;
	}
	private String convertStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    try {
	        while ((line = rd.readLine()) != null) {
	            total.append(line);
	        }
	    } catch (Exception e) {
	        Toast.makeText(con, "Stream Exception", Toast.LENGTH_SHORT).show();
	    }
	    return total.toString();
	}
	
	@Override
	public void onPostExecute(String result)
	{
		System.out.println("okkkkkk="+result);
		progressDialog.dismiss();
		try {
			JSONObject jsonObject=new JSONObject(result);
			int count=jsonObject.getJSONObject("status").getInt("noOfRecords");
			if(count>0)
			{
			 String data=jsonObject.getJSONArray("response").toString();
			 if(value.equals("1"))
			 {
				 Intent i=new Intent(con,SearchResult.class);
				 i.putExtra("data",data);
				 con.startActivity(i);
			 }
			 if(value.equals("2"))
			 {
				((SearchResult)con).setData(data); 
			 }
			 if(value.equals("3"))
			 {
				((DetailScreen)con).setDetailData(data); 
			 }
			}
			else
			{
				if(SearchResult.lvListView!=null)
				{
					SearchResult.aList.clear();
					SearchResult.lvListView.setAdapter(new EventAdapter(con,SearchResult.aList));
					showDialog("No record found");
				}
				else
				{
					showDialog("No record found");
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void showDialog(String msg)
	{
		progressDialog.dismiss();
		AlertDialog alertDialog = new AlertDialog.Builder(con).create();
	alertDialog.setTitle("Family Fun");
	alertDialog.setMessage(msg);
	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int which) {
	    // Write your code here to execute after dialog closed
	   
	    }
	});
	alertDialog.show();
	}
	

}
