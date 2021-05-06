package com.review.action;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.review.db.ReviewBean;
import com.review.db.ReviewDAO;

public class ReviewAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("M : ReviewAddAction_execute()_호출");

		request.setCharacterEncoding("UTF-8"); // 한글처리

		HttpSession session = request.getSession(); // 세션 id

		String id = (String) session.getAttribute("f_mem_id");
		String nick = (String) session.getAttribute("f_mem_nick");

		ServletContext ctx = request.getServletContext(); // 현재 프로젝트 정보 들어있는 객체
															// 생성
		String realPath = ctx.getRealPath("/upload");
		System.out.println("M : 파일이 실제로 저장되는 위치 : " + realPath);

		int maxSize = 10 * 1024 * 1024; // 10MB

		MultipartRequest multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		System.out.println("M : 파일 업로드 완료");

		ReviewBean rb = new ReviewBean();
		rb.setF_grv_content(multi.getParameter("rv_content"));

		String rv_image = "";
		Enumeration<String> filesArray = multi.getFileNames();
		while (filesArray.hasMoreElements()) {
			String name = filesArray.nextElement();
			System.out.println(multi.getFilesystemName(name));
			if (multi.getFilesystemName(name) != null) {
				rv_image += multi.getFilesystemName(name) + ",";
			}
		}

		if (rv_image != null) {
			rb.setF_grv_img(rv_image + "");
		}

		rb.setF_grv_mem_id(id);
		rb.setF_grv_gr_num(Integer.parseInt(multi.getParameter("rv_gr_num")));
		rb.setF_grv_star(multi.getParameter("rv_rating"));
		rb.setF_grv_mem_nick(nick);

		System.out.println("rb:" + rb);

		ReviewDAO rdao = new ReviewDAO();
		rdao.insertReview(rb);
		rdao.ratingAvg(rb.getF_grv_gr_num());

		ActionForward forward = new ActionForward();
		forward.setPath("./GoodResContentAction.gr?num=" + multi.getParameter("rv_gr_num"));
		forward.setRedirect(true);
		return forward;
	}
	
}
