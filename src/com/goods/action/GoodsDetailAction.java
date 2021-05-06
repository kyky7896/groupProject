package com.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.goods.db.GoodsDAO;

public class GoodsDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : GoodsDetailAction_execute() 호출!");
		
		int num=Integer.parseInt(request.getParameter("num"));
		
		GoodsDAO gdao=new GoodsDAO();
		
		request.setAttribute("goods", gdao.getGoods(num));
		
		//페이지이동
		ActionForward forward=new ActionForward();
		forward.setPath("./goods/goods_detail.jsp");
		forward.setRedirect(false);
		
		
		return forward;
	}

}
