package com.xyj.hnu.service;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.xyj.hnu.R;
import com.xyj.hnu.adapter.goodsAdapeter;
import com.xyj.hnu.http.goodsList;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.listview.MyListView.IXListViewListener;
import com.xyj.hnu.tools.Configs;

public class secondhandgoodsService extends Activity implements OnClickListener{

	private TextView textview;
	private PopupWindow popw;
	private MyListView lv_secondgoods;
	private TextView tv_digits;
	private TextView tv_books;
	private TextView tv_cloths;
	private TextView tv_studytools;
	private TextView tv_others;
	private LinearLayout ll_digits;
	private LinearLayout ll_books;
	private LinearLayout ll_cloths;
	private LinearLayout ll_studytools;
	private LinearLayout ll_others;
	private ImageButton ib_back;
	private ImageButton ib_search;
	private ImageButton ib_add;
	private String goods_type = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.secondhandgoods);
		textview = (TextView) findViewById(R.id.tv_allgoods);
		lv_secondgoods = (MyListView) findViewById(R.id.lv_secondgoods);
		ib_add = (ImageButton) findViewById(R.id.goods_add);
		ib_back = (ImageButton) findViewById(R.id.goods_back);
		ib_search = (ImageButton) findViewById(R.id.goods_search);
		ib_add.setOnClickListener(this);
		ib_search.setOnClickListener(this);
		ib_back.setOnClickListener(this);
		textview.setOnClickListener(this);
		// 得到当前时间
		long curTime = System.currentTimeMillis();
		// 得到二手用品list
        new goodsList().setGoodsList();
		goodsAdapeter adapter = new goodsAdapeter(getApplicationContext(),
				Configs.goods_list, Configs.queue, curTime);
		lv_secondgoods.setAdapter(adapter);
		lv_secondgoods.setPullLoadEnable(true);
		lv_secondgoods.setXListViewListener(new IXListViewListener() {
			@Override
			public void onRefresh() {
				
			}
			
			@Override
			public void onLoadMore() {
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_digits:
			goods_type = "0";
			Toast.makeText(getApplicationContext(), goods_type, 0).show();
			break;

		case R.id.ll_books:
			goods_type = "1";
			Toast.makeText(getApplicationContext(), goods_type, 0).show();
			break;
		case R.id.ll_cloths:
			goods_type = "2";
			Toast.makeText(getApplicationContext(), goods_type, 0).show();
			break;
		case R.id.ll_studytools:
			goods_type = "3";
			Toast.makeText(getApplicationContext(), goods_type, 0).show();
			break;

		case R.id.ll_others:
			goods_type = "4";
			break;

		case R.id.goods_add:
			// 跳转到发布二手物品
			Intent intent = new Intent(getApplication(), publishGoods.class);
			startActivity(intent);
			break;

		case R.id.goods_back:
			finish();
			break;

		case R.id.goods_search:
			popSearch();
			break;

		case R.id.tv_allgoods:
			popTypes();
		}
	}

	// 弹出搜索框
	private void popSearch() {
		if (null != popw && popw.isShowing()) {
			popw.dismiss();
		} else {
			View view = getLayoutInflater().inflate(R.layout.searchpop,
					null);
			popw = new PopupWindow(view, textview.getWidth()*2,
					LayoutParams.WRAP_CONTENT, true);
			popw.setBackgroundDrawable(new BitmapDrawable());
			popw.showAsDropDown(textview,-textview.getWidth()/3,10);
		}
	}

	// 弹出类别框
	private void popTypes() {
		if (null != popw && popw.isShowing()) {
			popw.dismiss();
		} else {
			// 向服务器获取最新各个种类数量

			View view = getLayoutInflater().inflate(R.layout.secondhandpop,
					null);
			tv_digits = (TextView) view.findViewById(R.id.tv_digits);
			tv_books = (TextView) view.findViewById(R.id.tv_book);
			tv_cloths = (TextView) view.findViewById(R.id.tv_cloth);
			tv_studytools = (TextView) view.findViewById(R.id.tv_studytools);
			tv_others = (TextView) view.findViewById(R.id.tv_others);
			ll_digits = (LinearLayout) view.findViewById(R.id.ll_digits);
			ll_books = (LinearLayout) view.findViewById(R.id.ll_books);
			ll_cloths = (LinearLayout) view.findViewById(R.id.ll_cloths);
			ll_studytools = (LinearLayout) view
					.findViewById(R.id.ll_studytools);
			ll_others = (LinearLayout) view.findViewById(R.id.ll_others);
			ll_books.setOnClickListener(this);
			ll_cloths.setOnClickListener(this);
			ll_digits.setOnClickListener(this);
			ll_others.setOnClickListener(this);
			ll_studytools.setOnClickListener(this);
			popw = new PopupWindow(view, textview.getWidth(),
					LayoutParams.WRAP_CONTENT, true);
			popw.setBackgroundDrawable(new BitmapDrawable());
			popw.showAsDropDown(textview);
			popw.setOutsideTouchable(true);
		}
	}
}
