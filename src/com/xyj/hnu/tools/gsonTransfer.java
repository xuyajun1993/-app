package com.xyj.hnu.tools;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.view.news;

/**
 * 把json数据转成多个新闻bean的list
 * @author xyj
 *
 */
public class gsonTransfer {

	public static List<newsBean> getList(String JsonString){
		newsBean[] newsArray=new Gson().fromJson(JsonString, newsBean[].class);
		return Arrays.asList(newsArray);
	}
}
