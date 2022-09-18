package com.recipe.board.action;

import java.awt.print.Printable;
import java.io.File;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.recipe.board.db.RecipeBean;
import com.recipe.board.db.RecipeDAO;

public class RecipeUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : RecipeUpdateAction_execute() 호출!");

		// 세션
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
		String thumb_img="";
		System.out.println("M : 썸네일이미지 쌔거 "+multi.getFilesystemName("input_thum_img"));
		System.out.println("M : 썸네일이미지 옛날거 "+multi.getParameter("f_rcp_th_img"));
		if(multi.getFilesystemName("input_thum_img")!=null){
			thumb_img+=multi.getFilesystemName("input_thum_img");
		}else if(multi.getParameter("f_rcp_th_img")!=null){
			thumb_img+=multi.getParameter("f_rcp_th_img");
		}
		System.out.println("thumb_img"+thumb_img);
		rb.setF_rcp_th_img(thumb_img);
		
		//재료 넣기
		String[] f_rcp_ingre0=null;
		String[] f_rcp_ingre1=null;
		String[] f_rcp_ingre2=null;
		String[] f_rcp_ingre3=null;
		
		String f_rcp_ingre="";
		
		if(multi.getParameterValues("f_rcp_ingre0")!=null){
		f_rcp_ingre0=multi.getParameterValues("f_rcp_ingre0");
			f_rcp_ingre += f_rcp_ingre0[0].toString()
						+","+f_rcp_ingre0[1].toString();
		}
		
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
		System.out.println("현재f_rcp_!!!!!!!!!"+multi.getParameter("f_rcp_ord_text"));
		System.out.println("예전!!!!order!!!"+multi.getParameter("order_text0"));
		
		if((multi.getParameter("f_rcp_ord_text") !=null) || 
			(multi.getParameter("ord_text1")!=null) ||
			(multi.getParameter("ord_text2")!=null) ||
			(multi.getParameter("ord_text3")!=null))
			{
				if(multi.getParameter("f_rcp_ord_text")!=null){
					ord_text=multi.getParameter("f_rcp_ord_text");
				}else if(multi.getParameter("order_text0")!=null){
					ord_text=multi.getParameter("order_text0");
					}
				
				
				if(multi.getParameter("ord_text1")!=null){
					ord_text+=","+multi.getParameter("ord_text1");
				}else if(multi.getParameter("order_text1")!=null){
					ord_text=multi.getParameter("order_text1");
				}
				
				if(multi.getParameter("ord_text2")!=null){
					ord_text+=","+multi.getParameter("ord_text2");
				}else if(multi.getParameter("order_text2")!=null){
					ord_text=multi.getParameter("order_text2");
				}
				
				if(multi.getParameter("ord_text3")!=null){
					ord_text+=","+multi.getParameter("ord_text3");
				}else if(multi.getParameter("order_text3")!=null){
					ord_text=multi.getParameter("order_text3");
				}
			}
		System.out.println("ord_text:///////"+ord_text);
		rb.setF_rcp_ord(ord_text);
		
		
		//요리순서 이미지 담기
		String f_rcp_ord_img = "";
		
		
			if(multi.getFilesystemName("ord_img_upload0") != null){
				f_rcp_ord_img = multi.getFilesystemName("ord_img_upload0");
			}else if(multi.getParameter("file0")!=null){
				f_rcp_ord_img = multi.getParameter("file0");
				System.out.println(f_rcp_ord_img);
			}
			
			if(multi.getFilesystemName("ord_img_upload1") != null){
				f_rcp_ord_img += ","+multi.getFilesystemName("ord_img_upload1");
			}else if(multi.getParameter("file1")!=null){
				f_rcp_ord_img += ","+multi.getParameter("file1");
				System.out.println(f_rcp_ord_img);
			}
			
			if(multi.getFilesystemName("ord_img_upload2") != null){
				f_rcp_ord_img += ","+multi.getFilesystemName("ord_img_upload2");
			}else if(multi.getParameter("file2")!=null){
				f_rcp_ord_img += ","+multi.getParameter("file2");
				System.out.println(f_rcp_ord_img);
			}
			
			if(multi.getFilesystemName("ord_img_upload3") != null){
				f_rcp_ord_img += ","+multi.getFilesystemName("ord_img_upload3");
			}else if(multi.getParameter("file3")!=null){
				f_rcp_ord_img += ","+multi.getParameter("file3");
				System.out.println(f_rcp_ord_img);
			}
		
		rb.setF_rcp_ord_img(f_rcp_ord_img);
		
		rb.setF_rcp_num(Integer.parseInt(multi.getParameter("f_rcp_num")));
		
	
		
		//번호 담기
		System.out.println("f_rcp_num"+Integer.parseInt(multi.getParameter("f_rcp_num")));
		
		// 이미지만 넘기기(Enumeration)
		/* Enumeration<String> files = multi.getFileNames();
		 String filename="";
		 
         while (files.hasMoreElements()) //java.util.Enumeration 인터페이스에 있는 메소드. 요소가 있는지 없는지 체크함
         {
             String name = (String) files.nextElement(); //Enumeration에 요소가 있을 때 요소를 추출하는 메소드. 오브젝트 타입으로 반환하기 때문에 
                                                         //String 타입으로 변환후 변수에 저장한다.
             filename += multi.getFilesystemName(name)+",";

             System.out.println("파라미터 이름 : " + name + "</br>"); //반복수행이 되면서 변수에 담긴 값들을 하나씩 출력
             System.out.println("저장된 파일 이름 : " + filename + "</br>");
             
         }*/
		
         
		
	
		rb.setF_rcp_hashtag(multi.getParameter("f_rcp_hashtag"));
		
		
		System.out.println("수정할 정보"+rb);
		//DAO 객체 생성 - UpdateRecipe(rb)
		RecipeDAO rdao=new RecipeDAO();
		rdao.UpdateRecipe(rb);
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./RecipeList.re");
		forward.setRedirect(true);
		return forward;
	}

}
