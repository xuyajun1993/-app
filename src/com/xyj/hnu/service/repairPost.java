package com.xyj.hnu.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xyj.hnu.R;
import com.xyj.hnu.fragment.MainFragment;

public class repairPost extends Activity implements OnClickListener {

	private ImageButton ib_woods;
	private ImageButton ib_elec;
	private ImageButton ib_water;
	private ImageButton ib_net;
	private EditText et_place;
	private EditText et_describe;
	private EditText et_phone;
	private Button submit;
	private ImageButton back;
	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repairpost);
		ib_woods = (ImageButton) findViewById(R.id.ib_woods);
		ib_elec = (ImageButton) findViewById(R.id.ib_elec);
		ib_water = (ImageButton) findViewById(R.id.ib_water);
		ib_net = (ImageButton) findViewById(R.id.ib_net);
		et_describe=(EditText) findViewById(R.id.et_describe);
		et_place=(EditText) findViewById(R.id.et_place);
		et_phone=(EditText) findViewById(R.id.et_phone);
		submit=(Button) findViewById(R.id.submit);
		back=(ImageButton) findViewById(R.id.ib_back);
		ib_elec.setOnClickListener(this);
		ib_woods.setOnClickListener(this);
		ib_water.setOnClickListener(this);
		ib_net.setOnClickListener(this);

		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String place=et_place.getText().toString().trim();
				String describe=et_describe.getText().toString().trim();
				String phone=et_phone.getText().toString().trim();
				if(TextUtils.isEmpty(type)||TextUtils.isEmpty(place)||TextUtils.isEmpty(describe)){
					Toast.makeText(getApplicationContext(), "请填写完整信息", 0).show();
				}else{
					//将类别，地点，详情发送到服务器
					new Thread(){
						public void run() {
							try {
								sleep(2000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//跳转到成功页面
							Intent intent=new Intent(repairPost.this,repairPostSuccess.class);
							startActivity(intent);
						};
					}.start();
				}
			}
		});
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_woods:
            ib_woods.setImageResource(R.drawable.woodspressed);
            ib_elec.setImageResource(R.drawable.electricnormal);
            ib_water.setImageResource(R.drawable.waternormal);
            ib_net.setImageResource(R.drawable.netnormal);
            type="woods";
			break;

		case R.id.ib_elec:
			ib_woods.setImageResource(R.drawable.woodsnormal);
            ib_elec.setImageResource(R.drawable.electricpressed);
            ib_water.setImageResource(R.drawable.waternormal);
            ib_net.setImageResource(R.drawable.netnormal);
            type="elec";
			break;

		case R.id.ib_water:
			ib_woods.setImageResource(R.drawable.woodsnormal);
            ib_elec.setImageResource(R.drawable.electricnormal);
            ib_water.setImageResource(R.drawable.waterpressed);
            ib_net.setImageResource(R.drawable.netnormal);
            type="water";
			break;

		case R.id.ib_net:
			ib_woods.setImageResource(R.drawable.woodsnormal);
            ib_elec.setImageResource(R.drawable.electricnormal);
            ib_water.setImageResource(R.drawable.waternormal);
            ib_net.setImageResource(R.drawable.netpressed);
            type="net";
			break;
		}
	}
}
