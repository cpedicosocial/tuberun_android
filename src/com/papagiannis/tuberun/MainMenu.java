package com.papagiannis.tuberun;

import java.util.ArrayList;
import java.util.HashMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.maps.MapActivity;
import com.papagiannis.tuberun.fetchers.Observer;
import com.papagiannis.tuberun.fetchers.OysterFetcher;
import com.papagiannis.tuberun.stores.CredentialsStore;
import com.slidingmenu.lib.SlidingMenu;

public class MainMenu extends FrameLayout 
		implements OnClickListener, OnCheckedChangeListener, Observer {
	public static final String SHOWMAP="showMap";
	
	private Context context;
	private SlidingMenu menu;
	
	Button menuButton;
	Button oysterButton;
	Button oysterButtonActive;
	
	TableRow statusesRow;
	TableRow departuresRow;
	TableRow favoritesRow;
	TableRow nearbyRow;
	TableRow mapRow;
	TableRow plannerRow;
	TableRow claimsRow;
	TableRow oysterRow;
	
	CheckBox autostartCheckbox;
	
	LinearLayout balanceLayout;
	ProgressBar balanceProgressbar;
	TextView balanceTextview;
	
	public MainMenu(Context context, SlidingMenu menu) {
		super(context);
		init(context);
		this.menu = menu;
	}
	
	private void init(Context context) {
		this.context=context;
		LayoutInflater.from(context).inflate(R.layout.main_menu, this, true);

		// ********** Initialise references ********************
		
		statusesRow=(TableRow) findViewById(R.id.status_row);
		departuresRow=(TableRow) findViewById(R.id.departures_row);
		favoritesRow=(TableRow) findViewById(R.id.favorites_row);
		nearbyRow=(TableRow) findViewById(R.id.nearby_row);
		mapRow=(TableRow) findViewById(R.id.map_row);
		plannerRow=(TableRow) findViewById(R.id.planner_row);
		claimsRow=(TableRow) findViewById(R.id.claims_row);
		oysterRow=(TableRow) findViewById(R.id.oyster_row);
		
		autostartCheckbox=(CheckBox)findViewById(R.id.autostart_checkbox);
		
		balanceLayout=(LinearLayout)findViewById(R.id.layout_balance);
		balanceProgressbar=(ProgressBar)findViewById(R.id.progressbar_balance);
		balanceTextview=(TextView)findViewById(R.id.textview_balance);
		
		statusesRow.setOnClickListener(this);
		departuresRow.setOnClickListener(this);
		favoritesRow.setOnClickListener(this);
		nearbyRow.setOnClickListener(this);
		mapRow.setOnClickListener(this);
		plannerRow.setOnClickListener(this);
		claimsRow.setOnClickListener(this);
		oysterRow.setOnClickListener(this);
//		balanceLayout.setOnClickListener(this);
		
		
		// ********** initialize state ******************** 
		
		initializeOyster();
		
		SharedPreferences shPrefs = context.getSharedPreferences(TubeRun.PREFERENCES, TubeRun.MODE_PRIVATE);
		int viewId = shPrefs.getInt( TubeRun.AUTOSTART, TubeRun.AUTOSTART_NONE);
		int currentViewId=getAutoStartViewId();
		autostartCheckbox.setChecked( viewId!=TubeRun.AUTOSTART_NONE && viewId==currentViewId );
		autostartCheckbox.setOnCheckedChangeListener(this);
		
	}
	
	private OysterFetcher fetcher;
	private void initializeOyster() {
		CredentialsStore store = CredentialsStore.getInstance();
		ArrayList<String> credentials = store.getAll(context);
		if (credentials.size() != 2) {
			balanceLayout.setVisibility(View.GONE);
			return;
		}
		fetcher=OysterFetcher.getInstance(credentials.get(0), credentials.get(1));
		if (fetcher.hasResult()) {
			update();
			return;
		}
		
		balanceLayout.setVisibility(View.VISIBLE);
		balanceTextview.setVisibility(View.GONE);
		balanceProgressbar.setVisibility(View.VISIBLE);
		fetcher.registerCallback(this);
		fetcher.update();
	}
	
	@Override
	public void update() {
		SharedPreferences preferences = context.getSharedPreferences(TubeRun.PREFERENCES, Context.MODE_PRIVATE);
		String defaultCard = preferences.getString(OysterActivity.DEFAULT_CARD, "");
		
		CharSequence balance="";
		HashMap<String,String> cards = fetcher.getCards();
		if (!defaultCard.equals("") && cards.containsKey(defaultCard)) {
			balance=cards.get(defaultCard);
		}
		else balance = fetcher.getResult();
		
		balanceTextview.setText(balance);
		balanceLayout.setVisibility(View.VISIBLE);
		balanceTextview.setVisibility(View.VISIBLE);
		balanceProgressbar.setVisibility(View.GONE);
	}
	
	private int getAutoStartViewId() {
		int viewId=TubeRun.AUTOSTART_NONE;
		Class<? extends Context> c=context.getClass();
		if (c==StatusActivity.class) viewId=R.id.button_status;
		else if (c==SelectLineActivity.class) viewId=R.id.button_departures;
		else if (c==NearbyStationsActivity.class) viewId=R.id.button_nearby;
		else if (c==PlanActivity.class) viewId=R.id.button_planner;
		else if (c==ClaimsActivity.class) viewId=R.id.button_claims;
		else if (c==OysterActivity.class) viewId=R.id.button_oyster_active;
		else if (c==FavoritesActivity.class) viewId=R.id.button_favorites;
		return viewId;
	}
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		SharedPreferences shPrefs = context.getSharedPreferences(TubeRun.PREFERENCES, TubeRun.MODE_PRIVATE);
		int viewId = shPrefs.getInt( TubeRun.AUTOSTART, TubeRun.AUTOSTART_NONE);
		
		viewId= (isChecked) ? getAutoStartViewId() : TubeRun.AUTOSTART_NONE;
		
		Editor editor = shPrefs.edit();
		editor.putInt(TubeRun.AUTOSTART, viewId);
		editor.commit();
	}
	
	@SuppressLint("NewApi")
	public void onClick(View v) {
		Intent i = null;
		Activity a=(Activity)context;
		Class<? extends Activity> c=a.getClass();
		boolean finishActivity=false; //closes the old activity
		switch (v.getId()) {
		case R.id.status_row:
			i = (c!=StatusActivity.class) ? new Intent(context, StatusActivity.class) : null;
			break;
		case R.id.departures_row:
			i = (c!=SelectLineActivity.class) ? new Intent(context, SelectLineActivity.class) : null;
			break;
		case R.id.map_row:
			if (c!=MapActivity.class) {
				if (!isMapAvailable()) {
					requestMapDownload();
					return;
				}
				i = new Intent(context, StatusMapActivity.class);
				i.putExtra("line",
						LinePresentation.getStringRespresentation(LineType.ALL));
				i.putExtra("type", "maps");
				finishActivity=false;
			}
			else i=null;
			break;
		case R.id.nearby_row:
			i = (c!=NearbyStationsActivity.class) ? new Intent(context, NearbyStationsActivity.class) : null;
			break;
		case R.id.favorites_row:
			i = (c!=FavoritesActivity.class) ? new Intent(context, FavoritesActivity.class) : null;
			break;
		case R.id.claims_row:
			i = (c!=ClaimsActivity.class) ? new Intent(context, ClaimsActivity.class) : null;
			break;
		case R.id.planner_row:
			i = (c!=PlanActivity.class) ? new Intent(context, PlanActivity.class) : null;
			break;
		case R.id.oyster_row:
		case R.id.layout_balance:
			i = (c!=OysterActivity.class) ? new Intent(context, OysterActivity.class) : null;
			break;
		case R.id.button_logo:
			i = (c!=AboutActivity.class) ? new Intent(context, AboutActivity.class) : null;
			break;
		}
		if (i!=null) {
			i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
			if (finishActivity) {
				//OysterFetcher (which is reused) holds a reference to this. To enable garbage collection:
				if (fetcher!=null) fetcher.deregisterCallback(this);
				a.finish();
			}
			else {
				menu.toggle();
			}
			context.startActivity(i);
		}
		else {
			if (menuButton!=null) menuButton.callOnClick();
		}
	}
	
	private void requestMapDownload() {
		Intent i=new Intent(context, TubeRun.class);
		i.putExtra(SHOWMAP, true);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
		context.startActivity(i);
		
		Activity a=(Activity) context;
		a.finish();
		
	}

	private boolean isMapAvailable() {
		SharedPreferences preferences = context.getSharedPreferences(TubeRun.PREFERENCES, TubeRun.MODE_PRIVATE);
		return preferences.getBoolean(TubeRun.TUBEMAP_EXISTS, false);
	}

	public void setMenuButton(Button menuButton) {
		this.menuButton=menuButton;
	}

}
