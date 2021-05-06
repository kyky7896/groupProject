package com.recipe.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.board.db.RecipeDAO;

public class RecipeDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : RecipeDeleteAction_execute() 호출!");
		
		//전달된 파라미터 정보 저장(게시글 번호)
		int f_rcp_num=Integer.parseInt(request.getParameter("f_rcp_num"));
		
		//DAO 생성 후 delete구문
		RecipeDAO rdao=new RecipeDAO();
		rdao.DeleteRecipe(f_rcp_num);
		
		ActionForward forward=new ActionForward();
		forward.setPath("./RecipeList.re");
		forward.setRedirect(true);
		return forward;
	}

}
