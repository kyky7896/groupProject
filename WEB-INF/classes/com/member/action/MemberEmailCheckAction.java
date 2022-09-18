package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.gmail.SHA256;
import com.member.db.MemberDAO;

public class MemberEmailCheckAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M :  MemberEmailCheckAction_execute() 호출");
		
		String code = request.getParameter("code");
		String email = request.getParameter("email");
		String userid = request.getParameter("userid");
		System.out.println(email);
		System.out.println(userid);
		boolean rightCode = false;
		
		PrintWriter script = response.getWriter();
		
		MemberDAO mdao = new MemberDAO();
		int eCheck = mdao.emailCheck(email);
		if (eCheck == 1) {
			rightCode = SHA256.getEncrypt(email, "cos").equals(code) ? true : false; 
		} else {
			script.println("<script>");
			script.println("alert('올바른 인증메일이 아닙니다.')");
			script.println("location.href='./Main.me'");
			script.println("</script>");
		}
		
		script = response.getWriter();
		
		if(rightCode == true && userid == null){
			System.out.println("MemberEmailCheckAction : 아이디 찾기");
			String findId = mdao.getIdSearch(email);
			request.setAttribute("findId", findId);
			ActionForward forward = new ActionForward();
			forward.setPath("./member/findid.jsp");
			forward.setRedirect(false);
			
			return forward;

		} else if(rightCode == true && !userid.equals(null)) {
			System.out.println("MemberEmailCheckAction : 비밀번호 찾기");
			String tempKey = mdao.getPwSearch(userid);
			System.out.println(tempKey);
			request.setAttribute("tempKey", tempKey);
			ActionForward forward = new ActionForward();
			forward.setPath("./member/findpw.jsp");
			forward.setRedirect(false);
			
			return forward;
		} else{
			script.println("<script>");
			script.println("alert('이메일 인증을 실패하였습니다.')");
			script.println("location.href='history.back()'");
			script.println("</script>");
		}
		
		return null;
	}

}
