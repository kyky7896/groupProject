package com.recipe.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.recipe.comment.db.CommentDAO;

public class RecipeCommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 댓글 리스트 디비에서 가져와 ->view로 보내는 작업 담당한 Action 객체 */
		
		
		System.out.println("[M] GoodsCommentListAction_execute() 호출됨");
		
		// 넘어오는 데이터 저장(게시글 번호)
		int f_rcpc_rcp_num = Integer.parseInt(request.getParameter("f_rcpc_rcp_num"));
		System.out.println("f_rcpc_rcp_num:"+f_rcpc_rcp_num);
		
		// 디비 작업해주는 객체 생성
		CommentDAO cdao = new CommentDAO();
		
		//DAO 작업결과 리턴되는 반환값 저장  
		JSONArray commentList = cdao.getCommentJ(f_rcpc_rcp_num);
		
		System.out.println("commentList: "+commentList);
		
		// JSONArray타입의 객체를 jsonObject에 다시 담을 필요 없음 (ajax에서 JSONArray타입 인식하기 때문.)
		
		// 출력 -> 사용하고 나서 반드시 닫기!
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(commentList);
		out.flush();
		out.close();

	}

}
