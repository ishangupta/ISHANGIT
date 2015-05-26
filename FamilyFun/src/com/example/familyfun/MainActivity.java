package com.example.familyfun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static String lat,lng;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		 try {
			    LocationManager locationManager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
			    Criteria criteria = new Criteria();
			                
			    LocationListener locationListener = new LocationListener() {
			     public void onLocationChanged(Location location) {
			      lat = Double.toString(location.getLatitude());
			      lng = Double.toString(location.getLongitude());
			     }
			     public void onStatusChanged(String provider, int status, Bundle extras) {}
			     public void onProviderEnabled(String provider) {}
			     public void onProviderDisabled(String provider) {}
			    };
			                
			    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
			   }
			   catch (Exception e) {
			    System.out.println("location exception="+e);
			   }
	}

public void findFamilyFun(View v)
{
	TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
  String deviceid=telephonyManager.getDeviceId();
  startActivity(new Intent(MainActivity.this,SearchScreen.class));
  /*if(deviceid.equals("352167057222961"))
  {
	  startActivity(new Intent(MainActivity.this,SearchScreen.class));
  }
  else
  {
	  Toast.makeText(getApplicationContext(), "This app is not for your device",Toast.LENGTH_SHORT).show();
  }*/
}
	
	
}
