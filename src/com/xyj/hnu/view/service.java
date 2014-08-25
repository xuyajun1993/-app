package com.xyj.hnu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xyj.hnu.R;
import com.xyj.hnu.domain.takeoutBean;
import com.xyj.hnu.fragment.GeneralFragment;
import com.xyj.hnu.service.emptyroomService;
import com.xyj.hnu.service.ktvService;
import com.xyj.hnu.service.movie;
import com.xyj.hnu.service.repairPost;
import com.xyj.hnu.service.secondhandgoodsService;
import com.xyj.hnu.service.takeoutService;

public class service extends GeneralFragment implements OnClickListener {
	private ImageView iv_query;
	private ImageView iv_repair;
	private ImageView iv_secondgoods;
	private ImageView iv_takeout;
	private ImageView iv_ktv;
	private ImageView iv_movie;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.service, container, false);
		iv_query = (ImageView) view.findViewById(R.id.iv_query);
		iv_repair = (ImageView) view.findViewById(R.id.iv_repair);
		iv_secondgoods = (ImageView) view.findViewById(R.id.iv_secondgoods);
		iv_takeout = (ImageView) view.findViewById(R.id.iv_takeout);
		iv_ktv = (ImageView) view.findViewById(R.id.iv_ktv);
		iv_movie = (ImageView) view.findViewById(R.id.iv_movie);
		iv_query.setOnClickListener(this);
		iv_repair.setOnClickListener(this);
		iv_secondgoods.setOnClickListener(this);
		iv_takeout.setOnClickListener(this);
		iv_ktv.setOnClickListener(this);
		iv_movie.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//跳转到空教室查询
		case R.id.iv_query:
			Intent intent_emptyroom=new Intent(getActivity(), emptyroomService.class);
			startActivity(intent_emptyroom);
			break;

		//跳转到故障报修
		case R.id.iv_repair:
			Intent intent_repair=new Intent(getActivity(), repairPost.class);
			startActivity(intent_repair);
			break;
			
        //跳转到二手物品
		case R.id.iv_secondgoods:
			Intent intent_secondgoods=new Intent(getActivity(), secondhandgoodsService.class);
			startActivity(intent_secondgoods);
			break;
			
        //跳转到外卖
		case R.id.iv_takeout:
			Intent intent_takeout=new Intent(getActivity(), takeoutService.class);
			startActivity(intent_takeout);
			break;
			
        //跳转到ktv
		case R.id.iv_ktv:
			Intent intent_ktv=new Intent(getActivity(), ktvService.class);
			startActivity(intent_ktv);
			break;
			
	    //跳转到电影
		case R.id.iv_movie:
			Intent intent_movie=new Intent(getActivity(), movie.class);
			startActivity(intent_movie);
			break;

		}
	}

}
