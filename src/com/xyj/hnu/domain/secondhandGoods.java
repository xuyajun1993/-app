package com.xyj.hnu.domain;
/**
 * ������Ʒ���ԣ�ԭ�ۣ����ۣ���Ʒ������Ƭ��ʱ��
 * @author xyj
 *
 */
public class secondhandGoods {

	public String goodsName;
	public String originPrice;
	public String barginPrice;
	public String url;
	public long time;
	public secondhandGoods(){}
	public secondhandGoods(String goodsName, String originPrice,
			String barginPrice, String url, long time) {
		this.goodsName = goodsName;
		this.originPrice = originPrice;
		this.barginPrice = barginPrice;
		this.url = url;
		this.time = time;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getOriginPrice() {
		return originPrice;
	}
	public void setOriginPrice(String originPrice) {
		this.originPrice = originPrice;
	}
	public String getBarginPrice() {
		return barginPrice;
	}
	public void setBarginPrice(String barginPrice) {
		this.barginPrice = barginPrice;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
