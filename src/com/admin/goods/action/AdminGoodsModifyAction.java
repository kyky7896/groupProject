package com.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : GoodsModifyAction_execute() 실행");

		// 전달된 파라미터값 저장(num)
		int num = Integer.parseInt(request.getParameter("num"));

		// DAO 객체 생성 -> num에 해당하는 상품정보를 가져옴(GoodsBean)
		// -> getGoods(num)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		// request영역 저장
		// GoodsBean gb = agdao.getGoods(num);
		// request.setAttribute("gb", gb);
		
		request.setAttribute("goodsbean", agdao.getGoods(num));

		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./admingoods/admin_goods_modify.jsp");
		forward.setRedirect(false);

		return forward;
	}

}
