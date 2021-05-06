package com.admin.goods.action;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.ag")
public class AdminGoodsFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[C] : AdminGoodsFrontController_doProcess() 호출됨");
		
		System.out.println("\n\n 1.가상주소 계산");
			String requestURI = request.getRequestURI(); // 가상주소 풀
			String contextPath = request.getContextPath(); // ~프로젝트 이름
			String command = requestURI.substring(contextPath.length()); 
			System.out.println("가상주소 이름(command) >> "+command);
			
		System.out.println("\n\n 2.가상주소 맵핑");
			ActionForward forward = null;
			Action action = null;
			
			if(command.equals("/GoodsAdd.ag")){
				System.out.println("[C] : GoodsAdd.ag 호출됨");
				// 어디로 이동? 등록할 상품 작성하는 페이지(View)로 바로 이동
				System.out.println("[C] : ./admingoods/admin_goods_write.jsp로 바로 이동(Model 거치지 X)");
				forward = new ActionForward();
				forward.setPath("./admingoods/admin_goods_write.jsp");
				forward.setRedirect(false);
			}else if(command.equals("/AdminGoodsAddAction.ag")){
				System.out.println("[C] : GoodsAddAction.ag 호출됨");
				// 어디로 이동? 사용자가 작성한 상품 데이터 디비에 insert해야 하니까 Model로 이동
				System.out.println("[C] : 디비에 상품 정보 등록해주는 객체 생성(Model)");
				action = new AdminGoodsAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/AdminGoodsList.ag")){
				System.out.println("[C] : AdminGoodsList.ag 호출됨");
				// GoodsListAction 객체 생성
				action = new AdminGoodsListAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("[C] : AdminGoodsList.ag 호출됨");
				
				
			}else if(command.equals("/AdminGoodsModify.ag")){
				System.out.println("C : /AdminGoodsModify.ag 호출 ");
				System.out.println("C : DB정보를 가져와서 수정 -> 저장");
				
				// AdminGoodsModifyAction 객체 생성
				action = new AdminGoodsModifyAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/AdminGoodsModifyProAction.ag")){
				System.out.println("C : /AdminGoodsModifyProAction.ag 호출 ");
				System.out.println("C : DB정보를 가져와서 수정 -> 저장");
				
				// AdminGoodsModifyAction 객체 생성
				action = new AdminGoodsModifyProAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	else if(command.equals("/AdminGoodsDelete.ag")){
				System.out.println("C : /AdminGoodsDelete.ag 호출 ");
				System.out.println("C : DB정보를 가져와서 수정 -> 저장");
				
				// AdminGoodsDeleteAction 객체 생성
				action = new AdminGoodsDeleteAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			
		System.out.println("\n\n 3.페이지 이동");
			if(forward != null){ 
				if(forward.isRedirect()){ // true : 주소 + 화면 바뀜
					response.sendRedirect(forward.getPath());
				}else{ // false : 주소 그대로, 화면만 바뀌는 이동방식
					request.getRequestDispatcher(forward.getPath()).forward(request, response); // 객체 생성 후 forward() 까지
				}
			}
		
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[C] : AdminGoodsFrontController_doGet() 호출됨");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[C] : AdminGoodsFrontController_doPost() 호출됨");
		doProcess(request, response);
	}
	
	

}
