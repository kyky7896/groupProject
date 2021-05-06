package com.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.admin.goods.db.AdminGoodsDAO;
import com.admin.goods.db.GoodsBean;
import com.goods.db.GoodsDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AdminGoodsAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/* 작업 내용 : 사용자가 등록한 상품 데이터를 디비에 업로드한다 */
		
		System.out.println("[M] : AdminGoodsAddAction_execute() 호출됨");
		
		// 관리자 세션 제어
			/*String id = (String)request.getSession().getAttribute("id");
			
			ActionForward forward = new ActionForward();
			if(id == null || !id.equals("admin")){
				forward.setPath("./MemberLogin.me");
				forward.setRedirect(true);
				return forward;
			}*/
			
		// 한글 처리
			request.setCharacterEncoding("UTF-8");
			
		// *넘어오는 데이터중 업로드 해야 할 파일이 있다면 => 사용자가 업로드한 파일을 서버 폴더에 올려놓는 작업부터 처리
			
		//실제 파일이 저장되는 위치 정보 저장
			ServletContext ctx = request.getServletContext(); // 현재 프로젝트 정보 들어있는 객체 생성
			String realPath = ctx.getRealPath("/upload");
			System.out.println("[M] : 파일이 실제로 저장되는 위치 =>"+realPath);
			
		//업로드 가능한 파일 용량 설정
			int maxSize = 10*1024*1024; // 10MB
			
		//객체 생성하여 파일 업로드 하기
			MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"UTF-8",new DefaultFileRenamePolicy());
			System.out.println("[M] : 파일 업로드 완료!!");
			
		// 파라미터로 넘어온 데이터(name값) 저장(goodsBean)
			GoodsBean gb = new GoodsBean();
			gb.setF_gs_cate(multi.getParameter("f_gs_cate"));
			gb.setF_gs_name(multi.getParameter("f_gs_name"));
			gb.setF_gs_price(Integer.parseInt(multi.getParameter("f_gs_price")));
			gb.setF_gs_amount(Integer.parseInt(multi.getParameter("f_gs_amount")));
			gb.setF_gs_msg(multi.getParameter("f_gs_msg"));
			String f_gs_img = multi.getFilesystemName("file1")+","
					+multi.getFilesystemName("file2")+","
					+multi.getFilesystemName("file3")+","
					+multi.getFilesystemName("file4");
			gb.setF_gs_img(f_gs_img);
			gb.setF_gs_hit(0); // 기본값 0 => 인기상품일 때 1로 변경
			// f_gs_num , f_gs_date 는 쿼리 작성시 데이터 입력할 것
			
		// 디비 작업 해주는 객체 생성 -> 관련 메서드 호출
			new AdminGoodsDAO().insertGoods(gb);
		// 페이지 이동 
			ActionForward forward = new ActionForward();
			forward.setPath("./AdminGoodsList.ag");
			forward.setRedirect(true);
			return forward;
	}

}
