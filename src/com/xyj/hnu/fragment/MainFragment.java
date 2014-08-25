package com.xyj.hnu.fragment;

import java.util.Timer;
import java.util.TimerTask;
import com.xyj.hnu.R;
import com.xyj.hnu.tools.Configs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
/**
 *MainView
 * @author xyj
 *
 */
public class MainFragment extends FragmentActivity implements BottomFragment.Callbacks {
	//������־�Ƿ��˳�
	public boolean isExit=false;
	private Timer timer = null;
	private TimerTask timeTask = null;
	public final static String Item = "item";
	private int position;//��ʾ��ҳ
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online);
		timer=new Timer();
		Intent intent=getIntent();
		position=intent.getIntExtra("position", R.id.fragment_bottom_news);
		if(position==R.id.fragment_bottom_service)
			Configs.rb.setChecked(true);
		//��ʼ��Ĭ�ϵ��ýӿ���itemѡ�з���
		onItemSelected(position);
	}

	@Override
	public void onItemSelected(int item) {
		Bundle arguments = new Bundle();
		arguments.putInt(Item, item); //��ѡ�еĵײ�radio��Id�Ž�ȥ
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments); //���ò���ֵ
		//�������item��ID���н�����ת
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.main_detail_FrameLayout, generalFragment).commit();
	}
	
	public void onBackPressed() {
		if (isExit) {
			finish();
		} else {
			isExit = true;
			Toast.makeText(MainFragment.this, "�ٰ�һ���˳�", Toast.LENGTH_SHORT)
					.show();
			timeTask = new TimerTask() {

				@Override
				public void run() {
					isExit = false;
				}
			};
			timer.schedule(timeTask, 2000);
		}
	}
}
