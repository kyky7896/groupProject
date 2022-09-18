package com.recipe.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.board.db.RecipeDAO;

public class MyRecipeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MyRecipeListAcion_execute() 실행 ");
		request.setCharacterEncoding("UTF-8");
		//로그인 세션 처리
		HttpSession session = request.getSession();		
		String id = (String) session.getAttribute("f_mem_id");
		
		//DAO 객체 생성
		RecipeDAO rdao=new RecipeDAO();
		request.setAttribute("MyRecipeList", rdao.MyRecipeList(id));
		System.out.println("M : aaaa"+rdao.MyRecipeList(id));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./recipe/MyRecipeList.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
