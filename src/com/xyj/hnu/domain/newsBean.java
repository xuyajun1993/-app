package com.xyj.hnu.domain;
/**
 * 每条新闻的属性:图片，标题，内容，跟帖数
 * @author xyj
 *
 */
public class newsBean {

	public String url;
	public String newsTitle;
	public String newsContent;
	public int num;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public newsBean(String url, String newsTitle, String newsContent, int num) {
		this.url = url;
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.num = num;
	}
	public newsBean(){}
}
