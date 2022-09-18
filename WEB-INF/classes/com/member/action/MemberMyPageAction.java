package com.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MemberMyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberMyPageAction_execute() 호출");
		
		// 세션확인
		HttpSession session = request.getSession();
		String f_mem_id = (String) session.getAttribute("f_mem_id");
				
		ActionForward forward = new ActionForward();
		
		if(f_mem_id == null) {
			forward.setPath("./MemberLoginSession.me");
			forward.setRedirect(true);
			return forward;
		}		
		
		forward.setPath("./member/MemberMyPage.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
