package com.xyj.hnu.news;

import com.xyj.hnu.R;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.listview.initMyLV;
import com.xyj.hnu.listview.MyListView.IXListViewListener;
import com.xyj.hnu.tools.Configs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class lecFragment extends Fragment implements IXListViewListener{
	private MyListView listView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.news_lec, null);
		listView = (MyListView) v.findViewById(R.id.lv_lec);
		initMyLV lv = new initMyLV(inflater, getActivity(), listView,
				Configs.queue);
		lv.initNews();
		
		return v;
	}
	@Override
	public void onRefresh() {
		
	}
	@Override
	public void onLoadMore() {
		
	}
}
