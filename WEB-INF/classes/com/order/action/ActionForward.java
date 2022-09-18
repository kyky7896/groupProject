package com.order.action;

public class ActionForward {
	
	// 페이지 이동 정보를 저장하는 객체
	// 페이지 이동시 항상 정보를 저장 (주소, 이동방식)
	
	private String path;   // 주소
	private boolean isRedirect;  // 이동방식 - 2가지 
	// true -> sendRedirect() 방식으로 이동 (주소 + 화면 변경)
	// false -> forward() 방식으로 이동  (화면만 변경)
	
	
	// alt shift s + r : set/get 메서드
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {   // getisRedirect 랑 같은 의미
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	

}
