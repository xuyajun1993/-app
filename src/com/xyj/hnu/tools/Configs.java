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
	// �Ƽ�list
	public static List<newsBean> sug_list = null;
	// ֪ͨlist
	public static List<newsBean> noti_list = null;
	// ����list
	public static List<newsBean> lec_list = null;
	// �list
	public static List<newsBean> acti_list = null;
	//����list
	public static List<newsBean> my_list = null;
	
	//banner
	public static List<ImageView> bannerIvList=null;
	
	
	// banner����ͼƬ������url
	public static final String[] url = { "", "", "", "" };
	
	//������Ϣ������ļ���
	public static final String sug_filename="sug.txt";
	public static final String noti_filename="noti.txt";
	public static final String lec_filename="lec.txt";
	public static final String acti_filename="acti.txt";
	public static final String my_filename="my.txt";
	
	//���ڵ�½��֤��url
	public static final String loginUrl = "";
	
    //��½���
	public static boolean loginResult=false;
	public static int apk_Version=1;
	
	//�������°汾��url
	public static final String apk_url="";
}
