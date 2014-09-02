package com.xyj.hnu.service;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xyj.hnu.R;
import com.xyj.hnu.tools.Metrics;

@SuppressLint("NewApi")
public class emptyroomService extends Activity implements OnClickListener {
	private TextView textview1,textview2,textview3,textview4,textview5,
	textview6,textview7,tv_write,textview8,textview9,textview10,textview11,textview12,
	textview13;
	Drawable allday0, allday1,class120, class121,class340, class341,class560, class561,
	class780, class781,class9100, class9101,class11120, class11121;
	private LinearLayout linelayout1,linelayout2,linelayout3,linelayout4,
	linelayout5,linelayout6,linelayout7;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emptyroomquery);
		int width = Metrics.getWidthPixels();
		textview1 = (TextView) findViewById(R.id.textView1);
		textview2 = (TextView) findViewById(R.id.textView2);
		textview3 = (TextView) findViewById(R.id.textView3);
		textview4 = (TextView) findViewById(R.id.textView4);
		textview5 = (TextView) findViewById(R.id.textView5);
		textview6 = (TextView) findViewById(R.id.textView6);
		textview7 = (TextView) findViewById(R.id.textView7);
		tv_write = (TextView) findViewById(R.id.tv_write);
		textview8 = (TextView) findViewById(R.id.textView8);
		textview9 = (TextView) findViewById(R.id.textView9);
		textview10 = (TextView) findViewById(R.id.textView10);
		textview11 = (TextView) findViewById(R.id.textView11);
		textview12 = (TextView) findViewById(R.id.textView12);
		textview13 = (TextView) findViewById(R.id.textView13);
		linelayout1 = (LinearLayout) findViewById(R.id.linelayout1);
		linelayout2 = (LinearLayout) findViewById(R.id.linelayout2);
		linelayout3 = (LinearLayout) findViewById(R.id.linelayout3);
		linelayout4 = (LinearLayout) findViewById(R.id.linelayout4);
		linelayout5 = (LinearLayout) findViewById(R.id.linelayout5);
		linelayout6 = (LinearLayout) findViewById(R.id.linelayout6);
		linelayout7 = (LinearLayout) findViewById(R.id.linelayout7);
		textview1.setWidth(width/4);
		textview2.setWidth(width/4);
		textview3.setWidth(width/4);
		textview4.setWidth(width/4);
		textview5.setWidth(width/4);
		textview6.setWidth(width/4);
		textview7.setWidth(width/4);
		tv_write.setWidth(width/4);
		textview8.setWidth(width/6);
		textview9.setWidth(width/6);
		textview10.setWidth(width/6);
		textview11.setWidth(width/6);
		textview12.setWidth(width/6);
		textview13.setWidth(width/6);
		textview1.setOnClickListener(this);
		textview2.setOnClickListener(this);
		textview3.setOnClickListener(this);
		textview4.setOnClickListener(this);
		textview5.setOnClickListener(this);
		textview6.setOnClickListener(this);
		textview7.setOnClickListener(this);
		textview8.setOnClickListener(new buildingListener());
		textview9.setOnClickListener(new buildingListener());
		textview10.setOnClickListener(new buildingListener());
		textview11.setOnClickListener(new buildingListener());
		textview12.setOnClickListener(new buildingListener());
		textview13.setOnClickListener(new buildingListener());
		ready();
	}
			
	public void ready(){
		allday0 = getResources().getDrawable(R.drawable.allday0);
		allday1 = getResources().getDrawable(R.drawable.allday1);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		allday0.setBounds(0, 0, allday0.getMinimumWidth(), allday0.getMinimumHeight());
		allday1.setBounds(0, 0, allday1.getMinimumWidth(), allday1.getMinimumHeight());
		
		class120 = getResources().getDrawable(R.drawable.class120);
		class121 = getResources().getDrawable(R.drawable.class121);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		class120.setBounds(0, 0, class120.getMinimumWidth(), class120.getMinimumHeight());
		class121.setBounds(0, 0, class121.getMinimumWidth(), class121.getMinimumHeight());
		
		class340 = getResources().getDrawable(R.drawable.class340);
		class341 = getResources().getDrawable(R.drawable.class341);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		class340.setBounds(0, 0, class340.getMinimumWidth(), class340.getMinimumHeight());
		class341.setBounds(0, 0, class341.getMinimumWidth(), class341.getMinimumHeight());
		
		class560 = getResources().getDrawable(R.drawable.class560);
		class561 = getResources().getDrawable(R.drawable.class561);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		class560.setBounds(0, 0, class560.getMinimumWidth(), class560.getMinimumHeight());
		class561.setBounds(0, 0, class561.getMinimumWidth(), class561.getMinimumHeight());
		
		class780 = getResources().getDrawable(R.drawable.class780);
		class781 = getResources().getDrawable(R.drawable.class781);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		class780.setBounds(0, 0, class780.getMinimumWidth(), class780.getMinimumHeight());
		class781.setBounds(0, 0, class781.getMinimumWidth(), class781.getMinimumHeight());
		
		class9100 = getResources().getDrawable(R.drawable.class9100);
		class9101 = getResources().getDrawable(R.drawable.class9101);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		class9100.setBounds(0, 0, class9100.getMinimumWidth(), class9100.getMinimumHeight());
		class9101.setBounds(0, 0, class9101.getMinimumWidth(), class9101.getMinimumHeight());
		
		class11120 = getResources().getDrawable(R.drawable.class11120);
		class11121 = getResources().getDrawable(R.drawable.class11121);
		// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
		class11120.setBounds(0, 0, class11120.getMinimumWidth(), class11120.getMinimumHeight());
		class11121.setBounds(0, 0, class11121.getMinimumWidth(), class11121.getMinimumHeight());
		textview1.setAlpha((float) 90);
		textview2.setAlpha((float) 80);
		textview3.setAlpha((float) 80);
		
		textview3.setAlpha((float) 80);
		textview4.setAlpha((float) 80);
		textview5.setAlpha((float) 80);
		textview6.setAlpha((float) 80);
		textview7.setAlpha((float) 80);
	}
		
	
	@Override
	public void onClick(View v) {
		processclass(v);
		
	}

	
	public void processclass(View v){
		if(R.id.textView1==v.getId()){
			textview1.setAlpha(90);
			textview1.setCompoundDrawables(null, allday1, null, null);
			textview1.setTextColor(Color.parseColor("#990000"));
			linelayout1.setBackgroundColor(Color.parseColor("#999999"));
		}else {
			if(textview1.getAlpha()==90){
		textview1.setAlpha(80);
		textview1.setCompoundDrawables(null, allday0, null, null);
		textview1.setTextColor(Color.parseColor("#999999"));
		linelayout1.setBackgroundColor(Color.parseColor("#f5f4f4"));}
		}
		
		if(R.id.textView2==v.getId()){
			textview2.setAlpha(90);
			textview2.setCompoundDrawables(null, class121, null, null);
			textview2.setTextColor(Color.parseColor("#990000"));
			linelayout2.setBackgroundColor(Color.parseColor("#999999"));
		}else {if(textview2.getAlpha()==90){
			textview2.setAlpha(80);
			textview2.setCompoundDrawables(null, class120, null, null);
		textview2.setTextColor(Color.parseColor("#999999"));
		linelayout2.setBackgroundColor(Color.parseColor("#f5f4f4"));}
		}
		
		if(R.id.textView3==v.getId()){
			textview3.setAlpha(90);
			textview3.setCompoundDrawables(null, class341, null, null);
			textview3.setTextColor(Color.parseColor("#990000"));
			linelayout3.setBackgroundColor(Color.parseColor("#999999"));
		}else{ if(textview3.getAlpha()==90){
			textview3.setAlpha(80);
			textview3.setCompoundDrawables(null, class340, null, null);
		textview3.setTextColor(Color.parseColor("#999999"));
		linelayout3.setBackgroundColor(Color.parseColor("#f5f4f4"));}
		}
		
		if(R.id.textView4==v.getId()){
			textview4.setAlpha(90);
			textview4.setCompoundDrawables(null, class561, null, null);
			textview4.setTextColor(Color.parseColor("#990000"));
			linelayout4.setBackgroundColor(Color.parseColor("#999999"));
		}else {if(textview4.getAlpha()==90){
			textview4.setAlpha(80);
			textview4.setCompoundDrawables(null, class560, null, null);
		textview4.setTextColor(Color.parseColor("#999999"));
		linelayout4.setBackgroundColor(Color.parseColor("#f5f4f4"));}
		}
		
		if(R.id.textView5==v.getId()){
			textview5.setAlpha(90);
			textview5.setCompoundDrawables(null, class781, null, null);
			textview5.setTextColor(Color.parseColor("#990000"));
			linelayout5.setBackgroundColor(Color.parseColor("#999999"));
		}else{ if(textview5.getAlpha()==90){
			textview5.setAlpha(80);
			textview5.setCompoundDrawables(null, class780, null, null);
		textview5.setTextColor(Color.parseColor("#999999"));
		linelayout5.setBackgroundColor(Color.parseColor("#f5f4f4"));}
		}
		
		if(R.id.textView6==v.getId()){
			textview6.setAlpha(90);
			textview6.setCompoundDrawables(null, class9101, null, null);
			textview6.setTextColor(Color.parseColor("#990000"));
			linelayout6.setBackgroundColor(Color.parseColor("#999999"));
		}else{ if(textview6.getAlpha()==90){
			textview6.setAlpha(80);
			textview6.setCompoundDrawables(null, class9100, null, null);
		textview6.setTextColor(Color.parseColor("#999999"));
		linelayout6.setBackgroundColor(Color.parseColor("#f5f4f4"));}
		}
		
		if(R.id.textView7==v.getId()){
			textview7.setAlpha(90);
			textview7.setCompoundDrawables(null, class11121, null, null);
			textview7.setTextColor(Color.parseColor("#990000"));
			linelayout7.setBackgroundColor(Color.parseColor("#999999"));
		}else{ if(textview7.getAlpha()==90){
			textview7.setAlpha(80);
			textview7.setCompoundDrawables(null, class11120, null, null);
		textview7.setTextColor(Color.parseColor("#999999"));}
		linelayout7.setBackgroundColor(Color.parseColor("#f5f4f4"));
		}
		
	}
	/**
	 * 实现对地点的监听
	 * @author Deller
	 *
	 */
	public class buildingListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			if(R.id.textView9==v.getId()){
				textview9.setBackgroundResource(R.drawable.cursor);
			}else{
				textview9.setBackgroundColor(Color.parseColor("#666666"));
			}
			
			if(R.id.textView10==v.getId()){
				textview10.setBackgroundResource(R.drawable.cursor);
			}else{
				textview10.setBackgroundColor(Color.parseColor("#666666"));
			}
			
			if(R.id.textView11==v.getId()){
				textview11.setBackgroundResource(R.drawable.cursor);
			}else{
				textview11.setBackgroundColor(Color.parseColor("#666666"));
			}
			
			if(R.id.textView12==v.getId()){
				textview12.setBackgroundResource(R.drawable.cursor);
			}else{
				textview12.setBackgroundColor(Color.parseColor("#666666"));
			}
			
			if(R.id.textView13==v.getId()){
				textview13.setBackgroundResource(R.drawable.cursor);
			}else{
				textview13.setBackgroundColor(Color.parseColor("#666666"));
			}
			
			if(R.id.textView8==v.getId()){
				textview8.setBackgroundResource(R.drawable.cursor);
			}else{
				textview8.setBackgroundColor(Color.parseColor("#666666"));
			}
			
			
		}

	}


}



