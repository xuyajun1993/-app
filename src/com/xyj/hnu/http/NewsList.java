package com.xyj.hnu.http;

import org.json.JSONArray;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.xyj.hnu.adapter.MyListViewAdapter;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.tools.Configs;
import com.xyj.hnu.tools.FileUtils;
import com.xyj.hnu.tools.gsonTransfer;

/**
 * 返回多条新闻的json数据
 * 
 * @author xyj
 * 
 */
public class NewsList {
	static String url = "http://121.40.145.214/takeaways.json#userconsent#";

	public static void getRefreshList(RequestQueue queue,
			final Activity activity, final MyListViewAdapter listViewAdapter,
			final MyListView myLV, String type) {
		// 得到服务器传来的json
		JsonArrayRequest request = new JsonArrayRequest(url,
				new Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						// 把json转化成list
						System.out.println(response.toString());
						Configs.sug_list = gsonTransfer.getList(response
								.toString());
						// 把json写到文件
						FileUtils.write(activity, Configs.sug_filename,
								response.toString());
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(activity, "刷新失败", 0).show();
					}
				});
		listViewAdapter.notifyDataSetChanged();
		myLV.stopRefresh();
		queue.add(request);
	}

	// 返回加载的数据
	public void getMoreList() {
       //将json数据追加到文件中
	}
}
