package com.xyj.hnu.tools;

import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.xyj.hnu.domain.newsBean;

/**
 * 把json数据转成多个新闻bean的list：字段要保持一致
 * @author xyj
 *
 */
public class gsonTransfer {

	public static List<newsBean> getList(String JsonString){
		newsBean[] newsArray=new Gson().fromJson(JsonString, newsBean[].class);
		return Arrays.asList(newsArray);
	}
}
