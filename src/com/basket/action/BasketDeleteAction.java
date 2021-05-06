package com.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basket.db.BasketDAO;

public class BasketDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("M : BasketDeleteAction_execute() 호출");
		
		HttpSession session = request.getSession();
	    String id = (String)session.getAttribute("f_mem_id");
	    ActionForward forward = new ActionForward();
	    if(id==null){
	      forward.setPath("./MemberLogin.me");
	      forward.setRedirect(true);
	    }
	    
	    int f_bas_num = Integer.parseInt(request.getParameter("f_bas_num"));
		
	    BasketDAO bkdao = new BasketDAO();
	    bkdao.basketDelete(f_bas_num);
	    
	    forward.setPath("./BasketList.ba");
	    forward.setRedirect(true);
		return forward;
	}

}
