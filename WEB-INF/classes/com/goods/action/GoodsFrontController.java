package com.goods.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.go")
public class GoodsFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Goods-doProcess() 호출");

		System.out.println("\n\n 1. 가상주소 계산");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);

		System.out.println("\n 1. 가상주소 계산");

		System.out.println("\n 2. 가상주소 매핑");

		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/GoodsList.go")){
			// http://localhost:8088/food/GoodsList.go
		System.out.println("C : /GoodsList.go 호출");
		System.out.println("C : DB에서 정보를 가져와서 view 출력");
		
			action=new GoodsListAction();
			
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}if(command.equals("/GoodsDetail.go")){
			System.out.println("C : /GoodsDetail.go 호출");
			System.out.println("C : DB에서 정보를 가져와서 view 출력");
			
			action=new GoodsDetailAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		System.out.println("\n 2. 가상주소 매핑");

		System.out.println("\n 3. 페이지 이동");

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());

				dis.forward(request, response);
			}
		}

		System.out.println("\n 3. 페이지 이동");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Goods-doGet() 호출");

		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Goods-doPost() 호출");

		doProcess(request, response);
	}

}
