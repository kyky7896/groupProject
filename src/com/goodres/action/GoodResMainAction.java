	package com.goodres.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodres.db.GoodResBoardBean;
import com.goodres.db.GoodResDAO;

public class GoodResMainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : GoodResMainAction_exectue() 호출");
		
		GoodResDAO grdao = new GoodResDAO();
		
		HashMap<String, List> totalList = grdao.getGoodResList();
		HashMap<String, List> totalTypeList = grdao.getGoodResTypeList();
		HashMap<String, List> foodTypeMap = grdao.getGoodResFoodTypeMap();
				
		List<GoodResBoardBean> starAvgList = grdao.getGoodResStarAvgList();
		
		request.setAttribute("totalList", totalList);
		request.setAttribute("totalTypeList", totalTypeList);
		request.setAttribute("starAvgList", starAvgList);	
		request.setAttribute("foodTypeMap", foodTypeMap);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goodRes/goodres_main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
