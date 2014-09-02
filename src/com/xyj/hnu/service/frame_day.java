package com.xyj.hnu.service;

import com.xyj.hnu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class frame_day extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View day_lesson = inflater.inflate(R.layout.day_schedule, null);
		return day_lesson;
	}
}
