package com.xyj.hnu.fragment;

import java.io.Serializable;

import com.xyj.hnu.R;
import com.xyj.hnu.view.news;
import com.xyj.hnu.view.personal;
import com.xyj.hnu.view.service;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * 框架类，抽象公共方法
 * @author
 *
 */
public class GeneralFragment extends Fragment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int item; //用于区分底部菜单项
	protected final static String key = "Bundle";   //跳转值传递key的名称
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getArguments() != null) {  //根据接收子类传来的arguments判断item的哪一项
			if(getArguments().containsKey(MainFragment.Item)) {
				item = getArguments().getInt(MainFragment.Item);
			}
		}
	}
	
	/**为Fragment加载布局时调用 **/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_general, container, false);
		GeneralFragment fragment = null;
		switch(item) {
		case R.id.fragment_bottom_news:  
			fragment = new news();
			break;
		case R.id.fragment_bottom_service:
			fragment = new service();  
			 break;
		case R.id.fragment_bottom_personal:
			fragment = new personal();   
			break;
		default:
			break;
		}
		if(fragment != null) {
			//更换mainView中间的内容
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, fragment).commit();
		}
		//将生成的view返回
		return view;
	}
	
	
	/**页面跳转值传递**/
	protected void setBundle(Object... objects) {
		Bundle arguments = new Bundle();
		arguments.putSerializable(key, objects);
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments);
	}
	
	/**获取所传递的值**/
	protected Object[] getBundle() {
		if(getArguments() != null) {
			System.out.println("getBundle");
			if(getArguments().containsKey(key)) {
				Object[] object = (Object[]) getArguments().getSerializable(key);
				return object;
			}
		}
		return null;
	}
	
	/**无参页面跳转**/
	protected void toIntent(GeneralFragment generalFragment) {
		if(generalFragment != null) {
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, generalFragment).commit();
		}
	}

}
