package com.recipe.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.recipe.board.db.RecipeBean;
import com.recipe.board.db.RecipeDAO;

public class RecipeUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : RecipeUpdate_execute() 호출!");
		request.setCharacterEncoding("UTF-8");
		
		int f_rcp_num=Integer.parseInt(request.getParameter("f_rcp_num"));
		
		
		/*// 로그인 세션 제어
		HttpSession session = request.getSession();		
		String id = (String) session.getAttribute("f_mem_id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}*/
		
		// DAO 객체 생성-> getRecipe로 가져오기
		RecipeDAO rdao=new RecipeDAO();
		//RecipeBean rb= rdao.GetRecipe(f_rcp_num);
		
		request.setAttribute("rb", rdao.GetRecipe(f_rcp_num));
		
		// 페이지 이동 (./recipe/RecipeUpdateForm.jsp)
		ActionForward forward = new ActionForward();
		forward.setPath("./recipe/RecipeUpdateForm.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
