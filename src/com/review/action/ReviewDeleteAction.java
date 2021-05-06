package com.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.review.db.ReviewDAO;

public class ReviewDeleteAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, 
      HttpServletResponse response) throws Exception {
    System.out.println("M : ReviewDeleteAction_execute() 실행");
    
    int gr_num = Integer.parseInt(request.getParameter("gr_num"));
    int grv_num = Integer.parseInt(request.getParameter("grv_num"));
    
    ReviewDAO rdao = new ReviewDAO();
    rdao.reviewDelete(grv_num);
    rdao.ratingAvg(gr_num);
    
    ActionForward forward = new ActionForward();
    forward.setPath("./GoodResContentAction.gr?num="+gr_num+"");
    forward.setRedirect(true);
    return forward;
  }

}
