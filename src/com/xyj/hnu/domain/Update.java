package com.xyj.hnu.domain;

/**
 * ���µ�ҵ��ģ�ͣ��汾�����汾��
 * 
 * @author xyj
 * 
 */
public class Update {

	private int versionCode;
	private String versionName;

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Update() {
	}

	public Update(int versionCode, String versionName) {
		this.versionCode = versionCode;
		this.versionName = versionName;
	}

}
