package com.xyj.hnu.domain;
/**
 * 外卖属性：饭店名，等级，平均消费，地址，菜系，距离，图片
 * @author xyj
 *
 */
public class takeoutBean {

	public String restaurant_name;
	public String level;
	public String average_consume;
	public String address;
	public String cusines;
	public String picture_address;
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAverage_consume() {
		return average_consume;
	}
	public void setAverage_consume(String average_consume) {
		this.average_consume = average_consume;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCusines() {
		return cusines;
	}
	public void setCusines(String cusines) {
		this.cusines = cusines;
	}
	public String getPicture_address() {
		return picture_address;
	}
	public void setPicture_address(String picture_address) {
		this.picture_address = picture_address;
	}
	
}
