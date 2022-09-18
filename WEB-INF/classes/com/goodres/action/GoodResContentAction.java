package com.goodres.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goodres.db.GoodResBoardBean;
import com.goodres.db.GoodResDAO;
import com.review.db.ReviewDAO;

public class GoodResContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : GoodResContentAction_execute() 호출");
		
		int goodres_num = Integer.parseInt(request.getParameter("num"));		// search 에서 넘어오는 음식점번호 
		//int goodres_num_rv = Integer.parseInt(request.getParameter("gr_num"));	// review 에서 넘어오는 음식점번호
		
		GoodResDAO grdao = new GoodResDAO();
		request.setAttribute("grbb", grdao.getBoardBean(goodres_num));
		
		ReviewDAO rdao = new ReviewDAO();
		int cnt = rdao.getReviewCount(goodres_num);
		
		int pageSize = 5;
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
	    int currentPage = Integer.parseInt(pageNum);
	    
	    int startRow = (currentPage-1)*pageSize+1;
	    ArrayList reviewList = null;
	    
	    if(cnt!=0) {
	      reviewList = rdao.getReviewList(goodres_num, startRow, pageSize);
	    }
	    
	    // 조회수 증가 추가
	    grdao.updateReadCount(goodres_num);	    
	    
	    request.setAttribute("cnt", cnt);
	    request.setAttribute("pageSize", pageSize);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("startRow", startRow);
	    request.setAttribute("reviewList", reviewList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./goodRes/goodres_content.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
}
