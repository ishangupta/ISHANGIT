package com.example.adapter;

import com.koushikdutta.ion.Ion;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImageAdapter extends PagerAdapter {
	Context context;
	String[] GalImages;
   
    public ImageAdapter(Context context,String[] GalImages){
    	this.context=context;
    	this.GalImages=GalImages;
    }
    @Override
    public int getCount() {
      return GalImages.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      ImageView imageView = new ImageView(context);
      imageView.setPadding(10,10,10,10);
      imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
      Ion.with(imageView).load(GalImages[position]);
      ((ViewPager) container).addView(imageView, 0);
      return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      ((ViewPager) container).removeView((ImageView) object);
    }
  }
