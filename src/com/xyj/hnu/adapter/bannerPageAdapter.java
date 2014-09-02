package com.xyj.hnu.adapter;

import java.util.List;

import com.android.volley.RequestQueue;
import com.xyj.hnu.http.BannerUtils;
import com.xyj.hnu.tools.Configs;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

/**
 * bannerµƒ  ≈‰∆˜
 * @author xyj
 *
 */
public class bannerPageAdapter extends PagerAdapter {
	static List<ImageView> bannerList;

	public bannerPageAdapter(List<ImageView> bannerIvList) {
		bannerPageAdapter.bannerList=bannerIvList;
	}

	@Override
	public int getCount() {
		return bannerList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0==arg1;
	}

	@Override
	public void destroyItem(View container, int position, Object object) {
		((ViewPager) container).removeView(bannerList.get(position));
	}

	@Override
	public Object instantiateItem(View container, int position) {
		((ViewPager) container).addView(bannerList.get(position));
		return bannerList.get(position);
	}
}
