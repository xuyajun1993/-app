package com.xyj.hnu.adapter;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.xyj.hnu.R;
import com.xyj.hnu.cache.bitmapCache;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.view.news;
/**
 * 新闻适配器
 * @author xyj
 *
 */
public class MyListViewAdapter extends BaseAdapter{

	private Context context;
	private  List<newsBean> newsList=new ArrayList<newsBean>();
	private RequestQueue queue=null;
	//存放组件
	static class ViewHolder {
		NetworkImageView ivPreview;
		TextView tvTitle;
		TextView tvContent;
		TextView tvReview;
	}
	
	
	public MyListViewAdapter(Context context, List<newsBean> list, RequestQueue queue) {
		this.context = context;
		this.newsList = list;
		this.queue=queue;
	}

	@Override
	public int getCount() {
		return newsList==null?0:newsList.size();
	}

	@Override
	public Object getItem(int position) {
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_news, null);
			holder = new ViewHolder();
			holder.ivPreview = (NetworkImageView) convertView.findViewById(R.id.ivPreview);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
			holder.tvReview = (TextView) convertView.findViewById(R.id.tvReview);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader loader=new ImageLoader(queue,new bitmapCache());
		holder.ivPreview.setDefaultImageResId(R.drawable.back);
		holder.ivPreview.setErrorImageResId(R.drawable.back);
		holder.ivPreview.setImageUrl(newsList.get(position).getUrl(), loader);
		holder.tvTitle.setText(((newsBean) getItem(position)).getNewsTitle());
		holder.tvContent.setText(newsList.get(position).getNewsContent());
		holder.tvReview.setText(newsList.get(position).getNum()+"跟帖");
		return convertView;
	}

}
