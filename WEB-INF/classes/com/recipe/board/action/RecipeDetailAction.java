package com.recipe.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.board.db.RecipeBean;
import com.recipe.board.db.RecipeDAO;

public class RecipeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 디비에서 상세 레시피 정보 가져와주는 작업 해주는 Action 객체 */
		
		
		System.out.println("[M] RecipeDetailAction_execute() 호출됨");
		
// (수정해야함)★★★★★★★★★★★★★★★★★★★★★★★★★★★★	리스트 페이지랑 연결되면 주석풀것!!!!	
		// 넘어온 데이터(레시피 게시글 번호) 저장
		int f_rcp_num = Integer.parseInt(request.getParameter("f_rcp_num"));
		 System.out.println("f_rcp_num>>"+f_rcp_num);	
		 
		// 디비 작업 해주는 객체 생성 -> 관련 메서드 호출
		RecipeDAO rdao = new RecipeDAO();
		
		// (글 가져오기 전에) 조회수 올리기 
		rdao.updateReadNumber(f_rcp_num);
		
		// 레시피 데이터 가져오기 
		RecipeBean rb = rdao.getRecipeDetail(f_rcp_num); 

		// 메서드 작업결과 반환되는 객체 -> session영역에 저장
		request.getSession().setAttribute("recipeDetail", rb);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./recipe/recipeDetail.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
