package com.admin.order.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.action.OrderDetailAction;

@WebServlet("*.ao")
public class AdminOrderFrontController extends HttpServlet{


			protected void doProcess(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				System.out.println("AdminOrderFrontController-doProcess()");
				
				System.out.println("\n\n 1. 가상주소 계산");
				String requestURI = request.getRequestURI();
				String contextPath = request.getContextPath();

				String command = requestURI.substring(contextPath.length());
				System.out.println("command : " + command);

				System.out.println("\n 1. 가상주소 계산");

				System.out.println("\n 2. 가상주소 매핑");

				Action action = null;
				ActionForward forward = null;
				
				if(command.equals("/AdminOrderList.ao")){
					System.out.println("/AdminOrderList.ao 호출");
					System.out.println("C : DB 정보를 view 페이지 출력");

					// OrderDetailAction 객체 생성
					action = new AdminOrderListAction();
					
					try {
						forward = action.execute(request, response); 
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
				
				
				else if(command.equals("/AdminOrderDetail.ao")){
					System.out.println("C : /AdminOrderDetail.ao 호출");
					System.out.println("C : 전달받은 파라미터를 사용해서 DB정보 출력");
					
					// AdminOrderDetailAction 객체
					action = new AdminOrderDetailAction();
					
					try {
						forward = action.execute(request, response); 
					} catch (Exception e) {
						e.printStackTrace();
					}	
				}
				
				
				else if(command.equals("/AdminOrderModify.ao")){
					System.out.println("C : /AdminOrderModify.ao 호출");
					System.out.println("C : 운송장 번호, 주문상태 변경(DB) -> 주문 List 이동");
					
					// AdminOrderModifyAction 객체
					action = new AdminOrderModifyAction();
					
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
			    System.out.println("AdminOrderFrontController-doGet()");
			    doProcess(request, response);
			}

			@Override
			protected void doPost(HttpServletRequest request,
					HttpServletResponse response) throws ServletException, IOException {
				System.out.println("AdminOrderFrontController-doPost()");
				doProcess(request, response);
				
			}
		
		}