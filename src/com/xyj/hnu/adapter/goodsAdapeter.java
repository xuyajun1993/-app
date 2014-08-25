package com.xyj.hnu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.xyj.hnu.R;
import com.xyj.hnu.cache.bitmapCache;
import com.xyj.hnu.domain.newsBean;
import com.xyj.hnu.domain.secondhandGoods;

/**
 * 二手用品适配器
 * @author xyj
 *
 */
public class goodsAdapeter extends BaseAdapter{
	private Context context;
	private  List<secondhandGoods> goodsList=new ArrayList<secondhandGoods>();
	private RequestQueue queue=null;
	private long curTime;
	//存放组件
	static class ViewHolder {
		NetworkImageView ivGoodsPreview;
		TextView tvGoodsTitle;
		TextView tvGoodsBarginPrice;
		TextView tvGoodsOriginPrice;
		TextView tvGoodsPostTime;
	}
	
	
	public goodsAdapeter(Context context, List<secondhandGoods> list, RequestQueue queue,long curTime) {
		this.context = context;
		this.goodsList = list;
		this.queue=queue;
		this.curTime=curTime;
	}

	@Override
	public int getCount() {
		return goodsList==null?0:goodsList.size();
	}

	@Override
	public Object getItem(int position) {
		return goodsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.item_secondhandgoods, null);
			holder = new ViewHolder();
			holder.ivGoodsPreview = (NetworkImageView) convertView.findViewById(R.id.ivGoodsPreview);
			holder.tvGoodsTitle = (TextView) convertView.findViewById(R.id.tvGoodsTitle);
			holder.tvGoodsBarginPrice = (TextView) convertView.findViewById(R.id.tvGoodsBarginPrice);
			holder.tvGoodsOriginPrice = (TextView) convertView.findViewById(R.id.tvGoodsOriginPrice);
		    holder.tvGoodsPostTime=(TextView) convertView.findViewById(R.id.tvGoodsPostTime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		ImageLoader loader=new ImageLoader(queue,new bitmapCache());
		holder.ivGoodsPreview.setDefaultImageResId(R.drawable.back);
		holder.ivGoodsPreview.setErrorImageResId(R.drawable.back);
		holder.ivGoodsPreview.setImageUrl(goodsList.get(position).getUrl(), loader);
		holder.tvGoodsTitle.setText(((secondhandGoods) getItem(position)).getGoodsName());
		holder.tvGoodsBarginPrice.setText(((secondhandGoods) getItem(position)).getBarginPrice());
		holder.tvGoodsOriginPrice.setText(goodsList.get(position).getOriginPrice());
		holder.tvGoodsOriginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		//计算出几个小时前
		String time="";
		int value=(int) ((curTime-goodsList.get(position).getTime())/60000);
		if(value<60)
			time=value+"分钟前";
		else{
			value=value/60;
			if(value<24)
				time=value+"小时前";
			else{
				value=value/24;
				time=value+"天前";
			}
		}
			
		holder.tvGoodsPostTime.setText(time);
		return convertView;
	}

}
