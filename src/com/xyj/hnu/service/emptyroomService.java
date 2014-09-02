package com.xyj.hnu.service;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xyj.hnu.R;
import com.xyj.hnu.tools.Metrics;

@SuppressLint("NewApi")
public class emptyroomService extends Activity implements  android.widget.CompoundButton.OnCheckedChangeListener  {
	private LinearLayout classes;
	private RadioGroup roomrg;
	private TextView textView;
	private RadioButton radio1,radio2,radio3,radio4,
	radio5,radio6,radio7,radioall,radiofl,radiozh,radiodl,radioej,radiozl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emptyroomquery);
		roomrg = (RadioGroup) findViewById(R.id.roomrg);
		classes = (LinearLayout) findViewById(R.id.classes);
		textView = (TextView) findViewById(R.id.tv_write);
		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);
		radio3 = (RadioButton) findViewById(R.id.radio3);
		radio4 = (RadioButton) findViewById(R.id.radio4);
		radio5 = (RadioButton) findViewById(R.id.radio5);
		radio6 = (RadioButton) findViewById(R.id.radio6);
		radio7 = (RadioButton) findViewById(R.id.radio7);
		radioall = (RadioButton) findViewById(R.id.radioall);
		radiofl = (RadioButton) findViewById(R.id.radiofl);
		radiozh = (RadioButton) findViewById(R.id.radiozh);
		radiodl = (RadioButton) findViewById(R.id.radiodl);
		radioej = (RadioButton) findViewById(R.id.radioej);
		radiozl = (RadioButton) findViewById(R.id.radiozl);
		
		
		radio1.setOnCheckedChangeListener(this);
		radio2.setOnCheckedChangeListener(this);
		radio3.setOnCheckedChangeListener(this);
		radio4.setOnCheckedChangeListener(this);
		radio5.setOnCheckedChangeListener(this);
		radio6.setOnCheckedChangeListener(this);
		radio7.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		 if(isChecked){
             int length = classes.getChildCount();
             RadioGroup group = (RadioGroup) buttonView.getParent();
             for (int i = 0; i < length; i++) {
            	 if(i!=1){
                     RadioGroup rdg = (RadioGroup) classes.getChildAt(i);
                     if(group != rdg){
                             rdg.clearCheck();
                     }}
             }
     }
	}
	
}




