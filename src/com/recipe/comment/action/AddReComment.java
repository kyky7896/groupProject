package com.recipe.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.comment.db.CommentBean;
import com.recipe.comment.db.CommentDAO;

public class AddReComment implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("[M] AddReComment_execute() 호출됨 ");
		
		// 넘어온 데이터 저장 -> 빈에다가
		CommentBean cb = new CommentBean();
		cb.setF_rcpc_rcp_num(Integer.parseInt(request.getParameter("f_rcpc_rcp_num"))); // 글번호
		cb.setF_rcpc_mem_id(request.getParameter("f_rcpc_mem_id")); // 글쓴이(id)
		cb.setF_rcpc_mem_nick(request.getParameter("f_rcpc_mem_nick"));//닉네임
		cb.setF_rcpc_content(request.getParameter("f_rcpc_content")); // 내용
		cb.setF_rcpc_ref(Integer.parseInt(request.getParameter("f_rcpc_ref"))); //글번호(그룹번호)
		cb.setF_rcpc_lev(Integer.parseInt(request.getParameter("f_rcpc_lev")));// 대댓글 깊이
		cb.setF_rcpc_seq(Integer.parseInt(request.getParameter("f_rcpc_seq"))); // 대댓글 내 순서
		cb.setF_rcpc_ip(request.getRemoteAddr());//ip
		
		System.out.println("넘어온 데이터>>"+cb);
		
		// 디비 작업해주는 객체 생성 -> 관련 메서드 호출
		CommentDAO cdao = new CommentDAO();
		cdao.insertReComment(cb);
	}

}
