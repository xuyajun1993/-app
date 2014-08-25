package com.xyj.hnu.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.xyj.hnu.R;
import com.xyj.hnu.fragment.MainFragment;

public class repairPostSuccess extends Activity {
	
	private ImageButton back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postrepairsuccess);
		back=(ImageButton) findViewById(R.id.ib_success_back);
		back.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent=new Intent(repairPostSuccess.this,MainFragment.class);
				intent.putExtra("position",R.id.fragment_bottom_service);
				startActivity(intent);
//				finish();
			}
		});
	}
}
