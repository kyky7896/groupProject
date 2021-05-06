package com.recipe.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.recipe.board.action.RecipeListAction;

public class RecipeDAO {
	

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결 - 커넥션풀
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/foodDB");
		con = ds.getConnection();

		System.out.println("DAO : 디비 연결 성공! -> " + con);

		return con;
	}

	// 자원해제
	public void closeDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
// ★(수정한거)------------------------------------------------------------------- void InsertRecipe(RecipeBean rb)
	
	public void InsertRecipe(RecipeBean rb){
		int f_rcp_num = 0;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3.sql생성 & pstmt객체
			// 상품번호 계산 (select)
			sql = "select max(f_rcp_num) from f_rcp";
			pstmt = con.prepareStatement(sql);
			
			// 4.sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				f_rcp_num = rs.getInt(1)+1;
			}
			System.out.println("DAO : 상품번호 생성 - "+f_rcp_num);
			
/* 레시피 등록 쿼리 변경 */
			
			// 상품등록 (insert)
			sql = "insert into f_rcp "
					+ "values (?,?,?,?,?,?,?,now(),?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,f_rcp_num);//글번호
			pstmt.setString(2, rb.getF_rcp_id());//작성자id
			pstmt.setString(3, rb.getF_rcp_nick());//닉네임
			pstmt.setString(4, rb.getF_rcp_title());//글제목
			pstmt.setString(5, rb.getF_rcp_content());//요리설명
			pstmt.setInt(6, 0);//조회수
			pstmt.setString(7, rb.getF_rcp_th_img());//대표이미지
			//올린날짜
			pstmt.setString(8, rb.getF_rcp_cate());//카테고리
			pstmt.setString(9, rb.getF_rcp_ingre());//재료
			pstmt.setString(10, rb.getF_rcp_ord());//요리순서 내용
			pstmt.setString(11, rb.getF_rcp_ord_img());//요리순서 이미지
			pstmt.setString(12, rb.getF_rcp_hashtag());//해시태그
			pstmt.executeUpdate();
			
/* 레시피 등록 쿼리 변경 */
			
			System.out.println("DAO : 관리자 상품등록완료! ");
			
		} catch (Exception e) {
			System.out.println("DAO : 관리자 상품등록 쿼리 실행 실패>> "+e.getMessage());
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
	}
// ------------------------------------------------------------------- RecipeBean getRecipeDetail(int f_rcp_num)
	
	public RecipeBean getRecipeDetail(int f_rcp_num){
		
		/* 특정 레시피 상세 데이터 빈에 저장해서 리턴 하는 작업 */
		
		RecipeBean rb = null;
		
		try {
			con = getCon();
			
			sql = "select * from f_rcp where f_rcp_num=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, f_rcp_num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				rb = new RecipeBean();
				
				rb.setF_rcp_num(rs.getInt("f_rcp_num"));
				rb.setF_rcp_id(rs.getString("f_rcp_id"));
				rb.setF_rcp_title(rs.getString("f_rcp_title"));
				rb.setF_rcp_content(rs.getString("f_rcp_content"));
				rb.setF_rcp_count(rs.getInt("f_rcp_count"));
				rb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
				rb.setF_rcp_date(rs.getDate("f_rcp_date"));
				rb.setF_rcp_cate(rs.getString("f_rcp_cate"));
				rb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
				rb.setF_rcp_ord(rs.getString("f_rcp_ord"));
				rb.setF_rcp_ord_img(rs.getString("f_rcp_ord_img"));
				rb.setF_rcp_hashtag(rs.getString("f_rcp_hashtag"));
				
			}
			
			
			System.out.println("[DAO]레피시 정보>>"+rb);
			System.out.println("[DAO] 디비에서 특성 레시피 게시글 빈에 담기 성공----");
			
		} catch (Exception e) {
			System.out.println("[DAO] 디비에서 특성 레시피 게시글 빈에 담는 쿼리 실행 실패>>"+e.getMessage());
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return rb;
	}
