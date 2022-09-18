package com.admin.goods.action;

public class ActionForward {
	
	
	private String path; // 이동할 페이지 주소
	private boolean isRedirect; // 이동방식
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
	

}
