package com.papagiannis.tuberun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.papagiannis.tuberun.binders.RailDeparturesBinder;
import com.papagiannis.tuberun.favorites.DeparturesFavorite;
import com.papagiannis.tuberun.favorites.Favorite;
import com.papagiannis.tuberun.fetchers.DeparturesRailFetcher;
import com.papagiannis.tuberun.fetchers.Observer;

public class RailDeparturesActivity extends ListActivity implements Observer,
		OnClickListener {
	private DeparturesRailFetcher fetcher;
	private final ArrayList<HashMap<String, Object>> departures_list = new ArrayList<HashMap<String, Object>>();
	private Station station;
	private ListView listView;
	private TextView lineTextView;
	private LinearLayout emptyLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		create();
	}

	private void create() {
		setContentView(R.layout.bus_departures);
		departures_list.clear();

		TextView title = (TextView) findViewById(R.id.title_textview);
		title.setText("Rail Departures");
		Button back_button = (Button) findViewById(R.id.back_button);
		Button logo_button = (Button) findViewById(R.id.logo_button);
		OnClickListener back_listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		};
		back_button.setOnClickListener(back_listener);
		logo_button.setOnClickListener(back_listener);

		emptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
		emptyLayout.setVisibility(View.GONE);

		listView = getListView();
		lineTextView = (TextView) findViewById(R.id.line_textview);

		Bundle extras = getIntent().getExtras();
		station = (Station) extras.get("station");
		if (station == null)
			return;

		lineTextView.setText(station.getName().toUpperCase(Locale.UK));

		fetcher = new DeparturesRailFetcher(station);
		fetcher.registerCallback(this);
		fetcher.update();

		Favorite.getFavorites(this);

		View updateButton = findViewById(R.id.button_update);
		updateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				emptyLayout.setVisibility(View.GONE);
				setListAdapter(null);
				fetcher.update();
			}
		});

		ToggleButton favButton = (ToggleButton) findViewById(R.id.add_favorite);
		DeparturesFavorite fav = new DeparturesFavorite(LineType.RAIL,
				new DeparturesRailFetcher(station));
		fav.setIdentification(station.getCode());
		fav.setStation_nice(station.getName());
		favButton.setChecked(Favorite.isFavorite(fav));
		favButton.setOnClickListener(this);
	}

	@Override
	public void update() {
		departures_list.clear();

		ArrayList<HashMap<String, String>> trains = fetcher.getUnsortedDepartures();
		for (HashMap<String, String> train : trains) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("time1", train.get("time"));
			m.put("time2", train.get("time"));
			m.put("destination", train.get("destination"));
			m.put("status", train.get("position"));
			// m.put("status", "random crap");
			String plat = train.get("platform").trim();
			m.put("platform", plat);
			departures_list.add(m);
		}

		if (trains.isEmpty()) {
			emptyLayout.setVisibility(View.VISIBLE);
		} else {
			emptyLayout.setVisibility(View.GONE);
		}

		SimpleAdapter adapter = new SimpleAdapter(this, departures_list,
				R.layout.rail_departures_status, new String[] { "time1", "time2",
						"platform", "destination", "status", "favorite" },
				new int[] { R.id.time1, R.id.time2, R.id.platform, 
					R.id.destination,R.id.status, R.id.add_favorite });
		adapter.setViewBinder(new RailDeparturesBinder(this));
		setListAdapter(adapter);
		listView.setVisibility(View.VISIBLE);

	}

	@Override
	public void onClick(View v) {
		ToggleButton tb = (ToggleButton) v;
		if (tb.isChecked()) {
			DeparturesFavorite fav = new DeparturesFavorite(LineType.RAIL,
					new DeparturesRailFetcher(station));
			fav.setStation_nice(station.getName());
			fav.setIdentification(station.getCode());
			Favorite.addFavorite(fav, this);
		} else {
			DeparturesFavorite fav = new DeparturesFavorite(LineType.RAIL,
					new DeparturesRailFetcher(station));
			fav.setIdentification(station.getCode());
			fav.setStation_nice(station.getName());
			Favorite.removeFavorite(fav, this);

			// also try this without station_nice for compatibility before 1.2.3
			fav = new DeparturesFavorite(LineType.RAIL,
					new DeparturesRailFetcher(station));
			fav.setIdentification(station.getCode());
			Favorite.removeFavorite(fav, this);
		}
	}
}