package com.xyj.hnu.app;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

/**
 * 全局应用程序类：控制信息第一次进去自动刷新
 * 
 * @author xyj
 * 
 */
public class AppContext extends Application {
	public static boolean noti_isrefresh = false;
	public static boolean lec_isrefresh = false;
	public static boolean acti_isrefresh = false;
	public static boolean sug_isrefresh = false;
	public static boolean my_isrefresh = false;

	@Override
	public void onCreate() {
		super.onCreate();
		// 注册异常崩溃处理器
		// Thread.setDefaultUncaughtExceptionHandler();
	}

	/**
	 * 检查网络情况
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * 返回安装包信息
	 * 
	 * @return
	 */
	public PackageInfo getPackageInfo() {
		PackageInfo info = null;
		try {
			info = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	/**
	 * 得到版本号
	 */
	public int getAppVersion() {
		PackageInfo info = getPackageInfo();
		return info.versionCode;
	}

	/**
	 * 得到缓存地址
	 */
	public File getDiskCacheDir(String uniqueName) {
		String cachePath;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)
				|| !Environment.isExternalStorageRemovable()) {
			cachePath = getExternalCacheDir().getAbsolutePath();
		} else {
			cachePath = getCacheDir().getAbsolutePath();
		}
		return new File(cachePath + File.separator + uniqueName);
	}
}
