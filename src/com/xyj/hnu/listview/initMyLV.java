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
 * ��ʼ��ͷ�����Ƽ��Ȱ�� �������Ͳ�ͬ���ز�ͬ���͵���Ϣ
 * 
 * @author xyj
 * 
 */
public class initMyLV  {
	// ����ǰҳ
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

	// ��ʼ����Ϣ��
	public void initNews() {
		// ����������ж���
//		myLV.setPullLoadEnable(true);
//		myLV.setXListViewListener(this);
//		List<newsBean> head_list = new ArrayList<newsBean>();
//		for (int i = 0; i < 10; i++) {
//			newsBean bean = new newsBean(
//					"http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg",
//					"�������ڵ���", "����ʱ�����������Ӱ����ڵ����е��뺣��", 2000);
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
//				//��ת�����Ľ���
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
			// ��ȡbannerͼƬ
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
			// ��������仯��tag
			final ImageView[] DotArray = new ImageView[bannerIvList.size()];
			// ����������
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
			// �����½���ӻ���tag
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
			// ʵ��banner�Զ�����
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
