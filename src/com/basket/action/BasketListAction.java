package com.basket.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.db.BasketDAO;

public class BasketListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("M : BasketListAction_execute() 호출");
		
		HttpSession session = request.getSession();
	    String id = (String)session.getAttribute("f_mem_id");
	    ActionForward forward = new ActionForward();
	    if(id==null){
	      forward.setPath("./MemberLogin.me");
	      forward.setRedirect(true);
	      return forward;
	    }
	    
	    BasketDAO bkdao = new BasketDAO();
	    Vector totalList = bkdao.getBasketList(id);
	    
	    request.setAttribute("basketList", (List)totalList.get(0));
	    request.setAttribute("goodsList", (List)totalList.get(1));
		
	    forward.setPath("./goods_order/goods_basket.jsp");
	    forward.setRedirect(false);
		return forward;
	}

}
