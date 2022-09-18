package com.member.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.db.MemberBean;
import com.member.db.MemberDAO;
import com.review.db.ReviewDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : MemberDeleteAction_exectue() 호출");
		// 세션값 제어(로그인 정보)	
		HttpSession session = request.getSession();		
		String id = (String) session.getAttribute("f_mem_id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달될 비밀번호 저장
		MemberBean mb = new MemberBean();
		mb.setF_mem_id(id);
		mb.setF_mem_pw(request.getParameter("f_mem_pw"));
		
		System.out.println("M : 삭제회원 정보 " + mb);
		
		ReviewDAO rdao = new ReviewDAO();
		List<Integer> GrNum = rdao.searchGrNum(id);
		
		// DAO 객체 생성 - deleteMember(mb)
		MemberDAO mdao = new MemberDAO();
		int check = mdao.deleteMember(mb);
		
		
		// 데이터의 처리 결과에 따라서 페이지 이동
		// update 동작과 동일하게 처리
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
			// 세션초기화
			session.invalidate();
			
			out.print("<script>");
			out.print("alert('회원 탈퇴 완료!');");
			out.print("location.href='./Main.me';");			
			out.print("</script>");
			
			// 맛집 총점 계산
			for(int i=0; i<GrNum.size(); i++){
			  rdao.ratingAvg(GrNum.get(i));
			}
			
			out.close();
			return null;
		}
	}
}
