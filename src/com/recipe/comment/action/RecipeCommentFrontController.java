package com.recipe.comment.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.co")
public class RecipeCommentFrontController extends HttpServlet {
	
protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[C] RecipeCommentFrontController_doProcess() 호출됨");
		
		System.out.println(" \n\n 1. 가상주소 계산 ");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		System.out.println("command = "+command);
		
		
		System.out.println(" \n\n 2. 가상주소 맵핑 ");
		
		Action action = null;
							
		if(command.equals("/CommentAdd.co")){
			System.out.println("[C] CommentAdd.co 호출됨!!");
			action = new RecipeCommentAddAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/CommentUpdate.co")){
			System.out.println("[C] CommentUpdate.co 호출됨!!");
			action = new RecipeCommentUpdateAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/CommentDelete.co")){
			System.out.println("[C] CommentDelete.co 호출됨!!");
			action = new RecipeCommentDeleteAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/CommentList.co")){
			System.out.println("[C] CommentList.co 호출됨!!");
			action = new RecipeCommentListAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/CountComment.co")){
			System.out.println("[C] CountComment.co 호출됨!!");
			action = new CountCommentAction();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AddReComment.co")){
			System.out.println("[C] AddReComment.co 호출됨!!");
			action = new AddReComment();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(command.equals("/CommentReList.co")){
			System.out.println("[C] CommentReList.co 호출됨!!");
			action = new ReCommentList();
			try {
				action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[C] RecipeCommentFrontController_doGet() 호출됨");
		doProcess(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[C] RecipeCommentFrontController_doPost() 호출됨");
		doProcess(request,response);
	}
	

}
