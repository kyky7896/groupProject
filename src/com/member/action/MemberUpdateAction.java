package com.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
System.out.println("M : MemberUpdateAction_exectue() 호출");
		
		// 로그인 세션 제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("f_mem_id");
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달정보를 저장( 수정할 데이터 ) MemberBean 사용
		MemberBean mb = new MemberBean();
		
		mb.setF_mem_postcode(request.getParameter("f_mem_postcode"));
		mb.setF_mem_address(request.getParameter("f_mem_address"));
		mb.setF_mem_detailAddress(request.getParameter("f_mem_detailAddress"));
		mb.setF_mem_extraAddress(request.getParameter("f_mem_extraAddress"));
		mb.setF_mem_email(request.getParameter("f_mem_email"));		
		mb.setF_mem_id(request.getParameter("f_mem_id"));		
		mb.setF_mem_name(request.getParameter("f_mem_name"));		
		mb.setF_mem_pw(request.getParameter("f_mem_pw"));
		mb.setF_mem_phone(request.getParameter("f_mem_phone"));
		mb.setF_mem_nick(request.getParameter("f_mem_nick"));
		
		System.out.println("M : 수정할 정보 " + mb);
		
		// DAO 객체 생성 - 정보 수정메서드 updateMember(mb)
		MemberDAO mdao = new MemberDAO();
		int check = mdao.updateMember(mb);
		
		System.out.println("M : 수정결과 >> " + check);
		
		// 수정처리 결과에 따른 페이지 이동
		// 자바스크립트 사용해서 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(check == 0) {
			out.print("<script>");
			out.print("alert('비밀번호 오류!');");
			out.print("history.back();");			
			out.print("</script>");
			
			out.close();
			return null;
		} else if(check == -1) {
			out.print("<script>");
			out.print("alert('회원 정보 없음!');");
			out.print("history.back();");			
			out.print("</script>");
			
			out.close();
			return null;
		} else { // check == 1 
			out.print("<script>");
			out.print("alert('회원정보 수정완료!');");
			out.print("location.href='./MemberMyPageAction.me';");			
			out.print("</script>");
			
			out.close();
			return null;
		}	
	}
}
