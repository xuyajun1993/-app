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
	// ͷ��list
	public static List<newsBean> head_list = null;
	// ����list
	public static List<newsBean> sug_list = null;
	// ֪ͨlist
	public static List<newsBean> noti_list = null;
	// ����list
	public static List<newsBean> lec_list = null;
	// �list
	public static List<newsBean> acti_list = null;
	
	//������Ʒlist
	public static List<secondhandGoods> goods_list=null;

	// banner����ͼƬ������url
	public static final String[] url = { "", "", "", "" };
}
