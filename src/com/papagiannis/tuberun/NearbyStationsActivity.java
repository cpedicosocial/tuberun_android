package com.papagiannis.tuberun;

import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.papagiannis.tuberun.fetchers.Observer;
import com.papagiannis.tuberun.fetchers.ReverseGeocodeFetcher;

public class NearbyStationsActivity extends FragmentActivity implements
		LocationListener {
	private static final int LOCATION_SERVICE_FAILED = 0;

	FragmentActivity self = this;

	ReverseGeocodeFetcher geocoder = new ReverseGeocodeFetcher(this, null);
	Observer geolocationObserver = new Observer() {
		@Override
		public void update() {
			displayLocation(geocoder.getResult());
		}
	};

	TabHost mTabHost;
	ViewPager mViewPager;
	TabsAdapter mTabsAdapter;
	NearbyStationsListFragment undergroundFragment;
	NearbyCycleStationsListFragment cycleFragment;
	NearbyBusLinesListFragment busesFragment;
	NearbyOysterListFragment oysterFragment;
	NearbyRailListFragment railFragment;
	TextView location_textview;
	TextView location_accuracy_textview;
	Button back_button;
	Button logo_button;
	Button map_button;
	TextView title_textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new SlidingBehaviour(this, R.layout.nearby);

		location_accuracy_textview = (TextView) findViewById(R.id.location_accuracy_textview);
		location_textview = (TextView) findViewById(R.id.location_textview);
		title_textview = (TextView) findViewById(R.id.title_textview);
		back_button = (Button) findViewById(R.id.back_button);
		logo_button = (Button) findViewById(R.id.logo_button);
		map_button = (Button) findViewById(R.id.map_button);
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();

		mViewPager = (ViewPager) findViewById(R.id.pager);

		mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);

		mTabsAdapter.addTab(
				mTabHost.newTabSpec("underground").setIndicator("Underground"),
				NearbyStationsListFragment.class, null);
		mTabsAdapter.getTabsTextView().setVisibility(View.GONE);
		mTabsAdapter.getTabsImageView().setVisibility(View.VISIBLE);
		mTabsAdapter.getTabsImageView().setImageResource(R.drawable.tube);

		mTabsAdapter.addTab(
				mTabHost.newTabSpec("buses").setIndicator("Buses"),
				NearbyBusLinesListFragment.class, null);
		mTabsAdapter.getTabsTextView().setVisibility(View.GONE);
		mTabsAdapter.getTabsImageView().setVisibility(View.VISIBLE);
		mTabsAdapter.getTabsImageView().setImageResource(R.drawable.buses);
		
		mTabsAdapter.addTab(
				mTabHost.newTabSpec("cycle hire").setIndicator("Cycle Hire"),
				NearbyCycleStationsListFragment.class, null);
		mTabsAdapter.getTabsTextView().setVisibility(View.GONE);
		mTabsAdapter.getTabsImageView().setVisibility(View.VISIBLE);
		mTabsAdapter.getTabsImageView().setImageResource(R.drawable.cycle_hire);
		
		
		mTabsAdapter.addTab(
				mTabHost.newTabSpec("rail").setIndicator("Rail"),
				NearbyRailListFragment.class, null);
		mTabsAdapter.getTabsTextView().setVisibility(View.GONE);
		mTabsAdapter.getTabsImageView().setVisibility(View.VISIBLE);
		mTabsAdapter.getTabsImageView().setImageResource(R.drawable.rail_tab);
		
		mTabsAdapter.addTab(
				mTabHost.newTabSpec("oyster").setIndicator("Oyster"),
				NearbyOysterListFragment.class, null);
		mTabsAdapter.getTabsTextView().setVisibility(View.GONE);
		mTabsAdapter.getTabsImageView().setVisibility(View.VISIBLE);
		mTabsAdapter.getTabsImageView().setImageResource(R.drawable.oyster_wletters);

		
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
		}
		undergroundFragment = (NearbyStationsListFragment) mTabsAdapter
				.getItem(0);
		busesFragment = (NearbyBusLinesListFragment) mTabsAdapter
				.getItem(1);
		cycleFragment = (NearbyCycleStationsListFragment) mTabsAdapter
				.getItem(2);
		railFragment = (NearbyRailListFragment) mTabsAdapter
				.getItem(3);
		oysterFragment = (NearbyOysterListFragment) mTabsAdapter
				.getItem(4);

		map_button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int t=mTabHost.getCurrentTab();
				switch (t) {
					case 0:
						undergroundFragment.showAllInMap();
						break;
					case 2:
						cycleFragment.showAllInMap();
						break;
					case 1:
						busesFragment.showAllInMap();
						break;
					case 3:
						railFragment.showAllInMap();
						break;
					case 4:
						oysterFragment.showAllInMap();
						break;
				}
			}
		});

		SharedPreferences preferences=getPreferences(MODE_PRIVATE);
		Boolean cycleNotice=preferences.getBoolean("cycleNotice", false);
		if (cycleNotice==false) {
			Editor editor = preferences.edit();
			editor.putBoolean("cycleNotice", true);
			editor.commit();
			showToast();
		}
		
		locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
	}
	
	private void showToast() {
		String msg="Cycle Hire data are updated every 3 minutes";
		Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
		toast.show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString("tab", mTabHost.getCurrentTabTag());
	}

	@Override
	public void onPause() {
		super.onPause();
		if (locationManager != null)
			locationManager.removeUpdates(this);
	}

	@Override
	public void onResume() {
		super.onResume();
		if (locationManager != null)
			requestLocationUpdates();
	}

	// LocationListener Methods
	LocationManager locationManager;
	Location lastKnownLocation;
	Date started;

	@Override
	public void onLocationChanged(Location l) {
		if (LocationHelper.isBetterLocation(l, lastKnownLocation)) {
			lastKnownLocation = l;
			displayLocation(null);
			reverseGeocode(lastKnownLocation);
			if (undergroundFragment!=null) undergroundFragment.locationChanged(lastKnownLocation);
			if (busesFragment!=null) busesFragment.locationChanged(lastKnownLocation);
			if (cycleFragment!=null) cycleFragment.locationChanged(lastKnownLocation);
			if (oysterFragment!=null) oysterFragment.locationChanged(lastKnownLocation);
			if (railFragment!=null) railFragment.locationChanged(lastKnownLocation);
		}
	}

	private void reverseGeocode(Location l) {
		geocoder.abort();
		geocoder = new ReverseGeocodeFetcher(this, l);
		geocoder.registerCallback(geolocationObserver).update();
	}

	private void displayLocation(List<Address> result) {
		if (result == null || result.size() < 1) {
			location_textview.setText("Fetching address...");
		} else {
			String geoc_result = result.get(0).getAddressLine(0);
			location_textview.setText(geoc_result);
		}
		location_accuracy_textview.setText("accuracy="
				+ Math.round(lastKnownLocation.getAccuracy()) + "m");
	}
	
	@Override
	public void onProviderDisabled(String arg0) {

	}

	@Override
	public void onProviderEnabled(String arg0) {

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

	}

	@SuppressWarnings("deprecation")
	private void requestLocationUpdates() {
		try {
			if (locationManager != null) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 2 * 1000, 5, this);
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 3 * 1000, 5, this);
			}
		} catch (Exception e) {
			Log.w("LocationService",e);
			showDialog(LOCATION_SERVICE_FAILED);

		}
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog result = null;
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		switch (id) {
		case LOCATION_SERVICE_FAILED:
			builder.setTitle("Location Service Failed")
					.setMessage(
							"Does you device support location services? Turn them on in the settings.")
					.setCancelable(true)
					.setPositiveButton("OK", null);
			result = builder.create();
			break;
		}
		return result;
	}

}