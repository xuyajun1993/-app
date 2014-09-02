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
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View week_lesson = inflater.inflate(R.layout.week_schedule, null);
		return week_lesson;
	}
}
