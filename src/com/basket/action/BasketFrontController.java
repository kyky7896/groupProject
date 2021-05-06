package com.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8088/food/GoodsDetail.go
@WebServlet("*.ba")
public class BasketFrontController extends HttpServlet {
  protected void doProcess(HttpServletRequest request, 
		  HttpServletResponse response) throws ServletException, IOException {
    System.out.println("doProcess() 호출");

    System.out.println("\n\n 1.가상주소 계산");
      String requestURI = request.getRequestURI(); // 가상주소 풀
      String contextPath = request.getContextPath(); // ~프로젝트 이름
      String command = requestURI.substring(contextPath.length()); 
      System.out.println("가상주소 이름(command) >> "+command);
      
      
      
      
      
      
    System.out.println("\n\n 2.가상주소 맵핑");
      ActionForward forward = null;
      Action action = null;
      
      if(command.equals("/BasketAdd.ba")){
        System.out.println("C : /BasketAdd.ba 호출");
        
        action = new BasketAddAction();
        try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
      }else if(command.equals("/BasketList.ba")){
    	  System.out.println("C : /BasketList.ba 호출");
    	  
    	  action = new BasketListAction();
    	  try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
      }else if(command.equals("/BasketDelete.ba")){
    	  System.out.println("C : /BasketDelete.ba 호출");
    	  
    	  action = new BasketDeleteAction();
    	  try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
      }

      
      
      
      
      
    System.out.println("\n\n 3.페이지 이동");
      if(forward != null){ 
        if(forward.isRedirect()){
          response.sendRedirect(forward.getPath());
        }else{ 
        	RequestDispatcher dis = 
        			request.getRequestDispatcher(forward.getPath());
        	
        	dis.forward(request, response);
        }
      }
  }
  
  @Override
  protected void doGet(HttpServletRequest request, 
		  HttpServletResponse response) throws ServletException, IOException {
    System.out.println("doGet() 호출");
    doProcess(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request,
		  HttpServletResponse response) throws ServletException, IOException {
    System.out.println("doPost() 호출");
    doProcess(request, response);
  }
}
