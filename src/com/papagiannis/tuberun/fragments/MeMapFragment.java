package com.papagiannis.tuberun.fragments;

import java.util.Date;
import java.util.List;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.maps.MapController;
import com.google.android.maps.Overlay;
import com.papagiannis.tuberun.R;

/*
 * A MapActivity that always shows the user's location
 */
public class MeMapFragment extends Fragment implements LocationListener {

	protected GoogleMap gMap;
	protected MapController mapController;
	protected LocationManager locationManager;
	protected final LatLng LONDON = new LatLng(51.501496, -0.124240);
	protected static final int TWO_MINUTES = 1000 * 60 * 2;
	private static final int LOCATION_SERVICE_FAILED = 0;

	protected Location lastKnownLocation;
	protected Date started;
	protected List<Overlay> mapOverlays;
	protected Overlay myPushpin;
	protected Button backButton;
	protected Button logoButton;
	protected TextView titleTextView;
	protected LinearLayout titleLayout;
	protected Button myLocationButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View fragment = inflater.inflate(R.layout.me_map_fragment, container,
				false);
		myLocationButton = (Button) fragment
				.findViewById(R.id.mylocation_button);

		gMap = ((SupportMapFragment) getFragmentManager().findFragmentById(
				R.id.map)).getMap();
		if (gMap == null)
			return fragment;

		gMap.setMyLocationEnabled(true);
		gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LONDON, 16));

		// lastKnownLocation = locationManager
		// .getLastKnownLocation(LocationManager.GPS_PROVIDER);
		// if (lastKnownLocation == null)
		// lastKnownLocation = locationManager
		// .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		// if (lastKnownLocation != null) {
		// myPushpin = generateMyLocationPushPin(lastKnownLocation);
		// mapOverlays.add(myPushpin);
		// animateToHere(lastKnownLocation);
		// } else {
		// Location l_london = new Location("");
		// l_london.setLongitude(gp_london.getLongitudeE6() / (float) 1000000);
		// l_london.setLatitude(gp_london.getLatitudeE6() / (float) 1000000);
		// l_london.setAccuracy(200);
		// lastKnownLocation = l_london;
		// myPushpin = generateMyLocationPushPin(l_london);
		// mapOverlays.add(myPushpin);
		// }
		//
		return fragment;
	}

	public void onLocationChanged(Location l) {
		if (isBetterLocation(l, lastKnownLocation)) {
			lastKnownLocation = l;

		}
	}

	public void animateToMarkers(Iterable<Marker> markers) {
		if (markers == null) return;
		LatLngBounds.Builder bc = new LatLngBounds.Builder();
		for (Marker item : markers) {
			bc.include(item.getPosition());
		}
		gMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bc.build(), 50));
	}

	/**
	 * Determines whether one Location reading is better than the current
	 * Location fix
	 * 
	 * @param location
	 *            The new Location that you want to evaluate
	 * @param currentBestLocation
	 *            The current Location fix, to which you want to compare the new
	 *            one
	 */
	public static boolean isBetterLocation(Location location,
			Location currentBestLocation) {
		if (currentBestLocation == null) {
			// A new location is always better than no location
			return true;
		}

		// Check whether the new location fix is newer or older
		long timeDelta = location.getTime() - currentBestLocation.getTime();
		boolean isSignificantlyNewer = timeDelta > TWO_MINUTES;
		boolean isSignificantlyOlder = timeDelta < -TWO_MINUTES;
		boolean isNewer = timeDelta > 0;

		// If it's been more than two minutes since the current location, use
		// the new location
		// because the user has likely moved
		if (isSignificantlyNewer) {
			return true;
			// If the new location is more than two minutes older, it must be
			// worse
		} else if (isSignificantlyOlder) {
			return false;
		}

		// Check whether the new location fix is more or less accurate
		int accuracyDelta = (int) (location.getAccuracy() - currentBestLocation
				.getAccuracy());
		boolean isSignificantlyMoreAccurate = accuracyDelta < -10;

		// Check if the old and new location are from the same provider
		// boolean isFromSameProvider = isSameProvider(location.getProvider(),
		// currentBestLocation.getProvider());

		float distance = location.distanceTo(currentBestLocation);
		boolean hasMovedSignificantly = distance > 5;

		if (isNewer && isSignificantlyMoreAccurate) {
			return true;
		}
		if (isNewer && hasMovedSignificantly) {
			return true;
		}
		return false;
	}

	private void requestLocationUpdates() {
		try {
			if (locationManager != null) {
				locationManager.requestLocationUpdates(
						LocationManager.NETWORK_PROVIDER, 2 * 1000, 5, this);
				locationManager.requestLocationUpdates(
						LocationManager.GPS_PROVIDER, 3 * 1000, 5, this);
			}
		} catch (Exception e) {
			Log.w("LocationService", e);
			showDialog(LOCATION_SERVICE_FAILED);
		}
	}

	protected void showDialog(int id) {
		DialogFragment newFragment;
		String title = "";
		String message = "";
		switch (id) {
		case LOCATION_SERVICE_FAILED:
			title = "Location Service Failed";
			message = "Does you device support location services? Turn them on in the settings.";
			break;
		}
		newFragment = AlertDialogFragment.newInstance(title, message);
		newFragment.show(getFragmentManager(), "dialog");
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

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}
}