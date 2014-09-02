package com.xyj.hnu.service;

import com.xyj.hnu.R;
import com.xyj.hnu.tools.Metrics;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class frame_week extends android.support.v4.app.Fragment {
	private ScrollView sl;
	private TextView textView1,day0;
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View week_lesson = inflater.inflate(R.layout.week_schedule, null);
		textView1 = (TextView) week_lesson.findViewById(R.id.textView1);
		sl = (ScrollView) week_lesson.findViewById(R.id.sl);
		Metrics.setSreenInfo(getActivity());
		int screen_width = Metrics.getWidthPixels();
		System.out.println("hhhhhhhh"+screen_width);
		day0 = (TextView)week_lesson.findViewById(R.id.day0);
		day0.setWidth((screen_width-42)/6);
		textView1.setWidth((screen_width-44)/6);		
		
		return week_lesson;
	}
}
