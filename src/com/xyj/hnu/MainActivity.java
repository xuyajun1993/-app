package com.xyj.hnu;

import java.util.ArrayList;

import com.android.volley.toolbox.Volley;
import com.xyj.hnu.R;
import com.xyj.hnu.fragment.MainFragment;
import com.xyj.hnu.tools.Configs;
import com.xyj.hnu.tools.Metrics;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

public class MainActivity extends Activity {
	private ViewPager mViewPager;
	private ImageView mPage0;
	private ImageView mPage1;
	private ImageView mPage2;
	private ImageView mPage3;
	private Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化设备参数
		Metrics.setSreenInfo(this);
		Configs.queue = Volley.newRequestQueue(this);

		// 读取shareprefernce中，看是否存在学号和密码，如果有，已经登陆过，如果没有，开启欢迎界面
		SharedPreferences preferences = getSharedPreferences("config",
				MODE_PRIVATE);
		boolean isWelcomed = preferences.getBoolean("isWelcomed", false);
		String user_xh = preferences.getString("USER_XH", null);
		String passwd = preferences.getString("PASSWD", null);
		if (!isWelcomed) {
			setContentView(R.layout.activity_main);
			welcome();
		} else if (TextUtils.isEmpty(user_xh) || TextUtils.isEmpty(passwd)) {
			setContentView(R.layout.start);
			Start2Login();
		} else {
			setContentView(R.layout.start);
			StarttoOnline();
		}
	}

	private void Start2Login() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(MainActivity.this, login.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		}, 1000);
	}

	private void StarttoOnline() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(MainActivity.this,
						MainFragment.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		}, 1000);
	}

	private void welcome() {
		// 绑定
		mViewPager = (ViewPager) findViewById(R.id.hnu_viewpager);
		mViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		mPage0 = (ImageView) findViewById(R.id.page0);
		mPage1 = (ImageView) findViewById(R.id.page1);
		mPage2 = (ImageView) findViewById(R.id.page2);
		mPage3 = (ImageView) findViewById(R.id.page3);

		// 将要分页显示的View装入数组中
		LayoutInflater mLi = LayoutInflater.from(this);
		View view1 = mLi.inflate(R.layout.welcome1, null);
		View view2 = mLi.inflate(R.layout.welcome2, null);
		View view3 = mLi.inflate(R.layout.welcome3, null);
		View view4 = mLi.inflate(R.layout.welcome4, null);
		// 每个页面的view数据
		final ArrayList<View> views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);
		views.add(view4);
		// login=(Button) view4.findViewById(R.id.login);
		// 填充ViewPager的数据适配器
		PagerAdapter mPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public int getCount() {
				return views.size();
			}

			@Override
			public void destroyItem(View container, int position, Object object) {
				((ViewPager) container).removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(View container, int position) {
				if (position == 3) {
					btn_login = (Button) views.get(position).findViewById(
							R.id.login);
					btn_login.setOnClickListener(new OnClickListener() {

						public void onClick(View v) {
							Intent intent = new Intent(MainActivity.this,
									login.class);
							startActivity(intent);
							MainActivity.this.finish();
						}
					});
				}
				((ViewPager) container).addView(views.get(position));
				return views.get(position);
			}
		};

		mViewPager.setAdapter(mPagerAdapter);
	}

	class MyOnPageChangeListener implements OnPageChangeListener {

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
				// 可能向左向右滑动，所以需要把前后都设置
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 1:
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 2:
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			case 3:
				mPage3.setImageDrawable(getResources().getDrawable(
						R.drawable.page_now));
				mPage2.setImageDrawable(getResources().getDrawable(
						R.drawable.page));
				break;
			}
		}
	}

}
