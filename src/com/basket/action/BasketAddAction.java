package com.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.db.BasketBean;
import com.basket.db.BasketDAO;

public class BasketAddAction implements Action {

  @Override
  public ActionForward execute(HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    System.out.println("M : BasketAddAction_execute() 호출");
    
    HttpSession session = request.getSession();
    String id = (String)session.getAttribute("f_mem_id");
    ActionForward forward = new ActionForward();
    if(id==null){
      forward.setPath("./MemberLogin.me");
      forward.setRedirect(true);
      return forward;
    }
    request.setCharacterEncoding("UTF-8");
    
    BasketBean basb = new BasketBean();
    basb.setF_bas_gs_num(Integer.parseInt(request.getParameter("gs_num")));
    basb.setF_bas_amount(Integer.parseInt(request.getParameter("amount")));
    basb.setF_bas_mem_id(id);
    System.out.println("M : 장바구니 정보 "+basb);
    
    BasketDAO bkdao = new BasketDAO();
    int check = bkdao.checkGoods(basb);
    if(check!=1){
    	bkdao.basketAdd(basb);
    }
    System.out.println("M : 장바구니 저장 완료");
    
    forward.setPath("./BasketList.ba");
    forward.setRedirect(true);
    return forward;
  }

}
