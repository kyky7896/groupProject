package com.recipe.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.recipe.comment.db.CommentBean;
import com.recipe.comment.db.CommentDAO;

public class RecipeCommentUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("[M] RecipeCommentUpdateAction_execute() 호출 됨 ");
		
		/* 댓글 수정 작업을 담당한 Action 객체 */

		// 넘어온 데이터 저장 - 자바빈
		CommentBean cb = new CommentBean();
		cb.setF_rcpc_no(Integer.parseInt(request.getParameter("f_rcpc_no")));
		cb.setF_rcpc_content(request.getParameter("f_rcpc_content"));
		
		// 디비 작업 해줄 객체 생성
		CommentDAO cdao = new CommentDAO();
		int data = cdao.updateComment(cb);
		
		// json 타입으로 변환
		JSONObject obj = new JSONObject();
		obj.put("data",data);
		
		// 데이터 출력 -> 출력객체 사용후 반드시 닫기!!
		response.setContentType("application/x-json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		
	}

}
