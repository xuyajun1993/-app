package com.xyj.hnu.news;

import com.xyj.hnu.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class review extends Activity implements OnClickListener{

	private ViewPager vp_review;
	private TextView tv_reviewhot;
	private TextView tv_newreview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newsreview);
		vp_review=(ViewPager) findViewById(R.id.vPager_review);
		tv_newreview=(TextView) findViewById(R.id.tv_newreview);
		tv_reviewhot=(TextView) findViewById(R.id.tv_hotreview);
		tv_newreview.setOnClickListener(this);
		tv_reviewhot.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		
	}
}
