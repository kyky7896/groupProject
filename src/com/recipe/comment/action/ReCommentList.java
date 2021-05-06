package com.recipe.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.recipe.comment.db.CommentDAO;

public class ReCommentList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("[M] ReCommentList_execute() 호출됨");
		
		// 넘어온 데이터(f_rcpc_ref) 저장
		int f_rcpc_ref = Integer.parseInt(request.getParameter("f_rcpc_ref"));
		
		// 디비 작업해주는 객체 생성 -> 관련 메서드 호출
		CommentDAO cdao = new CommentDAO();
		JSONArray reCommentList = cdao.getReCommentList(f_rcpc_ref);
		
		System.out.println("대댓글 리스트>> "+reCommentList);
		
		// JSONArray타입의 객체를 jsonObject에 다시 담을 필요 없음 (ajax에서 JSONArray타입 인식하기 때문.)
		
		// 출력 -> 사용하고 나서 반드시 닫기!
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(reCommentList);
		out.flush();
		out.close();

	}

}
