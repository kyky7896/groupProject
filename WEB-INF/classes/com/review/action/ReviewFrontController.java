package com.review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("C : doProcess() 호출");
			
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
			
			ActionForward forward = null;
			Action action = null;
	
			if (command.equals("/ReviewWrite.rv")) {
				System.out.println("C : /ReviewWrite.rv 호출");
	
				forward = new ActionForward();
				forward.setPath("./goodRes_review/gr_rvWrite.jsp");
				forward.setRedirect(false);
									   
			} else if (command.equals("/ReviewAdd.rv")) {
				System.out.println("C : /ReviewAdd.rv 호출");
	
				action = new ReviewAddAction();
				
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(command.equals("/ReviewDelete.rv")) {
			  System.out.println("C : /ReviewDelete.rv 호출");
			  
			  action = new ReviewDeleteAction();
			  
			  try {
          forward = action.execute(request, response);
        } catch (Exception e) {
          e.printStackTrace();
        }
			} else if(command.equals("/ReviewMy.rv")) {
			  System.out.println("C : /ReviewMy.rv 호출");
			  
			  action = new ReviewMyAction();
			  try {
          forward = action.execute(request, response);
        } catch (Exception e) {
          e.printStackTrace();
        }
			}
			
			System.out.println("      2. 가상주소 매핑 (연결)        ");
			/* 2. 가상주소 매핑 (연결)*/
				
			/* 3. 가상주소 페이지 이동 */
			System.out.println("\n\n\n      3. 가상주소 페이지 이동        ");
			
			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else {
					RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
	
					dis.forward(request, response);
				}
			}
			
			System.out.println("      3. 가상주소 페이지 이동        ");
			/* 3. 가상주소 페이지 이동 */	
		}
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("C : doGet() 호출");
			doProcess(request, response);
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("C : doPost() 호출");
			doProcess(request, response);
		}
}
