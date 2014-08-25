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
	//用来标志是否退出
	public boolean isExit=false;
	private Timer timer = null;
	private TimerTask timeTask = null;
	public final static String Item = "item";
	private int position;//表示到页
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.online);
		timer=new Timer();
		Intent intent=getIntent();
		position=intent.getIntExtra("position", R.id.fragment_bottom_news);
		if(position==R.id.fragment_bottom_service)
			Configs.rb.setChecked(true);
		//初始化默认调用接口中item选中方法
		onItemSelected(position);
	}

	@Override
	public void onItemSelected(int item) {
		Bundle arguments = new Bundle();
		arguments.putInt(Item, item); //将选中的底部radio的Id放进去
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments); //设置参数值
		//这里根据item的ID进行界面跳转
		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.main_detail_FrameLayout, generalFragment).commit();
	}
	
	public void onBackPressed() {
		if (isExit) {
			finish();
		} else {
			isExit = true;
			Toast.makeText(MainFragment.this, "再按一次退出", Toast.LENGTH_SHORT)
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
