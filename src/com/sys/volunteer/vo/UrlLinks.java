package com.sys.volunteer.vo;

public class UrlLinks{
	private String caption;
	private String url;
	private String style;
	private boolean isBlank;

	/**
	 * 标签 (标签的内容是返回上一页)
	 * @param caption
	 */
	public UrlLinks(String caption) {
		this.caption = caption;
		this.isBlank = false;
	}
	
	/**
	 * 标签
	 * @param caption
	 * @param url
	 */
	public UrlLinks(String caption,String url) {
		this.caption = caption;
		this.url = url;
		this.isBlank = false;
	}
	
	/**
	 * 标签
	 * @param caption
	 * @param links
	 */
	public UrlLinks(String caption,String links,boolean isBlank) {
		this.caption = caption;
		this.url = links;
		this.isBlank = isBlank;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public boolean isBlank() {
		return isBlank;
	}
	public void setBlank(boolean isBlank) {
		this.isBlank = isBlank;
	}
}
