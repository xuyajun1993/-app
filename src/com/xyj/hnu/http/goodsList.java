package com.xyj.hnu.http;

import java.util.ArrayList;
import java.util.List;

import com.xyj.hnu.domain.secondhandGoods;
import com.xyj.hnu.tools.Configs;

/**
 * �õ�������Ʒ����Ϣ
 * @author xyj
 *
 */
public class goodsList {
	
	public void setGoodsList(){
		Configs.goods_list=new ArrayList<secondhandGoods>();
		long time=System.currentTimeMillis()-665524;
		
		secondhandGoods goods=new secondhandGoods("����i3�ʼǱ�����", "4500", "2600", "", time);
		Configs.goods_list.add(goods);
	}
}
