package com.papagiannis.tuberun;

import java.util.Locale;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class StatusMapActivity extends Activity {
	WebView mWebView;

	private boolean isWeekend = false;

	@SuppressLint("SetJavaScriptEnabled")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statuses_map);

		mWebView = (WebView) findViewById(R.id.webview);
		mWebView.clearCache(true);
		
		WebSettings settings=mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setPluginState(WebSettings.PluginState.ON);
		
		Bundle extras = getIntent().getExtras();
		String url = "";
		if (extras != null) {
			String type = extras.getString("type");
			if (type.equals("maps")) {
				settings.setBuiltInZoomControls(true);
				settings.setUseWideViewPort(true);
				url = "file:///android_asset/map_";
				String last = extras.getString("line").toLowerCase(
						Locale.ENGLISH)
						+ ".html";
				url += last;
			} else if (type.equals("status")) {
				isWeekend = Boolean.parseBoolean(extras.getString("isWeekend"));
				if (isWeekend)
					url = "http://www.tfl.gov.uk/tfl/common/maps/swf/map-wrapper.swf?offset=weekend&mode=track";
				else
					url = "http://www.tfl.gov.uk/tfl/common/maps/swf/map-wrapper.swf?offset=now&mode=track";
			}
			mWebView.loadUrl(url);
			if (type.equals("maps")) {
				settings.setDefaultZoom(ZoomDensity.FAR);
			}
		}
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			//getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	    }
	}
	
}
