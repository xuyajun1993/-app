package com.xyj.hnu.service;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyj.hnu.R;

public class scheduleService extends FragmentActivity {
	private TextView day_lesson, week_lesson;
	private ImageView cursor;
	private ViewPager tabPager;
	private int zero = 0;// ����ͼƬƫ����
	private int currIndex = 0;// ��ǰҳ�����
	private int one;// ����ˮƽ����λ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lessontable);
		tabPager = (ViewPager) findViewById(R.id.tabpager);
		// ��tabPager���ü���
		tabPager.setOnPageChangeListener(new MyonPagerChangeListener());

		day_lesson = (TextView) findViewById(R.id.day_lesson);
		week_lesson = (TextView) findViewById(R.id.week_lesson);
		cursor = (ImageView) findViewById(R.id.imageView2);

		day_lesson.setOnClickListener(new MyOnClickListener(0));
		week_lesson.setOnClickListener(new MyOnClickListener(1));
		LayoutParams params = day_lesson.getLayoutParams();
		one = params.width;

		

		// ÿ��ҳ���view����
		final ArrayList<android.support.v4.app.Fragment> frames = new ArrayList<android.support.v4.app.Fragment>();
		frames.add(new frame_day());
		frames.add(new frame_week());

		// ���ViewPager������������
		PagerAdapter mPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return frames.size();
			}
			
			@Override
			public android.support.v4.app.Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return frames.get(arg0);
			}
		};

		tabPager.setAdapter(mPagerAdapter);
	}

	/**
	 * ͷ��������
	 * 
	 * @author why
	 * 
	 */
	public class MyOnClickListener implements OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			tabPager.setCurrentItem(index);
		}
	}

	/**
	 * �ڲ��࣬ҳ���л�����
	 * 
	 * @author why
	 * 
	 */
	public class MyonPagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			Animation animation = null;
			switch (arg0) {
			case 0:
				if (currIndex == 1) {
					animation = new TranslateAnimation(one, 0, 0, 0);
				}
				break;
			case 1:
				if (currIndex == 0) {
					animation = new TranslateAnimation(zero, one, 0, 0);
				}

				break;
			}
			currIndex = arg0;
			animation.setFillAfter(true);// True:ͼƬͣ�ڶ�������λ��
			animation.setDuration(150);
			cursor.startAnimation(animation);
		}
	}
}