// (★추가한거)------------------------------------------------------------------- void updateReadNumber(int f_rcp_num)
	
	public void updateReadNumber(int f_rcp_num){
		
		/* 클릭한 게시글 조회수 올리는 작업 */
		
		try {
			con = getCon();
			
			sql = "update f_rcp set f_rcp_count=f_rcp_count+1 where f_rcp_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, f_rcp_num);
			pstmt.executeUpdate();
			
			System.out.println("[DAO] 특정 게시글의 조회수 올리기 완료!!");
			
		} catch (Exception e) {
			System.out.println("[DAO] 특정 게시글의 조회수 올리기 쿼리 실행 실패>>"+e.getMessage());
			e.printStackTrace();
		}finally{
			closeDB();
		}
		
	}
//-------------------------------------------------- List getRecipeList(String item)
	
	public List getRecipeList(String item){
		List recipeList = new ArrayList();
		StringBuffer SQL = new StringBuffer();
		try {
			
			//1.2 디비연결
			con=getCon();
			
			//3.sql 작성 & pstmt객체
			SQL.append("select * from f_rcp");
			
			if(item.equals("all")){
				SQL.append(" order by f_rcp_num desc");
			}else{ //category
				SQL.append(" where f_rcp_cate=? order by f_rcp_num desc");
			}
			
		pstmt=con.prepareStatement(SQL.toString());
		
		if(item.equals("all")){
			
		}else{ //category
			pstmt.setString(1, item);
		}
		
		rs=pstmt.executeQuery();
		
		while(rs.next()){
			//상품 정보 저장(RecipeBean)
			RecipeBean rb=new RecipeBean();
			
			rb.setF_rcp_num(rs.getInt("f_rcp_num"));
			rb.setF_rcp_id(rs.getString("f_rcp_id"));
			rb.setF_rcp_nick(rs.getString("f_rcp_nick"));
			rb.setF_rcp_title(rs.getString("f_rcp_title"));
			rb.setF_rcp_content(rs.getString("f_rcp_content"));
			rb.setF_rcp_count(rs.getInt("f_rcp_count"));
			rb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
			rb.setF_rcp_date(rs.getDate("f_rcp_date"));
			rb.setF_rcp_cate(rs.getString("f_rcp_cate"));
			rb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
			rb.setF_rcp_ord(rs.getString("f_rcp_ord"));
			rb.setF_rcp_ord_img(rs.getString("f_rcp_ord_img"));
			rb.setF_rcp_hashtag(rs.getString("f_rcp_hashtag"));
			
			recipeList.add(rb);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return recipeList;
	}
// -------------------------------------------------------------- test
	
	public List<RecipeBean> getSearch(String q){
		
		List<RecipeBean> recipeList = new ArrayList<RecipeBean>();
		
		try {
			con = getCon();
			
			sql = "select * from f_rcp where f_rcp_title like ? "
					+ "order by f_rcp_date desc;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+q+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				RecipeBean rb = new RecipeBean();
				
				rb.setF_rcp_num(rs.getInt("f_rcp_num"));
				rb.setF_rcp_id(rs.getString("f_rcp_id"));
				rb.setF_rcp_nick(rs.getString("f_rcp_nick"));
				rb.setF_rcp_title(rs.getString("f_rcp_title"));
				rb.setF_rcp_content(rs.getString("f_rcp_content"));
				rb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
				rb.setF_rcp_date(rs.getDate("f_rcp_date"));
				rb.setF_rcp_cate(rs.getString("f_rcp_cate"));
				rb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
				rb.setF_rcp_count(rs.getInt("f_rcp_count"));
				rb.setF_rcp_hashtag(rs.getString("f_rcp_hashtag"));
				
				recipeList.add(rb);
			}
			
			System.out.println("[DAO] 해시태그 레시피 리스트 담기 완료 ");
		} catch (Exception e) {
			System.out.println("[DAO] 레시피 리스트 담기 쿼리 실패>>"+e.getMessage());
			e.printStackTrace();
		}finally{
			closeDB();
		}
		return recipeList;
	}
	// -------------------------------------------------------------- DeleteRecipe
	
		public void DeleteRecipe(int f_rcp_num){
			try {
				// 디비연결
				con=getCon();
				// sql & pstmt 
				sql="delete from f_rcp where f_rcp_num=?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, f_rcp_num);
				// 실행
				pstmt.executeUpdate();
				
				System.out.println("DAO: 글 삭제 완료!");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
	// -------------------------------------------------------------- UpdateRecipe
		public void UpdateRecipe(RecipeBean rb){
			
			// 디비연결
			try {
				con=getCon();
				
				sql="update f_rcp set f_rcp_title=?, f_rcp_content=?, f_rcp_th_img=?, f_rcp_cate=?, "
						+ "f_rcp_ingre=?, f_rcp_ord=?, f_rcp_ord_img=?, f_rcp_hashtag=?"
						+ "where f_rcp_num=?";
				pstmt=con.prepareStatement(sql);
				
				pstmt.setString(1, rb.getF_rcp_title());
				pstmt.setString(2, rb.getF_rcp_content());
				pstmt.setString(3, rb.getF_rcp_th_img());
				pstmt.setString(4, rb.getF_rcp_cate());
				pstmt.setString(5, rb.getF_rcp_ingre());
				pstmt.setString(6, rb.getF_rcp_ord());
				pstmt.setString(7, rb.getF_rcp_ord_img());
				pstmt.setString(8, rb.getF_rcp_hashtag());
				pstmt.setInt(9, rb.getF_rcp_num());
				pstmt.executeUpdate();
				
				System.out.println("DAO : 수정완료");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			
		}
		// -------------------------------------------------------------- GetRecipe
		public RecipeBean GetRecipe(int f_rcp_num){
			RecipeBean rb=null;
			try {
				// 디비연결
				con=getCon();
				// sql & pstmt
				sql="select * from f_rcp where f_rcp_num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, f_rcp_num);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					rb=new RecipeBean();
					rb.setF_rcp_cate(rs.getString("f_rcp_cate"));
					rb.setF_rcp_content(rs.getString("f_rcp_content"));
					rb.setF_rcp_count(rs.getInt("f_rcp_count"));
					rb.setF_rcp_date(rs.getDate("f_rcp_date"));
					rb.setF_rcp_hashtag(rs.getString("f_rcp_hashtag"));
					rb.setF_rcp_id(rs.getString("f_rcp_id"));
					rb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
					rb.setF_rcp_nick(rs.getString("f_rcp_nick"));
					rb.setF_rcp_num(rs.getInt("f_rcp_num"));
					rb.setF_rcp_ord(rs.getString("f_rcp_ord"));
					rb.setF_rcp_ord_img(rs.getString("f_rcp_ord_img"));
					rb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
					rb.setF_rcp_title(rs.getString("f_rcp_title"));
					
				}
				
				System.out.println("DAO : 레시피 정보 저장완료 "+rb);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return rb;
		}
////////★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★마이페이지레시피리스트★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★
		public List<RecipeBean> MyRecipeList(String id){
			List recipeList = new ArrayList();
			StringBuffer SQL = new StringBuffer();
			try {
				con = getCon();
				sql="select * from f_rcp where f_rcp_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					//상품 정보 저장(RecipeBean)
					RecipeBean rb=new RecipeBean();
					
					rb.setF_rcp_num(rs.getInt("f_rcp_num"));
					rb.setF_rcp_id(rs.getString("f_rcp_id"));
					rb.setF_rcp_nick(rs.getString("f_rcp_nick"));
					rb.setF_rcp_title(rs.getString("f_rcp_title"));
					rb.setF_rcp_content(rs.getString("f_rcp_content"));
					rb.setF_rcp_count(rs.getInt("f_rcp_count"));
					rb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
					rb.setF_rcp_date(rs.getDate("f_rcp_date"));
					rb.setF_rcp_cate(rs.getString("f_rcp_cate"));
					rb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
					rb.setF_rcp_ord(rs.getString("f_rcp_ord"));
					rb.setF_rcp_ord_img(rs.getString("f_rcp_ord_img"));
					rb.setF_rcp_hashtag(rs.getString("f_rcp_hashtag"));
					
					recipeList.add(rb);
					System.out.println("DAO >> MyRecipeList정보"+rb);
				}
				
			} catch (Exception e) {
				System.out.println("DAO >> MyRecipeList 정보 담기 실패 @@");
				e.printStackTrace();
			}finally{
				closeDB();
			}
			
			return recipeList;
		}
	///////////추가 //getListCount()/////List에 글이 몇개인지 체크하고 리턴
		public int getListCount(String item){
			int cnt=0;
			//DB에서 글이 몇개 인지 체크하고 리턴
			try {
				con = getCon();
				
				sql="select count(*) from f_rcp where f_rcp_cate=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, item);
				rs = pstmt.executeQuery();
				if(rs.next()){
					cnt = rs.getInt(1);
				}System.out.println("DAO : 글 갯수 조회 >>"+cnt);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DAO : 글 갯수 조회 실패 !");
			}finally{
				closeDB();
			}
			return cnt;
			}	
	//-------선택한 카테고리 & 옵션값에 부합되는 상품 정보 가져오기-------------------------------------------------------------
		public List<RecipeBean> getRecipeList(String item,String type){ 
			
			StringBuffer SQL = new StringBuffer();
			RecipeBean rb = null;
			List recipeList = new ArrayList();
			
				try {
					con = getCon();
					
					SQL.append("select * from f_rcp");
					
					if(item.equals("all")){
						if(type.equals("newest")){
							SQL.append(" order by f_rcp_num desc");
						}else if(type.equals("hit")){
							SQL.append(" order by f_rcp_count desc");
						}
					}else{
						if(type.equals("newest")){
							SQL.append(" where f_rcp_cate=? order by f_rcp_num desc");
						}else if(type.equals("hit")){
							SQL.append(" where f_rcp_cate=? order by f_rcp_count desc");
						}
					}
					
					pstmt = con.prepareStatement(SQL.toString());
					
					if(item.equals("all")){
						
					}else{
						pstmt.setString(1, item);
					}
					
					rs = pstmt.executeQuery();
					
				// 디비에서 반환된 값을 자바빈에 저장
					while(rs.next()){
						rb = new RecipeBean();
						
						rb.setF_rcp_num(rs.getInt("f_rcp_num"));
						rb.setF_rcp_id(rs.getString("f_rcp_id"));
						rb.setF_rcp_nick(rs.getString("f_rcp_nick"));
						rb.setF_rcp_title(rs.getString("f_rcp_title"));
						rb.setF_rcp_content(rs.getString("f_rcp_content"));
						rb.setF_rcp_count(rs.getInt("f_rcp_count"));
						rb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
						rb.setF_rcp_date(rs.getDate("f_rcp_date"));
						rb.setF_rcp_cate(rs.getString("f_rcp_cate"));
						rb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
						rb.setF_rcp_ord_img(rs.getString("f_rcp_ord_img"));
						rb.setF_rcp_ord(rs.getString("f_rcp_ord"));
						rb.setF_rcp_hashtag(rs.getString("f_rcp_hashtag"));
						
				// 자바빈 객체들을 list 자료구조에 저장
						recipeList.add(rb); 
					}// while
					
					System.out.println("[RecipeDAO] :  레시피정보 _리스트 타입별 담기!!");
					
				} catch (Exception e) {
					System.out.println("[RecipeDAO] : 레시피_리스트 타입별 담기 실패 >> "+e.getMessage());
					e.printStackTrace();
				}finally{
					closeDB();
				}
		
			// 작업결과인 list 배열 반환하기
				return recipeList;
		}
		// getRcpViewList() 시작 
		public List<RecipeBean> getRcpViewList() {
			List<RecipeBean> rcpViewList = new ArrayList<RecipeBean>();
			RecipeBean rcpb = null;
			
			try {
				con = getCon();

				sql = "select * from f_rcp order by f_rcp_count desc";

				pstmt = con.prepareStatement(sql);		

				rs = pstmt.executeQuery();
				
				// 5. 데이터 처리
				while(rs.next()) {
					rcpb = new RecipeBean();
		
					rcpb.setF_rcp_num(rs.getInt("f_rcp_num"));
					rcpb.setF_rcp_id(rs.getString("f_rcp_id"));
					rcpb.setF_rcp_nick(rs.getString("f_rcp_nick"));
					rcpb.setF_rcp_title(rs.getString("f_rcp_title"));
					rcpb.setF_rcp_content(rs.getString("f_rcp_content"));
					rcpb.setF_rcp_count(rs.getInt("f_rcp_count"));
					rcpb.setF_rcp_th_img(rs.getString("f_rcp_th_img"));
					rcpb.setF_rcp_date(rs.getDate("f_rcp_date"));
					rcpb.setF_rcp_cate(rs.getString("f_rcp_cate"));
					rcpb.setF_rcp_ingre(rs.getString("f_rcp_ingre"));
					rcpb.setF_rcp_ord(rs.getString("f_rcp_ord"));
					rcpb.setF_rcp_ord_img(rs.getString("f_rcp_ord_img"));

					rcpViewList.add(rcpb);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return rcpViewList;
		}
		// getRcpViewList() 끝

	}
