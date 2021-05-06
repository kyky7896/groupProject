package com.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.order.db.AdminOrderDAO;

public class AdminOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminOrderDetailAction_execute() 호출");

		// 세션제어(관리자)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("f_mem_id");

		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 파라미터값 저장(trade_num)
		String trade_num = request.getParameter("trade_num");
		
		// AdminOrderDAO 객체 생성
		// - getAdminOrderDetail(trade_num)
		AdminOrderDAO aodao = new AdminOrderDAO();
		
		// request 영역에 저장
		request.setAttribute("adminOrderDetail", aodao.getAdminOrderList(trade_num));

		// 페이지 이동 ("./adminorder/admin_order_modify.jsp")
		forward.setPath("./adminorder/admin_order_modify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
