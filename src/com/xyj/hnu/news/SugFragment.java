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
import com.xyj.hnu.app.AppContext;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.fragment.GeneralFragment;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.listview.MyListView.IXListViewListener;
import com.xyj.hnu.listview.initMyLV;
import com.xyj.hnu.tools.Configs;

public class SugFragment extends Fragment implements IXListViewListener {

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
		// �ж��Ƿ��Զ�ˢ�£�������ǣ����ļ��ж�ȡ����
		appContext = (AppContext) getActivity().getApplication();
		// ����������
		if (appContext.isNetworkConnected()) {
			if (!appContext.sug_isrefresh) {
				listView.doRefreshing();
			}
		}
		// ���ļ��ж�ȡ

		Configs.sug_list = new ArrayList<newsBean>();
//
//		if (Configs.head_list == null) {
//			for (int i = 0; i < 10; i++) {
//				newsBean bean = new newsBean(
//						"http://dotadb.uuu9.com/UploadFiles/Dota/Hero/klw.gif",
//						"�������ڵ���", "����ʱ�����������Ӱ����ڵ����е��뺣��", 2000);
//				Configs.head_list.add(bean);
//			}
//		}
		listViewAdapter = new MyListViewAdapter(getActivity(),
				Configs.sug_list, Configs.queue);
		listView.setAdapter(listViewAdapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ��ת�����Ľ���
				Intent intent = new Intent(view.getContext(), newsContent.class);
				startActivity(intent);
			}
		});
		return v;
	}

	@Override
	public void onRefresh() {
//		Configs.head_list.clear();
//		newsBean bean = new newsBean(
//				"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
//				"�ӷ���ɹ�����500��", "���ϴ�ѧ�ӷ����ŶӾ���һ�����۳ɹ�����500��", 290);
//		Configs.head_list.add(bean);
//		listViewAdapter.notifyDataSetChanged();
//		listView.stopRefresh();
		// �Զ�����������ݣ�banner+news
		initMyLV lv = new initMyLV(layoutInflater, getActivity(), listView,
				Configs.queue);
		lv.initNews();
		
		//�����Ѿ�ˢ�¹�
		appContext.noti_isrefresh=true;
	}

	@Override
	public void onLoadMore() {
//		newsBean bean = new newsBean(
//				"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
//				"����ѧ��", "8��24���ϴ�ѧ��ѧ�������ʵ�ѧ��", 290);
//		Configs.head_list.add(bean);
//		System.out.println(Configs.head_list.size());
		listViewAdapter.notifyDataSetChanged();
		listView.stopLoadMore();
	}

}
