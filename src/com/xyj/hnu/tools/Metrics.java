package com.xyj.hnu.tools;

import android.app.Activity;
import android.util.DisplayMetrics;

//由此类得到设备宽高参数
public class Metrics {
	private static int widthPixels;
	private static int heightPixels;
	
	public static void setSreenInfo(Activity activity){
		DisplayMetrics dm=new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		setWidthPixels(dm.widthPixels);
		setHeightPixels(dm.heightPixels);
	}

	public static int getWidthPixels() {
		return widthPixels;
	}

	public static void setWidthPixels(int widthPixels) {
		Metrics.widthPixels = widthPixels;
	}

	public static int getHeightPixels() {
		return heightPixels;
	}

	public static void setHeightPixels(int heightPixels) {
		Metrics.heightPixels = heightPixels;
	}
	
}
