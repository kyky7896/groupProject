package com.review.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.review.db.ReviewBean;
import com.review.db.ReviewDAO;

public class ReviewMyAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request, 
      HttpServletResponse response) throws Exception {
    System.out.println("M : ReveiwMyAction_execute() 호출");
    
    ActionForward forward = new ActionForward();
    HttpSession session = request.getSession();
    String id = (String)session.getAttribute("f_mem_id");
    if(id==""){
      forward.setPath("./Main.me");
      forward.setRedirect(true);
      return forward;
    }
    
    ReviewDAO rdao = new ReviewDAO();
    request.setAttribute("myReviewList", rdao.myReviewList(id));
    
    forward.setPath("./goodRes_review/gr_rvMy.jsp");
    forward.setRedirect(false);
    return forward;
  }

}
