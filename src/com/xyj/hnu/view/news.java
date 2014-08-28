package com.xyj.hnu.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyj.hnu.R;
import com.xyj.hnu.fragment.GeneralFragment;
import com.xyj.hnu.news.actiFragment;
import com.xyj.hnu.news.lecFragment;
import com.xyj.hnu.news.notiFragment;
import com.xyj.hnu.news.SugFragment;
import com.xyj.hnu.news.MyFragment;
import com.xyj.hnu.tools.Metrics;

public class news extends GeneralFragment implements OnClickListener {
	private static final long serialVersionUID = 1L;
	private ViewPager viewPager;
	private ImageView cursor,imageView1;
	private int current;
	private List<Fragment> views = new ArrayList<Fragment>();// 用来保存几个view
	private TextView tvNoti;
	private TextView tvActi;
	private TextView tvLec;
	private TextView tvSug;
	private TextView tvMy;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news, container, false);
		viewPager = (ViewPager) view.findViewById(R.id.vPager_news);
		cursor = (ImageView) view.findViewById(R.id.cursor);
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		// 给每个标题监听
		tvNoti = ((TextView) view.findViewById(R.id.txtNoti));
		tvActi = ((TextView) view.findViewById(R.id.txtActi));
		tvLec = ((TextView) view.findViewById(R.id.txtLec));
		tvSug = ((TextView) view.findViewById(R.id.txtSug));
		tvMy = ((TextView) view.findViewById(R.id.txtMy));
		tvSug.setOnClickListener(this);
		tvNoti.setOnClickListener(this);
		tvActi.setOnClickListener(this);
		tvMy.setOnClickListener(this);
		tvLec.setOnClickListener(this);
		System.out.println("oncreateview");
		viewPager.setAdapter(new MyPageAdaper(getActivity()
				.getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new MyOnPageListener());
		viewPager.setCurrentItem(0);
		// 修改游标的长度
		LayoutParams cursorlp = cursor.getLayoutParams();
		LayoutParams imageViewlp = imageView1.getLayoutParams();
		cursorlp.width = (Metrics.getWidthPixels()-2*imageViewlp.width)/5;
		
		//cursorlp.height = tvNotilp.height;
		cursor.setLayoutParams(cursorlp);
		
		current = cursor.getLeft();
		// viewPager.setOffscreenPageLimit(0);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		views.add(new SugFragment());
		views.add(new notiFragment());
		views.add(new actiFragment());
		views.add(new lecFragment());
		views.add(new MyFragment());
	}

	@SuppressLint("ResourceAsColor")
	public void changeCursor(TextView endT) {
		int start = current;
		int end = endT.getLeft();
		// 构造动画
		TranslateAnimation move = new TranslateAnimation(start, end, 0, 0);
		move.setDuration(100);
		move.setFillAfter(true);
		cursor.startAnimation(move);
		current = end;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.txtSug:
			viewPager.setCurrentItem(0);
			changeCursor(tvSug);
			break;
		case R.id.txtNoti:
			viewPager.setCurrentItem(1);
			changeCursor(tvNoti);
			break;
		case R.id.txtActi:
			viewPager.setCurrentItem(2);
			changeCursor(tvActi);
			break;

		case R.id.txtLec:
			viewPager.setCurrentItem(3);
			changeCursor(tvLec);
			break;

		case R.id.txtMy:
			viewPager.setCurrentItem(4);
			changeCursor(tvMy);
			break;
		}
	}

	private class MyPageAdaper extends FragmentStatePagerAdapter {

		public MyPageAdaper(FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int arg0) {
			System.out.println("viewpager" + arg0);
			return views.get(arg0);
		}

		@Override
		public int getCount() {
			return views.size();
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("viewpager destroy");
	}

	@Override
	public void onPause() {
		super.onPause();
		System.out.println("viewpager pause");
	}

	@Override
	public void onResume() {
		super.onResume();
		System.out.println("viewpager onresume");
	}

	private class MyOnPageListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				changeCursor(tvSug);
				break;
			case 1:
				changeCursor(tvNoti);
				break;
			case 2:
				changeCursor(tvActi);
				break;
			case 3:
				changeCursor(tvLec);
				break;
			case 4:
				changeCursor(tvMy);
				break;
			}
		}

	}
}
