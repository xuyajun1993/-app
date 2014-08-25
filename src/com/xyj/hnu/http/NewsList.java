package com.xyj.hnu.http;

import java.util.List;

import org.json.JSONArray;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.xyj.hnu.adapter.MyListViewAdapter;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.listview.MyListView;
import com.xyj.hnu.tools.Configs;
import com.xyj.hnu.tools.gsonTransfer;

/**
 * 返回多条新闻的json数据
 * 
 * @author xyj
 * 
 */
public class NewsList {
    String url = "http://121.40.145.214/takeaways.json#userconsent#";
	public void getRefreshList(RequestQueue queue, final Activity activity,
			final MyListViewAdapter listViewAdapter, final MyListView myLV, String type) {
		// 得到服务器传来的json
		JsonArrayRequest request = new JsonArrayRequest(url,
				new Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						// 把json转化成list
						System.out.println(response.toString());
						Configs.head_list=gsonTransfer.getList(response.toString());
						listViewAdapter.notifyDataSetChanged();
						myLV.stopRefresh();
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(activity, "刷新失败", 0).show();
					}
				});
		queue.add(request);
	}
	
	//返回加载的数据
	public void getMoreList(){
		
	}
}
