package com.papagiannis.tuberun.fetchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

public class ReverseGeocodeFetcher extends Fetcher {
	private static final long serialVersionUID = 1L;
	private Context context;
	private Location location;
	private List<Address> result=new ArrayList<Address>();
	
	public ReverseGeocodeFetcher(Context context, Location l) {
		super();
		this.context=context;
		this.location=l;
	}
	
	private transient AsyncTask<Double, Integer, List<Address>> task=null;
	private void reverseGeocode(Location l) {
		final Geocoder myLocation = new Geocoder(context, Locale.getDefault());
		if (myLocation != null) {
			 task = new AsyncTask<Double, Integer, List<Address>>() {
				@Override
				protected List<Address> doInBackground(Double... params) {
					List<Address> result = new ArrayList<Address>();
					try {
						result = myLocation.getFromLocation(params[0],
								params[1], 1);
					} catch (Exception e) {
						Log.w(getClass().toString(), e);
					}
					return result;
				}

				protected void onPostExecute(List<Address> res) {
					result=res;
					notifyClients();
				}
			};
			task.execute(l.getLatitude(), l.getLongitude());
		}
	}
	
	public ReverseGeocodeFetcher setLocation(Location l) {
		this.location=l;
		return this;
	}
	
	@Override
	public void update() {
		reverseGeocode(location);
	}

	@Override
	public Date getUpdateTime() {
		return new Date();
	}
	
	public List<Address> getResult() {
		return result;
	}
	
	@Override
    public void abort() {
    	if (task!=null) task.cancel(true);
    }

}
