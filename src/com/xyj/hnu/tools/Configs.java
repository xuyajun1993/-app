package com.xyj.hnu.tools;

import java.util.List;

import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.domain.secondhandGoods;

public class Configs {
	public static int current = 0;
	public static RadioButton rb = null;
	public static RequestQueue queue=null;
	// 头条list
	public static List<newsBean> head_list = null;
	// 本周list
	public static List<newsBean> sug_list = null;
	// 通知list
	public static List<newsBean> noti_list = null;
	// 讲座list
	public static List<newsBean> lec_list = null;
	// 活动list
	public static List<newsBean> acti_list = null;
	
	//二手用品list
	public static List<secondhandGoods> goods_list=null;

	// banner四张图片的请求url
	public static final String[] url = { "", "", "", "" };
}
