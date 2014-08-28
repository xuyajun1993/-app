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
 * 从网络上获取banner
 * @author xyj
 *
 */
public class BannerUtils {
     static int index=0;
     int[] array={R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4};
//	//返回banner图片
	public void getBanner(RequestQueue queue,ImageView iv,String url) {
		// 创建一个ImageLoader对象。
		ImageLoader loader=new ImageLoader(queue,new bitmapCache());
		//获取一个ImageListener对象。
		ImageListener listener=loader.getImageListener(iv, 0, 0);
		//调用ImageLoader的get()方法加载网络上的图片。
		loader.get("http://img.my.csdn.net/uploads/201404/13/1397393290_5765.jpeg", listener);
		index++;
		if(index==3)
			index=0;
	}
}
