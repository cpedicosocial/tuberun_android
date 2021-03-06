package com.papagiannis.tuberun.fetchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.Overlay;
import com.papagiannis.tuberun.BusStation;
import com.papagiannis.tuberun.DatabaseHelper;

public class RoutesBusFetcher extends Fetcher {
	private static final long serialVersionUID = 1L;
	private Context context;
	private transient GetRoutesTask task = new GetRoutesTask(context);

	private ArrayList<String> routes;
	private int direction=0;
	private HashMap<String, ArrayList<ArrayList<BusStation>>> results = new HashMap<String, ArrayList<ArrayList<BusStation>>>();

	public RoutesBusFetcher(Context c) {
		super();
		context = c;
		routes = new ArrayList<String>();
	}
	
	public RoutesBusFetcher(Context c, ArrayList<String> routes) {
		super();
		context = c;
		this.routes = (routes != null) ? routes : new ArrayList<String>();
	}
	
	public void setRoutes (ArrayList<String> routes) {
		this.routes = (routes != null) ? routes : new ArrayList<String>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update() {
		task = new GetRoutesTask(context);
		task.execute(routes);
	}

	@Override
	public Date getUpdateTime() {
		return new Date();
	}

	private class GetRoutesTask
			extends
			AsyncTask<ArrayList<String>, Integer, HashMap<String, ArrayList<ArrayList<BusStation>>>> {

		private HashMap<String, ArrayList<ArrayList<BusStation>>> result = new HashMap<String, ArrayList<ArrayList<BusStation>>>();
		private HashMap<String, Integer> resultColors=new HashMap<String, Integer>();
		Context context;

		public GetRoutesTask(Context c) {
			super();
			this.context = c;
		}

		@Override
		protected HashMap<String, ArrayList<ArrayList<BusStation>>> doInBackground(
				ArrayList<String>... routes) {
			// android.os.Debug.waitForDebugger();

			DatabaseHelper myDbHelper = new DatabaseHelper(context);
			try {
				myDbHelper.openDataBase();
				for (String route : routes[0]) {
					ArrayList<ArrayList<BusStation>> stations = myDbHelper
							.getStopsForRoute(route);
					if (stations.size() > 0 && stations.get(0).size() >= 2) {
						result.put(route, stations);
					}
				}
				results=result;
				generatePolylines();
				
			} catch (Exception e) {
				Log.w("LinesBusFetcher", e);
			} finally {
				myDbHelper.close();
			}
			return result;
		}
		
		private ArrayList<PolylineOptions> polylines;
		private ArrayList<Overlay> overlays;
		private LatLngBounds bounds;
		private final int[] colors=new int[]{Color.RED, Color.BLUE, Color.BLACK, 
				Color.argb(255, 0, 127, 14), Color.MAGENTA, Color.YELLOW, Color.CYAN, 
				Color.DKGRAY, Color.GRAY, Color.LTGRAY, Color.WHITE};

		
		public ArrayList<PolylineOptions> generatePolylines() {
			polylines=new ArrayList<PolylineOptions>();
			LatLng sw=null;
			LatLng ne=null;
			int color=0;
			int strokeWidth= (routes.size()==1) ? 8 : 5+routes.size()/2;
			for (String route:routes) {
				ArrayList<ArrayList<BusStation>> directions = getRouteStops(route);
				ArrayList<BusStation> stops = directions.get(direction); 
				PolylineOptions line=new PolylineOptions();
				for (int i = 0; i < stops.size(); i++) {
					BusStation stop1=stops.get(i);
					LatLng point=new LatLng( stop1.getLocation().getLatitude(),  stop1.getLocation().getLongitude() );
					if (sw==null) {
						sw=new LatLng(point.latitude, point.longitude);
					}
					if (ne==null) {
						ne=new LatLng(point.latitude, point.longitude);
					}
					if (point.latitude<sw.latitude) {
						sw=new LatLng(point.latitude, sw.longitude);
					}
					if (point.longitude<sw.longitude) {
						sw=new LatLng(sw.latitude, point.longitude);
					}
					if (point.latitude>ne.latitude) {
						ne=new LatLng(point.latitude, ne.longitude);
					}
					if (point.longitude>ne.longitude) {
						ne=new LatLng(ne.latitude, point.longitude);
					}
					line.add(point);
				}
				polylines.add(line);
				line.width(strokeWidth);
				line.color(colors[color%colors.length]);
				resultColors.put(route, colors[color%colors.length]);
				color++;
				if (color%2==0) strokeWidth--;
			}
			bounds=new LatLngBounds(sw, ne);
			return polylines;
		}
		
		public ArrayList<Overlay> getResultOverlays() {
			return overlays;
		}
		
		public ArrayList<PolylineOptions> getPolylines() {
			return polylines;
		}
		
		public HashMap<String, Integer> getResultLineColors() {
			return resultColors;
		}
		
		
		@Override
		protected void onPostExecute(HashMap<String, ArrayList<ArrayList<BusStation>>> res) {
			results = res;
			if (!isCancelled()) {
				notifyClients();
			}
		}

		public LatLngBounds getBounds() {
			return bounds;
		}
	}

	public ArrayList<ArrayList<BusStation>> getRouteStops(String route) {
		return results.get(route);
	}
	
	public ArrayList<Overlay> getOverlays() {
		ArrayList<Overlay> o=task.getResultOverlays();
		return o;
	}
	
	public ArrayList<PolylineOptions> getPolylines() {
		ArrayList<PolylineOptions> o=task.getPolylines();
		return o;
	}
	
	public LatLngBounds getBounds() {
		return task.getBounds();
	}
	
	public HashMap<String, Integer> getResultColors() {
		return task.getResultLineColors();
	}

	public void abort() {
		if (task != null)
			task.cancel(true);
	}

	public void setDirection(int direction) {
		this.direction=direction;
	}
	public int getDirection() {
		return direction;
	}

}
