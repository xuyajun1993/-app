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
 * ȫ��Ӧ�ó����ࣺ������Ϣ��һ�ν�ȥ�Զ�ˢ��
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
		// ע���쳣����������
		// Thread.setDefaultUncaughtExceptionHandler();
	}

	/**
	 * ����������
	 */
	public boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();
	}

	/**
	 * ���ذ�װ����Ϣ
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
	 * �õ��汾��
	 */
	public int getAppVersion() {
		PackageInfo info = getPackageInfo();
		return info.versionCode;
	}

	/**
	 * �õ������ַ
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
