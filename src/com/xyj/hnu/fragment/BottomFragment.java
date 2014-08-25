package com.xyj.hnu.fragment;

import com.xyj.hnu.R;
import com.xyj.hnu.tools.Configs;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * ҳ��ײ�������
 * 
 * 
 * 
 */
public class BottomFragment extends Fragment {
    //Ĭ�ϻص��ӿ�ʵ����Ķ���	
	private Callbacks callbacks = defaultCallbacks; 
	/** Fragment��Activity����������ʱ����� **/
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		//��ǰ���Ƿ�ʵ���˵ײ�����������¼��ص��ӿ�
		if(!(activity instanceof Callbacks)) {
			throw new IllegalStateException("Activity must implements fragment's callbacks !");
		}
		callbacks = (Callbacks) activity;
	}

	/** ΪFragment���ز���ʱ���� **/
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_bottom, container, false);
		RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.rg);
		Configs.rb=(RadioButton) v.findViewById(R.id.fragment_bottom_service);
//		rb.setChecked(true);
		//�󶨼�����
		radioGroup.setOnCheckedChangeListener(changeListener);
		return v;
	}
	
	/**RadioGroup������**/
	private OnCheckedChangeListener changeListener = new OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			callbacks.onItemSelected(checkedId); //���ýӿ��з���
		}
	};

	public interface Callbacks{
		/**�������ص��ӿ�**/
		public void onItemSelected(int item);
	}
	/**Ĭ�ϻص�ʵ����Ķ���**/
	private static Callbacks defaultCallbacks = new Callbacks() {
		@Override
		public void onItemSelected(int item) {
		//ʲô������
		}
	};
	
	/**Fragment��Activity���������ʱ�����**/
    @Override
    public void onDetach() {
        super.onDetach();
       callbacks = defaultCallbacks;
    }
}
