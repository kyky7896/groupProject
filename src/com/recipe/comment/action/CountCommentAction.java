package com.recipe.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.recipe.comment.db.CommentDAO;

public class CountCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		System.out.println("[M] CountComment_execute() 호출됨!!");
		
		/* 댓글 갯수 디비에서 카운트한 결과값을 가져와서 getJSON으로 넘기는 작업 하는 Action객체 */
	
		// 넘어온 데이터 저장 (게시피 게시글 번호)
		int f_rcpc_num = Integer.parseInt(request.getParameter("f_rcpc_num"));
		
		// 디비 관련 작업해주는 객체 생성 -> 관련 메서드 호출
		CommentDAO cdao = new CommentDAO();
		int data = cdao.countComment(f_rcpc_num);
		
		// JSONObject 타입으로 변경
		JSONObject obj = new JSONObject();
		obj.put("data",data);
		
		// 출력 
		response.setContentType("application/x-json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
	}

}
