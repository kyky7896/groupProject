package com.goodres.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodres.db.GoodResDAO;

public class GoodResListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String gu = request.getParameter("gu");
		String foodtype = request.getParameter("foodtype");
		
		GoodResDAO grdao = new GoodResDAO();
		request.setAttribute("guList", grdao.getGuList(gu));
		request.setAttribute("typeList", grdao.getTypeList(foodtype));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goodRes/goodres_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
