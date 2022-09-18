package com.recipe.board.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.recipe.board.db.RecipeBean;
import com.recipe.board.db.RecipeDAO;

public class RecipeWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : GoodsAddAction_execute() 호출");
		
		// 세션확인 => 나중에 풀어주세요!
		HttpSession session = request.getSession();
		String f_mem_id = (String) session.getAttribute("f_mem_id");
		String f_mem_nick = (String)session.getAttribute("f_mem_nick");
				
		// 파일 업로드
		// upload 가상폴더 생성
	
		// 파일이 저장되는 위치
		ServletContext ctx = request.getServletContext();
		String realpath = ctx.getRealPath("/recipe_upload");
		
		System.out.println("M : realpath -"+realpath);
		
		// 파일의 용량
		int maxSize = 10 * 1024 * 1024; //10MB
		
		MultipartRequest multi =
				new MultipartRequest(
						request,
						realpath,
						maxSize,
						"UTF-8",
						new DefaultFileRenamePolicy()
						);
		
		System.out.println("M : 파일업로드 완료!");
		
		RecipeBean rb=new RecipeBean();
		
	// 빈에 아이디, 별명 저장
		rb.setF_rcp_id(f_mem_id);
		rb.setF_rcp_nick(f_mem_nick);
		
		
		//제목, 카테고리, 내용
		rb.setF_rcp_title(multi.getParameter("f_rcp_title"));
		rb.setF_rcp_cate(multi.getParameter("category"));
		rb.setF_rcp_content(multi.getParameter("f_rcp_content"));
		
		
		//썸네일 이미지
		String thumb_img=multi.getFilesystemName("input_thum_img");
		System.out.println("M : img "+thumb_img);
		rb.setF_rcp_th_img(thumb_img);
		
		//재료 넣기
		String[] f_rcp_ingre0=null;
		String[] f_rcp_ingre1=null;
		String[] f_rcp_ingre2=null;
		String[] f_rcp_ingre3=null;
		
		String f_rcp_ingre="";
		
		if(multi.getParameterValues("f_rcp_ingre0")!=null){
		f_rcp_ingre0=multi.getParameterValues("f_rcp_ingre0");
			/*if(f_rcp_ingre!=""){*/
			f_rcp_ingre += f_rcp_ingre0[0].toString()
						+","+f_rcp_ingre0[1].toString();
			
		/*	}*/
		}
		
		
			/*f_rcp_ingre0[0]="";
			f_rcp_ingre0[1]="";*/
			
		
		if(multi.getParameterValues("f_rcp_ingre1")!=null){
		f_rcp_ingre1 = multi.getParameterValues("f_rcp_ingre1");	
		f_rcp_ingre +=","+f_rcp_ingre1[0].toString()
				+","+f_rcp_ingre1[1].toString();
		}
		
		if(multi.getParameterValues("f_rcp_ingre2")!=null){
		f_rcp_ingre2=multi.getParameterValues("f_rcp_ingre2");
		f_rcp_ingre +=","+f_rcp_ingre2[0].toString()
				+","+f_rcp_ingre2[1].toString();
		}
		
		if(multi.getParameterValues("f_rcp_ingre3")!=null){
		f_rcp_ingre3=multi.getParameterValues("f_rcp_ingre3");
		f_rcp_ingre +=","+f_rcp_ingre3[0].toString()
				+","+f_rcp_ingre3[1].toString();
		}
		
		
		System.out.println("f_rcp_ingre0 :"+f_rcp_ingre0[0]);
		System.out.println("f_rcp_ingre0 :"+f_rcp_ingre0[1]);


		rb.setF_rcp_ingre(f_rcp_ingre);
		
		
		
		
		
		//재료 부를때
		
		//요리순서 내용
		String ord_text="";
		if(multi.getParameter("f_rcp_ord_text")!=null){
			ord_text=multi.getParameter("f_rcp_ord_text");
		}

		if(multi.getParameter("ord_text1")!=null){
			ord_text+=","+multi.getParameter("ord_text1");
		}else{
			ord_text += "";
		}
		if(multi.getParameter("ord_text2")!=null){
			ord_text+=","+multi.getParameter("ord_text2");
		}else{
			ord_text += "";
		}

		if(multi.getParameter("ord_text3")!=null){
			ord_text+=","+multi.getParameter("ord_text3");
		}else{
			ord_text += "";
		}
		
		System.out.println("ord_text:"+ord_text);
		rb.setF_rcp_ord(ord_text);
		
		
		//요리순서 이미지
		
		String f_rcp_ord_img = "";
		
		if(multi.getFilesystemName("ord_img_upload") != null){
			f_rcp_ord_img = multi.getFilesystemName("ord_img_upload");
		}
		
		if(multi.getFilesystemName("ord_img_upload1") != null){
			f_rcp_ord_img += ","+multi.getFilesystemName("ord_img_upload1");
		}else{
			f_rcp_ord_img += "";
		}
		
		if(multi.getFilesystemName("ord_img_upload2") != null){
			f_rcp_ord_img += ","+multi.getFilesystemName("ord_img_upload2");
		}else{
			f_rcp_ord_img += "";
		}
		
		if(multi.getFilesystemName("ord_img_upload3") != null){
			f_rcp_ord_img += ","+multi.getFilesystemName("ord_img_upload3");
		}else{
			f_rcp_ord_img += "";
		}
		
		rb.setF_rcp_ord_img(f_rcp_ord_img);
		
		System.out.println("f_rcp_ord_img:"+f_rcp_ord_img);
		
		
		
		//
		
		
		//요리완성(다중파일)
		/*
		ArrayList saveFiles=new ArrayList();
		ArrayList origFiles=new ArrayList();
		
		Enumeration e=multi.getFileNames(); //파일명 정보를 배열로 만듬
		
		//System.out.println(e);
		while(e.hasMoreElements()){ // 다음 요소가 있으면 계속 반복
		
			//String name=multi.getFilesystemName("f_rcp_ord_img0");
			Object names = e.nextElement(); //파일명 정보 Enumeration의 다음 요소를 name에 저장한다
			//String[] filenames=(multi.getFilesystemName(name).split(","));
			//saveFiles.add(multi.getFilesystemName(name).split(","));
			saveFiles=(ArrayList<String>) Arrays.asList(filenames);
			//origFiles.add(multi.getOriginalFileName(name));

			System.out.println("saveFiles :"+names.toString());
			}
		
		
		
		*/
	
		rb.setF_rcp_hashtag(multi.getParameter("f_rcp_hashtag"));
		
		RecipeDAO rdao=new RecipeDAO();
		rdao.InsertRecipe(rb);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./RecipeList.re");
		forward.setRedirect(true);
		return forward;
		
		
	}

}
