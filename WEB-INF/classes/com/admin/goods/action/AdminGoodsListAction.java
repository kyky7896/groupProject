package com.admin.goods.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;

public class AdminGoodsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 작업 내용 : 디비에 저장되 있는 모든 상품정보를 꺼내오는  */
		System.out.println("[M] : AdminGoodsListAction_execute() 호출됨");
		
		// 관리자 세션 제어 
//		String id = (String)request.getSession().getAttribute("id");
//		
		ActionForward forward = new ActionForward();
//		if(id == null || !id.equals("admin")){
//			forward.setPath("./MemberLogin.me");
//			forward.setRedirect(true);
//			return forward;
//		}
		
		// 파라미터로 넘어오는 데이터 저장(=사용자가 설정한 카테고리 기준(검색) 정렬)
		String item = request.getParameter("item");
		if(item == null) item = "all";
		
		// 디비 작업해주는 객체 생성 _ 관련 메서드 호출 ()
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		
		// 메서드 결과 반환되는 값을 request영역(공유)에 저장
		request.setAttribute("adminGoodsList", agdao.getAdminGoodsList(item));
		
		// 페이지 이동
		forward.setPath("./admingoods/admin_shoppingMain.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
