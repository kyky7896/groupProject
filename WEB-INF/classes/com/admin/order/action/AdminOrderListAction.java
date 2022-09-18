package com.admin.order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.order.db.AdminOrderDAO;

public class AdminOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : AdminOrderListAction_execute() 호출 ");
		
		// 세션제어(관리자)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("f_mem_id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			
			return forward;
		}
		
		// AdminOrderDAO 객체 생성
		// - getAdminOrderList() (주문정보 전부를 가져오기)
		AdminOrderDAO aodao = new AdminOrderDAO();
		// aodao.getAdminOrderList();
				
		// request 영역 저장
		request.setAttribute("orderAllList", aodao.getAdminOrderList());
		
		// 페이지 ("./adminorder/admin_order_list.jsp")
		forward.setPath("./adminorder/admin_order_list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
