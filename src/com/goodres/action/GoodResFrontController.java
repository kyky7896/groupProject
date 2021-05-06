package com.goodres.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.gr")
public class GoodResFrontController extends HttpServlet {
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("GoodResFrontController - doProcess() 호출! ");
		
		/* 1. 가상주소 계산 */
		System.out.println("\n\n\n      1. 가상주소 계산              ");
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : "+requestURI);
		//System.out.println("URL : "+request.getRequestURL());
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath : "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		System.out.println(" 1. 가상주소 계산 ");
		/* 1. 가상주소 계산 */
		
		/* 2. 가상주소 매핑 (연결)*/
		System.out.println("\n\n\n      2. 가상주소 매핑 (연결)        ");
		
		Action action = null;
		ActionForward forward = null;
		
		if (command.equals("/GoodResMain.gr")) {
			System.out.println("C : /GoodResMain.gr 호출");
			System.out.println("C : DB정보를 받아서 view 출력");

			action = new GoodResMainAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/GoodResList.gr")) {
			System.out.println("C : /GoodResList.gr 호출");

			// DB사용 객체는 ->Action 객체로 구현 // GoodResListAction 객체를 생성
			action = new GoodResListAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
			
		} else if(command.equals("/GoodResMainAction.gr")) {
			System.out.println("C : /GoodResMainAction.gr 호출");
			System.out.println("C : DB에서 데이터 가지고 view이동");
			
			action = new GoodResMainAction();
			
			try {				
				forward = action.execute(request, response);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/GoodResSearchAction.gr")) {
			System.out.println("C : /GoodResSearchAction.gr 호출");
			System.out.println("C : DB에서 데이터 가지고 view이동");
			
			action = new GoodResSearchAction();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else if(command.equals("/GoodResContentAction.gr")) {
			System.out.println("C : /GoodResContentAction.gr 호출");
			System.out.println("C : DB에서 데이터 가지고 view이동");
			
			action = new GoodResContentAction();
			
			try {
				forward = action.execute(request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} 
		
		
		/* 2. 가상주소 매핑 (연결)*/
		
		
		/* 3. 가상주소 페이지 이동 */
		System.out.println("\n\n\n      3. 가상주소 페이지 이동        ");
		
		if(forward != null){ // 이동정보가 있을경우 페이지 이동
			if(forward.isRedirect()) {
				System.out.println("C : sendRedirect 이동 ->" + forward.getPath());
				//true
				response.sendRedirect(forward.getPath());
			} else {
				//false
				System.out.println("C : forward 이동 -> " + forward.getPath());
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());				
				dis.forward(request, response);				
			}
		}
		
		System.out.println("      3. 가상주소 페이지 이동        ");
		/* 3. 가상주소 페이지 이동 */	
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
}
