package com.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.or")
public class OrderFrontController extends HttpServlet {

		protected void doProcess(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			System.out.println("BasketFrontController-doProcess()");
			
			System.out.println("\n\n 1. 가상주소 계산");
			String requestURI = request.getRequestURI();
			String contextPath = request.getContextPath();

			String command = requestURI.substring(contextPath.length());
			System.out.println("command : " + command);

			System.out.println("\n 1. 가상주소 계산");

			System.out.println("\n 2. 가상주소 매핑");

			Action action = null;
			ActionForward forward = null;
			
			if(command.equals("/OrderStart.or")){
				System.out.println("C : /OrderStart.or 호출");
				System.out.println("C : 장바구니 정보를 저장");
			
			// OrderStartAction 객체 생성
				action = new OrderStartAction();
				
				try {
					forward = action.execute(request, response); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			else if(command.equals("/OrderAdd.or")){
				System.out.println("C : /OrderAdd.or 호출");
				System.out.println("C : 전달받은 정보를 DB에 저장");
				
				// OrderAddAction 객체 생성
				action = new OrderAddAction();
				
				try {
					forward = action.execute(request, response); 
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
			
			else if(command.equals("/OrderList.or")){
				System.out.println("C: /OrderList.or 호출");
				System.out.println("C: DB 정보 가져와서 view 페이지 출력");
			
				// OrderListAction 객체 생성
				action = new OrderListAction();
				
				try {
					forward = action.execute(request, response); 
				} catch (Exception e) {
					e.printStackTrace();
				}						
			}
			
			
			else if(command.equals("/OrderDetail.or")){
				System.out.println("C : /OrderDetail.or 호출");
				System.out.println("C : DB 정보를 view 페이지 출력");

				// OrderDetailAction 객체 생성
				action = new OrderDetailAction();
				
				try {
					forward = action.execute(request, response); 
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
		protected void doGet(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		    System.out.println("BasketFrontController-doGet()");
		    doProcess(request, response);
		}

		@Override
		protected void doPost(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			System.out.println("BasketFrontController-doPost()");
			doProcess(request, response);
			
		}
	
	}
