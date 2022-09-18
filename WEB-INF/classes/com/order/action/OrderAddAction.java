package com.order.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.admin.goods.db.GoodsBean;
import com.basket.db.BasketBean;
import com.basket.db.BasketDAO;
import com.goods.db.GoodsDAO;
import com.order.db.OrderBean;
import com.order.db.OrderDAO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : OrderAddAction_execute() 호출");
		
		// 세션정보
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("f_mem_id");
		
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달받은 정보를 저장하는 OrderBean객체
		// 필요한 정보 저장
		OrderBean ob = new OrderBean();
		ob.setF_or_mem_id(id);
		ob.setF_or_rec_name(request.getParameter("f_or_rec_name"));
		ob.setF_or_rec_postcode(request.getParameter("f_or_rec_postcode"));
		ob.setF_or_rec_address(request.getParameter("f_or_rec_address"));
		ob.setF_or_rec_detailAddress(request.getParameter("f_or_rec_detailAddress"));
		ob.setF_or_rec_extraAddress(request.getParameter("f_or_rec_extraAddress"));
		ob.setF_or_rec_phone(request.getParameter("f_or_rec_phone"));
		ob.setF_or_memo(request.getParameter("f_or_memo"));
		ob.setF_or_trade_type(request.getParameter("f_or_trade_type"));
		ob.setF_or_trade_payer(request.getParameter("f_or_trade_payer"));

		// 장바구니에 있는 상품정보 
		BasketDAO bkdao = new BasketDAO();
		Vector totalList = bkdao.getBasketList(id);
		ArrayList basketList = (ArrayList) totalList.get(0);
		ArrayList goodsList = (ArrayList) totalList.get(1);
		
		// 결제 모듈 호출 => 결제 완료		
		System.out.println("M : 결제모듈 실행 ");
		System.out.println("M : 결제모듈 실행 완료");
		
		// 재고수량 - 구매수량 >= 0
		for(int i=0; i<basketList.size(); i++){
		  GoodsBean gb = new GoodsBean();
		  BasketBean bb = new BasketBean();
		  
		  gb = (GoodsBean)goodsList.get(i);
		  bb = (BasketBean)basketList.get(i);
		  
		  if(gb.getF_gs_amount() - bb.getF_bas_amount() >= 0){
		    System.out.println("구매수량 이상 없음");
		  } else {
		    System.out.println("구매수량이 재고수량을 초과");
		    
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    
		    out.print("<script>");
	      out.print("alert('재고가 부족합니다.');");
	      out.print("history.back();"); 
	      out.print("</script>");
	      
	      out.close();
	      return null;
		  }
		}
		
	// 주문정보 저장 OrderDAO 객체
    OrderDAO ordao = new OrderDAO();
    // 주문정보 저장 메서드
    ordao.addOrder(ob,basketList,goodsList);
    
    System.out.println("M : 주문정보 저장 완료 ");
    
    // 사용자 알림 (문자/메일) -> 쓰레드 사용
    System.out.println("M : 주문 내역 메일 전송중....................");
    
    // 상품정보 수정(판매량 제거)
    //  itwill_goods  수량정보를 수정(판매한 만큼 수량)
    GoodsDAO gdao = new GoodsDAO();
    gdao.updateAmount(basketList);
    
    // 장바구니 정보 삭제 
    // itwill basket 해당 데이터만 삭제
    // BasketDAO bkdao = new BasketDAO();
    bkdao.basketDelete(id);
    
    // 페이지 이동
    forward.setPath("./OrderList.or");
    forward.setRedirect(true);
    
    return forward;
	}

}
