package com.papagiannis.tuberun;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * This is a helper class that implements the management of tabs and all details
 * of connecting a ViewPager with associated TabHost. It relies on a trick.
 * Normally a tab host has a simple API for supplying a View or Intent that each
 * tab will show. This is not sufficient for switching between pages. So instead
 * we make the content part of the tab host 0dp high (it is not shown) and the
 * TabsAdapter supplies its own dummy view to show as the tab content. It
 * listens to changes in tabs, and takes care of switch to the correct paged in
 * the ViewPager whenever the selected tab changes.
 */
public class TabsAdapter extends FragmentPagerAdapter implements
		TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {
	private final Context mContext;
	private final TabHost mTabHost;
	private final ViewPager mViewPager;
	private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();
	private final ArrayList<Fragment> mPastFragments = new ArrayList<Fragment>();

	static final class TabInfo {
		private final Class<?> clss;
		private final Bundle args;

		TabInfo(Class<?> _class, Bundle _args) {
			clss = _class;
			args = _args;
		}
	}

	static class DummyTabFactory implements TabHost.TabContentFactory {
		private final Context mContext;

		public DummyTabFactory(Context context) {
			mContext = context;
		}

		@Override
		public View createTabContent(String tag) {
			View v = new TextView(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}
	}

	public TabsAdapter(FragmentActivity activity, TabHost tabHost,
			ViewPager pager) {
		super(activity.getSupportFragmentManager());
		mContext = activity;
		mTabHost = tabHost;
		mViewPager = pager;
		mTabHost.setOnTabChangedListener(this);
		mViewPager.setAdapter(this);
		mViewPager.setOnPageChangeListener(this);
	}

	public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
		String tag = tabSpec.getTag();
		tabSpec.setContent(new DummyTabFactory(mContext));
		tabSpec.setIndicator(createTabView(mContext, tag));
		TabInfo info = new TabInfo(clss, args);
		mTabs.add(info);
		mTabHost.addTab(tabSpec);
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mTabs.size();
	}

	@Override
	public Fragment getItem(int position) {
		TabInfo info = mTabs.get(position);
		Fragment res = null;
		if (mPastFragments.size() > position)
			res = mPastFragments.get(position);
		else {
			res = Fragment
					.instantiate(mContext, info.clss.getName(), info.args);
			mPastFragments.add(position, res);
		}
		return res;
	}

	@Override
	public void onTabChanged(String tabId) {
		int position = mTabHost.getCurrentTab();
		mViewPager.setCurrentItem(position);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		// Unfortunately when TabHost changes the current tab, it kindly
		// also takes care of putting focus on it when not in touch mode.
		// The jerk.
		// This hack tries to prevent this from pulling focus out of our
		// ViewPager.
		TabWidget widget = mTabHost.getTabWidget();
		int oldFocusability = widget.getDescendantFocusability();
		widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
		mTabHost.setCurrentTab(position);
		widget.setDescendantFocusability(oldFocusability);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	private TextView tabsTextView;
	private ImageView tabsImageView;
	public  View createTabView(final Context context, final String text) {
		View view = LayoutInflater.from(context)
				.inflate(R.layout.tabs_background, null);
		tabsTextView = (TextView) view.findViewById(R.id.tabs_textview);
		tabsTextView.setText(text.toUpperCase(Locale.ENGLISH));
		tabsImageView = (ImageView) view.findViewById(R.id.tabs_imageview);
		return view;
	}

	public TextView getTabsTextView() {
		return tabsTextView;
	}

	public ImageView getTabsImageView() {
		return tabsImageView;
	}

	
	

}
