package com.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.action.ActionForward;
import com.recipe.board.db.RecipeDAO;
import com.goodres.db.GoodResDAO;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : MainAction_exectue() 호출");
		
		GoodResDAO grdao = new GoodResDAO();
		RecipeDAO rcpdao = new RecipeDAO();
		
		request.setAttribute("starAvgList", grdao.getGoodResStarAvgList());
		request.setAttribute("rcpViewList", rcpdao.getRcpViewList());
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
