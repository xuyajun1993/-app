package com.xyj.hnu.view;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.database.MergeCursor;
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
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.news.actiFragment;
import com.xyj.hnu.news.lecFragment;
import com.xyj.hnu.news.notiFragment;
import com.xyj.hnu.news.todayFragment;
import com.xyj.hnu.news.weekFragment;
import com.xyj.hnu.tools.Metrics;

public class news extends GeneralFragment implements OnClickListener {
	private static final long serialVersionUID = 1L;
	private ViewPager viewPager;
	private ImageView cursor,imageView1;
	private int current;
	private List<Fragment> views = new ArrayList<Fragment>();// 用来保存几个view
	private TextView txtToday;
	private TextView txtWeek;
	private TextView txtLec;
	private TextView txtNoti;
	private TextView txtActi;
	MyListView lv_head;
	MyListView lv_sug;
	MyListView lv_noti;
	MyListView lv_lec;
	MyListView lv_acti;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.news, container, false);
		viewPager = (ViewPager) view.findViewById(R.id.vPager_news);
		cursor = (ImageView) view.findViewById(R.id.cursor);
		imageView1 = (ImageView) view.findViewById(R.id.imageView1);
		// 给每个标题监听
		txtToday = ((TextView) view.findViewById(R.id.txtToday));
		txtWeek = ((TextView) view.findViewById(R.id.txtWeek));
		txtLec = ((TextView) view.findViewById(R.id.txtLec));
		txtNoti = ((TextView) view.findViewById(R.id.txtNoti));
		txtActi = ((TextView) view.findViewById(R.id.txtActi));
		txtToday.setOnClickListener(this);
		txtWeek.setOnClickListener(this);
		txtActi.setOnClickListener(this);
		txtLec.setOnClickListener(this);
		txtNoti.setOnClickListener(this);
		System.out.println("oncreateview");
		viewPager.setAdapter(new MyPageAdaper(getActivity()
				.getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(new MyOnPageListener());
		viewPager.setCurrentItem(0);
		// 修改游标的长度
		LayoutParams cursorlp = cursor.getLayoutParams();
		LayoutParams imageViewlp = imageView1.getLayoutParams();
		cursorlp.width = (Metrics.getWidthPixels()-2*imageViewlp.width)/5;
		
		//cursorlp.height = txtTodaylp.height;
		cursor.setLayoutParams(cursorlp);
		
		current = cursor.getLeft();
		// viewPager.setOffscreenPageLimit(0);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		views.add(new todayFragment());
		views.add(new weekFragment());
		views.add(new notiFragment());
		views.add(new lecFragment());
		views.add(new actiFragment());
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
		case R.id.txtToday:
			viewPager.setCurrentItem(0);
			changeCursor(txtToday);
			break;
		case R.id.txtWeek:
			viewPager.setCurrentItem(1);
			changeCursor(txtWeek);
			break;

		case R.id.txtNoti:
			viewPager.setCurrentItem(2);
			changeCursor(txtNoti);
			break;

		case R.id.txtLec:
			viewPager.setCurrentItem(3);
			changeCursor(txtLec);
			break;
		case R.id.txtActi:
			viewPager.setCurrentItem(4);
			changeCursor(txtActi);
			break;
		default:
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
				changeCursor(txtToday);
				break;
			case 1:
				changeCursor(txtWeek);
				break;
			case 2:
				changeCursor(txtNoti);
				break;
			case 3:
				changeCursor(txtLec);
				break;
			case 4:
				changeCursor(txtActi);
				break;
			}
			// int one = 2 * offset + cursorWidth;
			// int two = one * 2;
			// int three = one * 3;
			// int four = one * 4;
			// switch (originalIndex) {
			// case 0:
			// txtToday.setTextColor(getResources().getColor(R.color.black));
			// if (arg0 == 1) {
			// animation = new TranslateAnimation(0, one, 0, 0);
			// txtWeek.setTextColor(getResources().getColor(R.color.red));
			// }
			// if (arg0 == 2) {
			// animation = new TranslateAnimation(0, two, 0, 0);
			// txtNoti.setTextColor(getResources().getColor(R.color.red));
			// }
			// if (arg0 == 3) {
			// animation = new TranslateAnimation(0, three, 0, 0);
			// txtLec.setTextColor(getResources().getColor(R.color.red));
			// }
			// if (arg0 == 4) {
			// animation = new TranslateAnimation(0, four, 0, 0);
			// txtActi.setTextColor(getResources().getColor(R.color.red));
			// }
			// break;
			// case 1:
			// txtWeek.setTextColor(getResources().getColor(R.color.black));
			// if (arg0 == 0) {
			// animation = new TranslateAnimation(one, 0, 0, 0);
			// txtToday.setTextColor(getResources().getColor(R.color.red));
			// }
			// if (arg0 == 2) {
			// animation = new TranslateAnimation(one, two, 0, 0);
			// txtNoti.setTextColor(getResources().getColor(R.color.red));
			// }
			// if (arg0 == 3) {
			// animation = new TranslateAnimation(one, three, 0, 0);
			// txtLec.setTextColor(getResources().getColor(R.color.red));
			// }
			// if (arg0 == 4) {
			// animation = new TranslateAnimation(one, four, 0, 0);
			// txtActi.setTextColor(getResources().getColor(R.color.red));
			// }
			// break;
			// case 2:
			// txtNoti.setTextColor(getResources().getColor(R.color.black));
			// if (arg0 == 1) {
			// txtWeek.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(two, one, 0, 0);
			// }
			// if (arg0 == 0) {
			// txtToday.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(two, 0, 0, 0);
			// }
			// if (arg0 == 3) {
			// txtLec.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(two, three, 0, 0);
			// }
			// if (arg0 == 4) {
			// txtActi.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(two, four, 0, 0);
			// }
			// break;
			// case 3:
			// txtLec.setTextColor(getResources().getColor(R.color.black));
			// if (arg0 == 1) {
			// txtWeek.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(three, one, 0, 0);
			// }
			// if (arg0 == 0) {
			// txtToday.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(three, 0, 0, 0);
			// }
			// if (arg0 == 2) {
			// txtNoti.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(three, two, 0, 0);
			// }
			// if (arg0 == 4) {
			// txtActi.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(three, four, 0, 0);
			// }
			// break;
			// case 4:
			// txtActi.setTextColor(getResources().getColor(R.color.black));
			// if (arg0 == 1) {
			// txtWeek.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(four, one, 0, 0);
			// }
			// if (arg0 == 0) {
			// txtToday.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(four, 0, 0, 0);
			// }
			// if (arg0 == 3) {
			// txtLec.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(four, three, 0, 0);
			// }
			// if (arg0 == 2) {
			// txtNoti.setTextColor(getResources().getColor(R.color.red));
			// animation = new TranslateAnimation(four, two, 0, 0);
			// }
			// break;
			// }
			// animation.setFillAfter(true);
			// animation.setDuration(300);
			// cursor.startAnimation(animation);
			//
			// originalIndex = arg0;
		}

	}
}
