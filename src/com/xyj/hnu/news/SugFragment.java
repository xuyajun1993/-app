package com.xyj.hnu.news;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.xyj.hnu.R;
import com.xyj.hnu.adapter.MyListViewAdapter;
import com.xyj.hnu.app.AppContext;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.fragment.GeneralFragment;
import com.xyj.hnu.http.BannerUtils;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.listview.MyListView.IXListViewListener;
import com.xyj.hnu.listview.initMyLV;
import com.xyj.hnu.tools.Configs;
import com.xyj.hnu.tools.FileUtils;

public class SugFragment extends GeneralFragment implements IXListViewListener {

	private MyListView listView;
	private MyListViewAdapter listViewAdapter;
	private AppContext appContext;
	private LayoutInflater layoutInflater;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutInflater=inflater;
		View v = inflater.inflate(R.layout.news_sug, null);
		listView = (MyListView) v.findViewById(R.id.lv_sug);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(this);
		
		// 从文件中读取
		initMyLV lv = new initMyLV(layoutInflater, getActivity(), listView);
		lv.initNews();
		//从文件中得到json字符串
		String jsonData=FileUtils.read(getActivity(), "sug.txt");
		//解析json
		
		// 判断是否自动刷新(每天第一次打开自动刷新)
		appContext = (AppContext) getActivity().getApplication();
		// 检查网络情况
		if (appContext.isNetworkConnected()) {
			if (!appContext.sug_isrefresh) {
				new Handler().postDelayed(new Runnable() {
					@Override
					public void run() {
						listView.updateHeaderHeight(120);
						listView.doRefreshing();
					}
				}, 500);
			}
		}

		Configs.sug_list = new ArrayList<newsBean>();
		listViewAdapter = new MyListViewAdapter(getActivity(),
				Configs.sug_list, Configs.queue);
		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 跳转到正文界面
				Intent intent = new Intent(view.getContext(), newsContent.class);
				startActivity(intent);
			}
		});
		return v;
	}

	@Override
	public void onRefresh() {
		// 自动联网获得数据：banner+news
		for(int i=0;i<Configs.bannerIvList.size();i++){
			BannerUtils.getBanner(Configs.queue, Configs.bannerIvList.get(i),Configs.url[i]);
		}
		
		
		//设置已经刷新过
		appContext.sug_isrefresh=true;
	}

	@Override
	public void onLoadMore() {
		
//		listViewAdapter.notifyDataSetChanged();
//		listView.stopLoadMore();
	}

}
