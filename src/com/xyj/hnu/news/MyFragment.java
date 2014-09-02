package com.xyj.hnu.news;

import com.xyj.hnu.R;
import com.xyj.hnu.adapter.MyListViewAdapter;
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

public class MyFragment extends Fragment implements IXListViewListener{

	private MyListView listView;
	private MyListViewAdapter listViewAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v=inflater.inflate(R.layout.news_my, null);
		listView = (MyListView) v.findViewById(R.id.lv_my);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(this);
		initMyLV lv = new initMyLV(inflater, getActivity(), listView);
		lv.initNews();
		listViewAdapter = new MyListViewAdapter(getActivity(),
				Configs.my_list, Configs.queue);
		listView.setAdapter(listViewAdapter);
		return v;
	}

	@Override
	public void onRefresh() {
		
	}

	@Override
	public void onLoadMore() {
	}
}
