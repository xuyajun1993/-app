package com.xyj.hnu.news;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.xyj.hnu.R;
import com.xyj.hnu.adapter.MyListViewAdapter;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.fragment.GeneralFragment;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.listview.MyListView.IXListViewListener;
import com.xyj.hnu.listview.initMyLV;
import com.xyj.hnu.tools.Configs;

public class todayFragment extends Fragment implements IXListViewListener {

	private MyListView listView;
	private MyListViewAdapter listViewAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("today");
		View v=inflater.inflate(R.layout.news_head, null);
		listView=(MyListView) v.findViewById(R.id.lv_head);
		listView.setPullLoadEnable(true);
		listView.setXListViewListener(this);
		initMyLV lv=new initMyLV(inflater, getActivity(), listView,Configs.queue);
		lv.initNews();
		Configs.head_list=new ArrayList<newsBean>();
		
		if(Configs.head_list==null){
		for (int i = 0; i < 10; i++) {
			newsBean bean = new newsBean(
					"http://dotadb.uuu9.com/UploadFiles/Dota/Hero/klw.gif",
					"安倍死于地震", "北京时间下午三点钟安倍于地震中跌入海中", 2000);
			Configs.head_list.add(bean);
		}
		}
		listViewAdapter = new MyListViewAdapter(getActivity(),
				Configs.head_list, Configs.queue);
		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//跳转到正文界面
				Intent intent=new Intent(view.getContext(), newsContent.class);
				startActivity(intent);
			}
		});
		return v;
	}

	@Override
	public void onRefresh() {
		Configs.head_list.clear();
		newsBean bean = new newsBean(
				"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
				"子非鱼成功融资500万", "湖南大学子非鱼团队经过一番波折成功融资500万", 290);
		Configs.head_list.add(bean);
		listViewAdapter.notifyDataSetChanged();
		listView.stopRefresh();
	}

	@Override
	public void onLoadMore() {
		newsBean bean = new newsBean(
				"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
				"湖大开学了", "8月24湖南大学开学啦，新鲜的学妹", 290);
		Configs.head_list.add(bean);
		System.out.println(Configs.head_list.size());
		listViewAdapter.notifyDataSetChanged();
		listView.stopLoadMore();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("onpause");
	}
	@Override
	public void onResume() {
		super.onResume();
		System.out.println("today onresume");
	}
}
