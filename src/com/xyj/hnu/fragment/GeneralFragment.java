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
 * ����࣬���󹫹�����
 * @author
 *
 */
public class GeneralFragment extends Fragment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int item; //�������ֵײ��˵���
	protected final static String key = "Bundle";   //��תֵ����key������
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getArguments() != null) {  //���ݽ������ഫ����arguments�ж�item����һ��
			if(getArguments().containsKey(MainFragment.Item)) {
				item = getArguments().getInt(MainFragment.Item);
			}
		}
	}
	
	/**ΪFragment���ز���ʱ���� **/
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
			//����mainView�м������
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, fragment).commit();
		}
		//�����ɵ�view����
		return view;
	}
	
	
	/**ҳ����תֵ����**/
	protected void setBundle(Object... objects) {
		Bundle arguments = new Bundle();
		arguments.putSerializable(key, objects);
		GeneralFragment generalFragment = new GeneralFragment();
		generalFragment.setArguments(arguments);
	}
	
	/**��ȡ�����ݵ�ֵ**/
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
	
	/**�޲�ҳ����ת**/
	protected void toIntent(GeneralFragment generalFragment) {
		if(generalFragment != null) {
			getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.general_fragment, generalFragment).commit();
		}
	}

}
