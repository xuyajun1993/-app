package com.xyj.hnu.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.xyj.hnu.R;
import com.xyj.hnu.adapter.AdvViewPager;
import com.xyj.hnu.adapter.MyListViewAdapter;
import com.xyj.hnu.adapter.bannerPageAdapter;

/**
 * 初始化头条，推荐等板块 根据类型不同返回不同类型的信息
 * 
 * @author xyj
 * 
 */
public class initMyLV  {
	// 代表当前页
	int currentPage = 0;
	LayoutInflater inflater;
	Activity activity;
	MyListView myLV;
	RequestQueue queue;
	MyListViewAdapter listViewAdapter;

	public initMyLV(LayoutInflater inflater, Activity activity,
			MyListView myLV, RequestQueue queue) {
		this.inflater = inflater;
		this.activity = activity;
		this.myLV = myLV;
		this.queue = queue;
		System.out.println("initmylv");
	}

	// 初始化信息，
	public void initNews() {
		// 创建请求队列对象
//		myLV.setPullLoadEnable(true);
//		myLV.setXListViewListener(this);
//		List<newsBean> head_list = new ArrayList<newsBean>();
//		for (int i = 0; i < 10; i++) {
//			newsBean bean = new newsBean(
//					"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
//					"安倍死于地震", "北京时间下午三点钟安倍于地震中跌入海中", 2000);
//			head_list.add(bean);
//		}
//		listViewAdapter = new MyListViewAdapter(activity,
//				head_list, queue);
//		myLV.setAdapter(listViewAdapter);
//		myLV.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				//跳转到正文界面
//				Intent intent=new Intent(view.getContext(), newsContent.class);
//				activity.startActivity(intent);
//				
//			}
//		});

		if (myLV.getId() == R.id.lv_head) {
			RelativeLayout rlAdv = (RelativeLayout) inflater.inflate(
					R.layout.sliding_advertisement, null);
			final AdvViewPager advViewPager = (AdvViewPager) rlAdv
					.findViewById(R.id.vpAdv);
			ViewGroup group = (ViewGroup) rlAdv.findViewById(R.id.viewGroup);
			// 获取banner图片
			// final List<View> bannerList =
			// BannerUtils.getBannerList(activity);
			final List<ImageView> bannerIvList = new ArrayList<ImageView>();
			ImageView iv1 = new ImageView(activity);
			iv1.setScaleType(ScaleType.FIT_XY);
			ImageView iv2 = new ImageView(activity);
			iv2.setScaleType(ScaleType.FIT_XY);
			ImageView iv3 = new ImageView(activity);
			iv3.setScaleType(ScaleType.FIT_XY);
			ImageView iv4 = new ImageView(activity);
			iv4.setScaleType(ScaleType.FIT_XY);
			bannerIvList.add(iv1);
			bannerIvList.add(iv2);
			bannerIvList.add(iv3);
			bannerIvList.add(iv4);
			// 保存下面变化的tag
			final ImageView[] DotArray = new ImageView[bannerIvList.size()];
			// 设置适配器
			bannerPageAdapter bannerAdapter = new bannerPageAdapter(
					bannerIvList, queue);
			advViewPager.setAdapter(bannerAdapter);
			advViewPager.setOnPageChangeListener(new OnPageChangeListener() {
				@Override
				public void onPageSelected(int arg0) {
					currentPage = arg0;
					for (int i = 0; i < bannerIvList.size(); i++) {
						if (i == arg0) {
							DotArray[i]
									.setBackgroundResource(R.drawable.banner_dian_focus);
						} else {
							DotArray[i]
									.setBackgroundResource(R.drawable.banner_dian_blur);
						}
					}
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {

				}

				@Override
				public void onPageScrollStateChanged(int arg0) {

				}
			});
			// 在右下角添加滑动tag
			ImageView imageView;
			for (int i = 0; i < bannerIvList.size(); i++) {
				imageView = new ImageView(activity);
				imageView.setLayoutParams(new LayoutParams(20, 20));
				DotArray[i] = imageView;
				if (i == 0) {
					DotArray[i]
							.setBackgroundResource(R.drawable.banner_dian_focus);
				} else {
					DotArray[i]
							.setBackgroundResource(R.drawable.banner_dian_blur);
				}
				group.addView(DotArray[i]);
			}
			// 实现banner自动播放
			final Handler handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					advViewPager.setCurrentItem(msg.what);
					super.handleMessage(msg);
				}
			};
			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Thread.sleep(3000);
							currentPage++;
							if (currentPage > bannerIvList.size() - 1) {
								currentPage = 0;
							}
							handler.sendEmptyMessage(currentPage);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}).start();
			myLV.addHeaderView(rlAdv);
		}
		else{
			LinearLayout ll=(LinearLayout) inflater.inflate(R.layout.ivbanner, null);
			ImageView iv=(ImageView) ll.findViewById(R.id.iv_banner);
			iv.setBackgroundResource(R.drawable.yuelu);
			myLV.addHeaderView(ll);
		}
	}

}
