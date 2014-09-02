package com.xyj.hnu.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xyj.hnu.R;
import com.xyj.hnu.fragment.GeneralFragment;
import com.xyj.hnu.service.clubService;
import com.xyj.hnu.service.emptyroomService;
import com.xyj.hnu.service.flowService;
import com.xyj.hnu.service.libraryService;
import com.xyj.hnu.service.scheduleService;
import com.xyj.hnu.service.scoreService;

public class service extends GeneralFragment implements OnClickListener {
	private ImageView iv_query;
	private ImageView iv_club;
	private ImageView iv_class;
	private ImageView iv_score;
	private ImageView iv_flow;
	private ImageView iv_library;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.service, container, false);
		iv_query = (ImageView) view.findViewById(R.id.iv_query);
		iv_club = (ImageView) view.findViewById(R.id.iv_club);
		iv_class = (ImageView) view.findViewById(R.id.iv_class);
		iv_score = (ImageView) view.findViewById(R.id.iv_score);
		iv_flow = (ImageView) view.findViewById(R.id.iv_flow);
		iv_library = (ImageView) view.findViewById(R.id.iv_library);
		iv_query.setOnClickListener(this);
		iv_club.setOnClickListener(this);
		iv_class.setOnClickListener(this);
		iv_score.setOnClickListener(this);
		iv_library.setOnClickListener(this);
		iv_flow.setOnClickListener(this);
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

		//跳转到社团
		case R.id.iv_club:
			Intent intent_club=new Intent(getActivity(), clubService.class);
			startActivity(intent_club);
			break;
			
        //跳转到课表
		case R.id.iv_class:
			Intent intent_schedule=new Intent(getActivity(), scheduleService.class);
			startActivity(intent_schedule);
			break;
			
        //跳转到成绩
		case R.id.iv_score:
			Intent intent_score=new Intent(getActivity(), scoreService.class);
			startActivity(intent_score);
			break;
			
        //跳转到图书馆
		case R.id.iv_library:
			Intent intent_library=new Intent(getActivity(), libraryService.class);
			startActivity(intent_library);
			break;
			
	    //跳转到流量
		case R.id.iv_flow:
			Intent intent_flow=new Intent(getActivity(), flowService.class);
			startActivity(intent_flow);
			break;
		}
	}

}
