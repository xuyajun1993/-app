package com.xyj.hnu.http;

import java.util.ArrayList;
import java.util.List;

import com.xyj.hnu.domain.secondhandGoods;
import com.xyj.hnu.tools.Configs;

/**
 * 得到二手用品的信息
 * @author xyj
 *
 */
public class goodsList {
	
	public void setGoodsList(){
		Configs.goods_list=new ArrayList<secondhandGoods>();
		long time=System.currentTimeMillis()-665524;
		
		secondhandGoods goods=new secondhandGoods("联想i3笔记本电脑", "4500", "2600", "", time);
		Configs.goods_list.add(goods);
	}
}
