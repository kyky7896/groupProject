package com.recipe.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.board.action.Action;
import com.recipe.board.action.ActionForward;


@WebServlet("*.re")
public class RecipeController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController - doProcess() 호출! ");
		
		/* 1. 가상주소 계산 */
		System.out.println("\n\n\n      1. 가상주소 계산              ");
		
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : "+requestURI);
		//System.out.println("URL : "+request.getRequestURL());
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath : "+contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : "+command);
		
		System.out.println("      1. 가상주소 계산              ");
		/* 1. 가상주소 계산 */
		
		
		
		
		
		
		/* 2. 가상주소 매핑 (연결)*/
		System.out.println("\n\n\n      2. 가상주소 매핑 (연결)        ");
		
		Action action = null;
		ActionForward forward = null;
		
		System.out.println("      2. 가상주소 매핑 (연결)        ");
		/* 2. 가상주소 매핑 (연결)*/
		
		if(command.equals("/RecipeWrite.re")){
			System.out.println("C : /RecipeWrite.re 호출");
			System.out.println("C : DB 사용없이 view 페이지로 이동");
		
			forward = new ActionForward();
			forward.setPath("./recipe/RecipeWriteForm.jsp");
			forward.setRedirect(false);
			
			
		}else if(command.equals("/RecipeWriteAction.re")){
			System.out.println("C : /RecipeWriteAction.re 호출");
			System.out.println("C : DB에 정보를 저장 후 페이지 이동");
			
			// GoodsAddAction 객체 생성
			action = new RecipeWriteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}else if(command.equals("/RecipeDetail.re")){
			System.out.println("[C] : /RecipeDetail.re 호출됨");
			action = new RecipeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/RecipeList.re")){
			System.out.println("C : /RecipeList.re 호출");
			
			//RecipeList객체 생성
			action = new RecipeListAction();
			
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/RecipeUpdate.re")){
			System.out.println("C: /RecipeUpdate.re 호출");
			
			action=new RecipeUpdate();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/RecipeUpdateAction.re")){
			System.out.println("C: /RecipeUpdate.re 호출");
			
			action=new RecipeUpdateAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/RecipeDelete.re")){
			System.out.println("C: /RecipeDelete.re 호출");
			
			action=new RecipeDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/MyRecipeList.re")){
			System.out.println("C : /MyRecipeList.re 호출");
			//MyRecipeListAction 생성
			action = new MyRecipeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
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
		System.out.println("MemberFrontController - doGet() 호출! ");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MemberFrontController - doPost() 호출! ");
		doProcess(request, response);
	}

}
