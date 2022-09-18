package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberListAction_exectue() 호출");
		
		// 세션확인
		HttpSession session = request.getSession();
		String f_mem_id = (String) session.getAttribute("f_mem_id");
				
		ActionForward forward = new ActionForward();
		
		if(f_mem_id == null || !f_mem_id.equals("admin")) {
			forward.setPath("./MemberLoginSession.me");
			forward.setRedirect(true);
			return forward;
		}
		
		MemberDAO dao = new MemberDAO();
		List<MemberBean> memberList = dao.getMemberList();
		
		request.setAttribute("memberList", memberList);
		
		forward.setPath("./member/MemberList.jsp");
		forward.setRedirect(false);		
		
		return forward;
	}

}
