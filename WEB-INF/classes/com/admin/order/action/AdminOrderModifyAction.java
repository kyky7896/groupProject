package com.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.order.db.AdminOrderDAO;
import com.order.db.OrderBean;

public class AdminOrderModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : AdminOrderModifyAction_execute() 호출");

		// 세션제어(관리자)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("f_mem_id");

		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 파라미터값 저장(trade_num, status, trans_num)
		// 파라미터가 2개 이상이면 Bean을 사용하는게 낫다
		// => OrderBean 저장
		OrderBean ob = new OrderBean();
		ob.setF_or_trade_num(request.getParameter("trade_num"));
		ob.setF_or_status(Integer.parseInt(request.getParameter("status")));
		ob.setF_or_trans_num(request.getParameter("trans_num"));
		
		// AdminOrderDAO 객체 생성 - updateOrder(ob)
		AdminOrderDAO aodao = new AdminOrderDAO();
		aodao.updateOrder(ob);
		
		
		// 페이지 이동 (AdminOrderList.ao)
		forward.setPath("./AdminOrderList.ao");
		forward.setRedirect(true);
		
		return forward;
	}
	
}
