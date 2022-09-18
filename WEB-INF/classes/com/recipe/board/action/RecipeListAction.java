package com.recipe.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.goods.db.GoodsDAO;
import com.recipe.board.db.RecipeBean;
import com.recipe.board.db.RecipeDAO;

public class RecipeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : RecipeListAction_execute() 호출!");
		request.setCharacterEncoding("UTF-8");
		//로그인 세션 처리
		HttpSession session = request.getSession();		
		String id = (String) session.getAttribute("f_mem_id");
		
		//DAO 객체 생성
		RecipeDAO rdao=new RecipeDAO();
		
		//카테고리 처리
		String item = request.getParameter("item");
		String type = request.getParameter("type"); //최신순, 조회순
		System.out.println("카테고리 번호>>"+item);
		System.out.println("type >>"+type);
		
		//해시태그 단어
		String q = request.getParameter("q");
		List<RecipeBean> recipeList = null;
		
		//카테고리,type,해시태그 처리
		if(item == null){
			if(type == null && q == null){
				System.out.println("전체보기");
				item="all";
				recipeList = rdao.getRecipeList(item);
			}else if(type == null && q != null){
				System.out.println("해시태그 검색어");
				recipeList = rdao.getSearch(q);
			}else if(type != null && q == null){
				System.out.println("타입별 검색어");
				recipeList = rdao.getRecipeList(item,type);
			}else if(type != null && q != null){
				System.out.println("해시태그 검색어2222");
				recipeList = rdao.getSearch(q);
			}
		}else if(item !=null){
			if(type == null && q == null){
				System.out.println("카테고리 검색어");
				recipeList = rdao.getRecipeList(item);
			}else if(type == null && q != null){
				System.out.println("해시태그 검색어3333");
				recipeList = rdao.getSearch(q);
			}else if(type != null && q == null){
				System.out.println("카테고리,type별 정렬 검색어");
				recipeList = rdao.getRecipeList(item,type);
			}else if(type != null && q != null){
				System.out.println("해시태그 검색어4444");
				recipeList = rdao.getSearch(q);
			}
		}
		

		
		
		//getListCount()리스트에 몇개 있는지 체크
		int cnt = rdao.getListCount(item);
		System.out.println("List갯수 >>"+cnt);
		
		/*
		//페이징 처리////////////////////////////////////////////////
		int pageSize = 16;
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		}
		//페이지 출력 첫행 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage-1)*pageSize+1;
		if(cnt != 0){
			recipeList = rdao.getRecipeList(startRow,pageSize);
			System.out.println("Action>> 글 목록 저장완료! ");
		}*/
		
		//request 영역에 저장
		request.setAttribute("recipeList", recipeList);
		
		System.out.println(recipeList);
		
		//페이지 이동()
		ActionForward forward = new ActionForward();
		forward.setPath("./menuList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
