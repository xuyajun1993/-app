package com.xyj.hnu.tools;

import java.util.List;

import android.widget.ImageView;
import android.widget.RadioButton;

import com.android.volley.RequestQueue;
import com.xyj.hnu.domain.newsBean;

public class Configs {
	public static int current = 0;
	public static RadioButton rb = null;
	public static RequestQueue queue=null;
	// 推荐list
	public static List<newsBean> sug_list = null;
	// 通知list
	public static List<newsBean> noti_list = null;
	// 讲座list
	public static List<newsBean> lec_list = null;
	// 活动list
	public static List<newsBean> acti_list = null;
	//社团list
	public static List<newsBean> my_list = null;
	
	//banner
	public static List<ImageView> bannerIvList=null;
	
	
	// banner四张图片的请求url
	public static final String[] url = { "", "", "", "" };
	
	//几种信息保存的文件名
	public static final String sug_filename="sug.txt";
	public static final String noti_filename="noti.txt";
	public static final String lec_filename="lec.txt";
	public static final String acti_filename="acti.txt";
	public static final String my_filename="my.txt";
	
	//用于登陆验证的url
	public static final String loginUrl = "";
	
    //登陆结果
	public static boolean loginResult=false;
	public static int apk_Version=1;
	
	//下载最新版本的url
	public static final String apk_url="";
}
