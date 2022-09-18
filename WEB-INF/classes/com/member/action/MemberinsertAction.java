package com.member.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;

public class MemberinsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		System.out.println("D : MemberJoinAction_execute() 호출");
		request.setCharacterEncoding("UTF-8");
		
		MemberBean mb = new MemberBean();
		mb.setF_mem_id(request.getParameter("f_mem_id"));
		mb.setF_mem_pw(request.getParameter("f_mem_pw"));
		mb.setF_mem_name(request.getParameter("f_mem_name"));
		mb.setF_mem_phone(request.getParameter("f_mem_phone"));
		mb.setF_mem_postcode(request.getParameter("f_mem_postcode"));
		mb.setF_mem_address(request.getParameter("f_mem_address"));
		mb.setF_mem_detailAddress(request.getParameter("f_mem_detailAddress"));
		mb.setF_mem_extraAddress(request.getParameter("f_mem_extraAddress"));
		mb.setF_mem_email(request.getParameter("f_mem_email"));
		mb.setF_mem_nick(request.getParameter("f_mem_nick"));
		mb.setF_mem_date(new Date(System.currentTimeMillis()));
		mb.setF_mem_ip(request.getParameter("f_mem_ip"));
		
		System.out.println("M : 전달된 회원정보 "+mb);
		
		MemberDAO mdao = new MemberDAO();
		
		mdao.insertMember(mb);
		
		System.out.println("M : 회원가입 성공!");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
	
		out.print("<script>");
		out.print(" alert('회원가입이 완료되었습니다.');");
		out.print(" location.href='./Main.me' ");
		out.print("</script>");
		
		out.close();
		
		return null;
		
		//페이지 이동
/*		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		
		return forward;*/
	}

}
