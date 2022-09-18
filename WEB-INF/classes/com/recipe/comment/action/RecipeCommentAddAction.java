package com.recipe.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.recipe.comment.db.CommentBean;
import com.recipe.comment.db.CommentDAO;

public class RecipeCommentAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("[M] RecipeCommentAddAction_execute() 호출됨");
		
		/* 댓글 추가 작업을 담당한 Action 객체 */

		// 한글설정
		request.setCharacterEncoding("UTF-8");
		
		// 넘어온 데이터 빈에 저장(글번호,작성자 아이디, 작성내용)
		CommentBean cb = new CommentBean();
		cb.setF_rcpc_rcp_num(Integer.parseInt(request.getParameter("f_rcpc_rcp_num")));
		// 등록 날짜는 쿼리문에서 입력
		cb.setF_rcpc_mem_id(request.getParameter("f_rcpc_mem_id"));
		cb.setF_rcpc_mem_nick(request.getParameter("f_rcpc_mem_nick"));
		cb.setF_rcpc_content(request.getParameter("f_rcpc_content"));
		cb.setF_rcpc_ip(request.getRemoteAddr()); // 입력한 사람의 Ip정보 
		
		System.out.println("넘어온 데이터:"+cb);
		
		// 디비 작업해주는 객체 생성
		CommentDAO cdao = new CommentDAO();
		int data = cdao.InsertComment(cb); // 댓글 넣고
		
		System.out.println(data);
		
		// 자바코드 데이터 -> JSONObject데이터 타입으로 변경하여 ajax로 데이터(DAO작업결과 반환값) 넘기기 
		JSONObject obj = new JSONObject();
		obj.put("data",data); 
		
		// ajax쪽으로 데이터 출력 -> 출력객체 사용 후 반드시 닫기!
		response.setContentType("application/x-json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.flush();
		out.close();
		
		
		}

	}


