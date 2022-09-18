package com.goodres.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodres.db.GoodResBoardBean;
import com.goodres.db.GoodResDAO;

public class GoodResSearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : GoodResSearchAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		String keyword = request.getParameter("gr_input_keyword_search");
		
		GoodResDAO grdao = new GoodResDAO();
		List<GoodResBoardBean> keywordList = new ArrayList<GoodResBoardBean>();
		keywordList = grdao.getKeywordSearch(keyword);
		
		request.setAttribute("keywordList", keywordList);
		request.setAttribute("keyword", keyword);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goodRes/goodres_search.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
