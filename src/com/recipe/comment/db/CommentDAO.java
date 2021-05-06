package com.recipe.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class CommentDAO {
	
	// 메서드 작업에 필요한 참조변수 준비 
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String SQL = "";
			
				
		private Connection getCon() throws Exception{ 
			Context initCTX = new InitialContext(); 
			DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/foodDB"); //java:comp/env/ : 고정 + context.xml 의 name 정보 붙여넣기
			con = ds.getConnection();
				
			System.out.println("[goodsDAO] : 디비 연결 성공! >> "+con);
			return con;
		}//getCon()
				
				
		private void closeDB(){
			try {
				if(rs != null ) rs.close();
				if(pstmt != null ) pstmt.close();
				if(con != null ) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}// closeDB()
		
	//--------------------------------------------------------------------- int InsertComment(CommentBean cb)
		
			/* 디비에 댓글 insert */
		
			public int InsertComment(CommentBean cb){
			
			int f_rcpc_no = 0;
			int result = 0;
			
			try {
				con = getCon();
				
			//(디비에 데이터 넣기 전!!) 댓글 번호 계산 !! (select)
			SQL = "select max(f_rcpc_no) from f_rcpcomment";
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				f_rcpc_no = rs.getInt(1)+1; // rs.getInt(1)의 값이 null이라면(=게시글 없음) 0을 반환한다.
			}
			
		// 디비에 상품 정보 저장하기(insert into)
				SQL = "insert into f_rcpcomment "
						+ " values(?,?,now(),?,?,?,?,?,?,?,?)";
				
				pstmt = con.prepareStatement(SQL);
				
				pstmt.setInt(1, f_rcpc_no);
				pstmt.setInt(2, cb.getF_rcpc_rcp_num());
				pstmt.setString(3, cb.getF_rcpc_mem_id());
				pstmt.setString(4, cb.getF_rcpc_mem_nick()); // => 값 가져와야 함
				pstmt.setString(5, cb.getF_rcpc_content());
				pstmt.setInt(6, f_rcpc_no); // re_ref 답변글 그룹 = 일반글 글번호랑 동일
				pstmt.setInt(7, 0); // re_lev 답변글 들여쓰기 (깊이정도)
				pstmt.setInt(8, 0); // re_seq 답변글 순서 
				pstmt.setString(9, cb.getF_rcpc_ip());
				pstmt.setString(10, cb.getF_rcpc_img());
				
				result = pstmt.executeUpdate();
				
				System.out.println("[DAO] 디비에 댓글넣기 성공====");
				
			} catch (Exception e) {
				System.out.println("[DAO] 디비에 댓글넣기 쿼리 실패>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}
			
			return result;	
		}
	//--------------------------------------------------------------------- JSONArray getCommentJ(int f_rcpc_rcp_num)

			/*  댓글 리스트 가져오기 - 리턴 : jsonArray타입 객체 */
			
		public JSONArray getCommentJ(int f_rcpc_rcp_num){
			
			JSONArray commentList = null;
			
			try {
				con = getCon();
				
				// 일반댓글 - 최신순 , 대댓글내 정렬 순서-오름차순(등록순)
				SQL = "select * from f_rcpcomment where f_rcpc_rcp_num=? order by f_rcpc_ref desc, f_rcpc_seq asc"; 
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, f_rcpc_rcp_num);
				rs = pstmt.executeQuery();
				
			// 배열객체 생성
				  
			  	commentList = new JSONArray();
			  	
			  	while(rs.next()){
				  // 댓글 1개의 정보를 하나씩 JSON 객체에 담기
				  	JSONObject cb = new JSONObject();
				  
				  	cb.put("f_rcpc_no", rs.getInt("f_rcpc_no"));//글번호
				  	cb.put("f_rcpc_rcp_num", rs.getInt("f_rcpc_rcp_num"));
				  	cb.put("f_rcpc_date", rs.getDate("f_rcpc_date")+"");//★★Json은 Date타입의 데이터를 인식하지 못하기에 String으로 반드시 변경해야 함!!
				  	cb.put("f_rcpc_mem_id", rs.getString("f_rcpc_mem_id"));//아이디
				 	cb.put("f_rcpc_mem_nick", rs.getString("f_rcpc_mem_nick"));//닉네임
				 	cb.put("f_rcpc_content", rs.getString("f_rcpc_content")); // 내용
				 	cb.put("f_rcpc_ref", rs.getInt("f_rcpc_ref"));//글번호
				 	cb.put("f_rcpc_lev", rs.getInt("f_rcpc_lev"));//대댓글 깊이 
				 	cb.put("f_rcpc_seq", rs.getInt("f_rcpc_seq"));//대댓글 내 순서
				  	commentList.add(cb);
				  	
			  }
						
				System.out.println("DAO : 댓글 리스트에 담기 완료~~");
				System.out.println("DAO : "+commentList);
				
				
			} catch (Exception e) {
				System.out.println("DAO : 댓글 리스트에 담기 쿼리 실패>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return commentList;
		}
	//--------------------------------------------------------------------- int deleteComment(CommentBean cb)
		
				/* 댓글 삭제 */
		
		public void deleteComment(CommentBean cb){
			
			int data = 0;
			
			try {
				con = getCon();
				
				SQL = "delete from f_rcpcomment "
						+ "where f_rcpc_no=? ";
				
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, cb.getF_rcpc_no());
				
				pstmt.executeUpdate();

				System.out.println("[DAO] 댓글 삭제 완료!");

				
			} catch (Exception e) {
				System.out.println("[DAO] 댓글 삭제 쿼리 실행 실패>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}
	//--------------------------------------------------------------------- int updateComment(CommentBean cb)
		
			/* 댓글 내용 수정 */
		
		public int updateComment(CommentBean cb){
			
			int result = 0;
			
			try {
				con = getCon();
				
				SQL = "update f_rcpcomment"
						+ " set f_rcpc_content=?"
						+ " where f_rcpc_no=? ";
				
				pstmt = con.prepareStatement(SQL);
				pstmt.setString(1, cb.getF_rcpc_content());
				pstmt.setInt(2, cb.getF_rcpc_no());
				
				result = pstmt.executeUpdate();
				
				System.out.println("[DAO] 디비에 댓글 수정 완료!!");
				
			} catch (Exception e) {
				System.out.println("[DAO] 디비에 댓글 수정 쿼리 실패>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}
			return result;
		}
	//--------------------------------------------------------------------- test
		
		public int countComment(int f_rcpc_rcp_num){
			
			int result = 0;
			
			try {
				con = getCon();
				
				SQL = "select count(*) from f_rcpcomment where f_rcpc_rcp_num=?";
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, f_rcpc_rcp_num);
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					result = rs.getInt("count(*)");
				}
				System.out.println("result>>"+result);
				System.out.println("[DAO] 댓글 갯수 담기 완료!!===");
			} catch (Exception e) {
				System.out.println("[DAO] 댓글 갯수 담기 쿼리 실패>>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}
			
			return result;
		}

	//----------------------------------------------------------- test
		
		public void insertReComment(CommentBean cb){
			int f_rcpc_no = 0;
			
		try {
			con = getCon();
				
		// 대댓글 번호 계산(f_rcpc_no)
			SQL = "select max(f_rcpc_no) from f_rcpcomment";
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery(); // rs == 가장 마지막 쓴 글의 번호 들어 있음
				
			if(rs.next()){
				f_rcpc_no = rs.getInt("max(f_rcpc_no)")+1;
				  // num = rs.getInt(1)+1;
				System.out.println("dao : 답글 번호 확인 ->"+f_rcpc_no);
			}
				
		//////////////////////////////////////
				
		// 답글 순서 재배치 (원하는 순서로 보이기 위함)
			// re_ref 값이 같은 그룹 중, re_seq 기존의 값보다 큰 값이 있을 때 1 증가 시킴(단,첫 답글 쓸때는 update구문 실행되지는 않음.).
			SQL = "update f_rcpcomment set f_rcpc_seq=f_rcpc_seq+1 "
					+ "where f_rcpc_ref=? and f_rcpc_seq>?"; // 원글의 seq(?) 보다 큰 seq가 있으면 seq를 1증가 시켜라.
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, cb.getF_rcpc_ref());
			pstmt.setInt(2, cb.getF_rcpc_seq());
			pstmt.executeUpdate();
				
			System.out.println("dao : 답글 순서 재배치!");
				
		//////////////////////////////////////		
				
		// 답글(대댓글) 넣기
				
			SQL = "insert into f_rcpcomment "
					+ " values(?,?,now(),?,?,?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(SQL);
				
			pstmt.setInt(1, f_rcpc_no);
			pstmt.setInt(2, cb.getF_rcpc_rcp_num());
			pstmt.setString(3, cb.getF_rcpc_mem_id());
			pstmt.setString(4, cb.getF_rcpc_mem_nick()); // => 값 가져와야 함
			pstmt.setString(5, cb.getF_rcpc_content());
			pstmt.setInt(6, cb.getF_rcpc_ref()); // 답글 ref = 일반글(원글)의 ref 값 사용 , pro페이지로 올 떄 ref 값 주소줄에 가지고 들고왓고 자바빈에 저장했음
			pstmt.setInt(7, cb.getF_rcpc_lev()+1); // 원글의 답글이니까 +1 증가 시켜서 반영해 줘야 함.
			pstmt.setInt(8, cb.getF_rcpc_seq()+1); // 답글 seq : 순서 1 증가
			pstmt.setString(9, cb.getF_rcpc_ip());
			pstmt.setString(10, cb.getF_rcpc_img());	
				
			pstmt.executeUpdate();
				
			System.out.println("[DAO] 대댓글 저장 완료!!!");
			
			} catch (Exception e) {
				System.out.println("[DAO] 대댓글 저장 실패>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}
		}
	//------------------------------------------------------------- test
		
		public JSONArray getReCommentList(int f_rcpc_ref){
			
			JSONArray reCommentList = null;
			
			try {
				con = getCon();
				
				SQL = "select * from f_rcpcomment where f_rcpc_ref=? and f_rcpc_lev>0 ";
				pstmt = con.prepareStatement(SQL);
				pstmt.setInt(1, f_rcpc_ref);
				rs = pstmt.executeQuery();
				
				// 배열객체 생성
				reCommentList = new JSONArray();
			  	
			  	while(rs.next()){
				  
				  // 댓글 1개의 정보를 하나씩 JSON 객체에 담기
				  	JSONObject cb = new JSONObject();
				  
				  	cb.put("f_rcpc_rcp_num", rs.getInt("f_rcpc_rcp_num"));//댓글번호
				  	cb.put("f_rcpc_no", rs.getInt("f_rcpc_no"));//글번호
				  	cb.put("f_rcpc_date", rs.getDate("f_rcpc_date")+"");//★★Json은 Date타입의 데이터를 인식하지 못하기에 String으로 반드시 변경해야 함!!
				  	cb.put("f_rcpc_mem_id", rs.getString("f_rcpc_mem_id"));//아이디
				 	cb.put("f_rcpc_mem_nick", rs.getString("f_rcpc_mem_nick"));//닉네임
				 	cb.put("f_rcpc_content", rs.getString("f_rcpc_content")); // 내용
				 	cb.put("f_rcpc_ref", rs.getInt("f_rcpc_ref"));//글번호
				 	cb.put("f_rcpc_lev", rs.getInt("f_rcpc_lev"));//대댓글 깊이 
				 	cb.put("f_rcpc_seq", rs.getInt("f_rcpc_seq"));//대댓글 내 순서
				 	cb.put("f_rcpc_mem_id", rs.getString("f_rcpc_mem_id"));
				 	reCommentList.add(cb);
				 	
			  	}//while()
			  	
			  	System.out.println("[DAO] 대댓글 리스트 담기 완료!!");

			} catch (Exception e) {
				System.out.println("[DAO] 대댓글 리스트 담기 쿼리 실행 실패>>"+e.getMessage());
				e.printStackTrace();
			}finally{
				closeDB();
			}

			return reCommentList;
		}
	
	
	
}
