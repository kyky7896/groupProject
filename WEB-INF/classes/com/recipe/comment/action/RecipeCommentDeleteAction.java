package com.recipe.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.recipe.comment.db.CommentBean;
import com.recipe.comment.db.CommentDAO;

public class RecipeCommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 댓글 삭제 작업을 담당한 Action 객체 */
		
		System.out.println("[M] RecipeCommentDeleteAction_execute() 호출됨");
		
		// 넘어온 데이터(삭제할 댓글 번호) 받아서 빈에 저장
			CommentBean cb = new CommentBean();
			cb.setF_rcpc_no(Integer.parseInt(request.getParameter("f_rcpc_no")));
		
		// 댓글 디비 작업해주는 객체 생성 -> 관련 메서드 호출(deleteComment)
			CommentDAO cdao = new CommentDAO();
			cdao.deleteComment(cb);
			
			/*System.out.println("data : "+data);*/
		
		/*// DAO 작업 결과 반환되는 데이터 -> JSON타입으로 변환
			JSONObject obj = new JSONObject();
			obj.put("data", data);
			
		// 변환한 데이터 출력 -> 출력 객체 사용후 반드시 닫기!
			response.setContentType("application/x-json; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();*/

	}

}
