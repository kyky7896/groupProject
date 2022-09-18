package com.admin.goods.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;
import com.admin.goods.db.GoodsBean;

public class AdminGoodsModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : AdminGoodsModifyProAction_execute() 호출");
		// 한글처리
		
		request.setCharacterEncoding("utf-8");
		
		// 파라미터값 저장
		// -> GoodsBean 객체에 저장
		GoodsBean gb = new GoodsBean();
		
		gb.setF_gs_amount(Integer.parseInt(request.getParameter("f_gs_amount")));
		gb.setF_gs_cate(request.getParameter("f_gs_cate"));
		gb.setF_gs_msg(request.getParameter("f_gs_msg"));
		gb.setF_gs_name(request.getParameter("f_gs_name"));
		gb.setF_gs_num(Integer.parseInt(request.getParameter("f_gs_num")));
		gb.setF_gs_price(Integer.parseInt(request.getParameter("f_gs_price")));
	//	gb.setF_gs_price(Integer.parseInt(request.getParameter("f_gs_price")));
		gb.setF_gs_hit(Integer.parseInt(request.getParameter("f_gs_hit")));

		System.out.println("!!!!!!!!!"+gb);
		// DAO 객체생성 - modifyGoods(상품정보)
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		agdao.modifyGoods(gb);
		
		// 페이지이동 (GoodsList)
		ActionForward forward = new ActionForward();
/*		forward.setPath("./AdminGoodsList.ag");
		forward.setRedirect(true);*/
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('수정성공!!');");
			out.print(" opener.location.href='./AdminGoodsList.ag '; ");
			out.print("window.close();");
			out.print("</script>");
		
		out.close();
		
		return forward;
	}

}
