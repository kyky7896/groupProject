package com.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {

	// 추상메서드
	// 다형성을 위해 사용한다 (강제성 부여)
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}
