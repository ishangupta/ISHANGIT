package com.example.adapter;

import java.util.ArrayList;

import com.example.Model.Events;
import com.example.familyfun.R;
import com.koushikdutta.ion.Ion;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends BaseAdapter{

	//// ishannnnnnnnnnnnnnnn
	Context context;
	ArrayList<Events> arrayList;
	
	public EventAdapter(Context context,ArrayList<Events> arrayList)
	{
		this.context=context;
		this.arrayList=arrayList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v=convertView;
		if(v==null)
		{
			LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=inflater.inflate(R.layout.search_list_item, null);
		}
		ImageView imageView=(ImageView)v.findViewById(R.id.img_item);
		TextView tv_name=(TextView)v.findViewById(R.id.tv_itemName);
		TextView tv_address=(TextView)v.findViewById(R.id.tv_itemAddress);
		TextView tv_rating=(TextView)v.findViewById(R.id.tv_itemrate);
		TextView tv_distance=(TextView)v.findViewById(R.id.tv_distance);
		
		tv_name.setText(arrayList.get(position).getName());
		tv_address.setText(arrayList.get(position).getAddress());
		tv_rating.setText(arrayList.get(position).getRating());
		if(arrayList.get(position).getDistance().equals("null"))
		tv_distance.setText("0.0 miles");
		else
		tv_distance.setText(String.format("%.2f",Float.parseFloat(arrayList.get(position).getDistance()))+" miles");
		
		Ion.with(imageView).load(arrayList.get(position).getImage());
		return v;
	}

}
