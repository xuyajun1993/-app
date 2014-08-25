package com.xyj.hnu.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyj.hnu.R;
import com.xyj.hnu.fragment.GeneralFragment;

public class personal extends GeneralFragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.personal, container,false);
		return view;
	}
}
