package com.xyj.hnu.news;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.xyj.hnu.R;

public class newsContent extends Activity{

	private ImageButton news_back;
	private ImageButton news_review;
	private TextView news_reviewcount;
	private EditText et_writereview;
	private ImageButton review_share;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.newscontent);
		news_back=(ImageButton) findViewById(R.id.news_back);
		news_review=(ImageButton) findViewById(R.id.news_review);
		news_reviewcount=(TextView) findViewById(R.id.news_reviewcount);
		et_writereview=(EditText) findViewById(R.id.et_writereview);
		review_share=(ImageButton) findViewById(R.id.review_share);
		
		//ͨ����ͼ�õ������ŵ�type��id
		
		//�����첽�����ȡ������
		
		
		//����
		news_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                 finish();				
			}
		});
		
		//���������
		news_review.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
		
		//�ύ����
		review_share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//�õ�����
				String review=et_writereview.getText().toString().trim();
				if(TextUtils.isEmpty(review)){
					Toast.makeText(getApplicationContext(), "�ף���д�������ύŶ", 0).show();
				}else{
					
				}
			}
		});
	}
}
