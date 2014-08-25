package com.xyj.hnu.http;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.Volley;
import com.xyj.hnu.R;
import com.xyj.hnu.cache.bitmapCache;

import android.app.Activity;
import android.widget.ImageView;
/**
 * �������ϻ�ȡbanner
 * @author xyj
 *
 */
public class BannerUtils {
     static int index=0;
     int[] array={R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
//	//����bannerͼƬ
	public  void getBanner(RequestQueue queue,ImageView iv,String url) {
		// ����һ��ImageLoader����
		ImageLoader loader=new ImageLoader(queue,new bitmapCache());
		//��ȡһ��ImageListener����
		ImageListener listener=loader.getImageListener(iv, array[index], array[index]);
		//����ImageLoader��get()�������������ϵ�ͼƬ��
		loader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
		index++;
		if(index==3)
			index=0;
	}
}
