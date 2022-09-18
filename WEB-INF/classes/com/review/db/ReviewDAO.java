package com.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {
	// DB - f_member 테이블의 모든 처리 
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";		
	
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
			if(rs != null){ rs.close(); }
			if(pstmt != null){ pstmt.close(); }
			if(con != null){ con.close(); }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	// getReviewCount(gr_num)
	public int getReviewCount(int gr_num){
	    int cnt = 0;
	    
	    try {
	      con = getCon();
	      sql = "select count(*) from f_grreview where f_grv_gr_num=?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setInt(1, gr_num);
	      rs = pstmt.executeQuery();
	      if(rs.next()){
	        cnt = rs.getInt(1);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeDB();
	    }
	    
	    return cnt;
	  }
	// getReviewCount(gr_num)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// getReviewList(goodres_num, startRow, pageSize)
	public ArrayList getReviewList(int goodres_num, int startRow, int pageSize) {
		ArrayList reviewList = new ArrayList();

		try {
			con = getCon();
			sql = "select * from f_grreview where f_grv_gr_num=? order by f_grv_num desc limit ?, ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, goodres_num);
			pstmt.setInt(2, startRow - 1);
			pstmt.setInt(3, pageSize);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewBean rb = new ReviewBean();
				
				rb.setF_grv_content(rs.getString("f_grv_content"));
				rb.setF_grv_date(rs.getDate("f_grv_date"));
				rb.setF_grv_gr_num(rs.getInt("f_grv_gr_num"));
				rb.setF_grv_img(rs.getString("f_grv_img"));
				rb.setF_grv_mem_id(rs.getString("f_grv_mem_id"));
				rb.setF_grv_mem_nick(rs.getString("f_grv_mem_nick"));
				rb.setF_grv_num(rs.getInt("f_grv_num"));
				rb.setF_grv_star(rs.getString("f_grv_star"));
				
				reviewList.add(rb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return reviewList;
	}
	// getReviewList(goodres_num, startRow, pageSize)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// insertReview(rb)
	public void insertReview(ReviewBean rb) {
		
	    int num = 0;
	    try {
	      con = getCon();
	      sql = "select max(f_grv_num) from f_grreview";	      
	      pstmt = con.prepareStatement(sql);
	      rs = pstmt.executeQuery();
	      if(rs.next()) {
	    	  num = rs.getInt(1)+1;
	      }
	      
	      System.out.println("num>>>>>>>>>>" + num);
	      
	      sql = "insert into f_grreview(f_grv_mem_id, f_grv_mem_nick, f_grv_date, f_grv_img, f_grv_star, f_grv_content, f_grv_num, f_grv_gr_num) "
	          + "values (?, ?, now(), ?, ?, ?, ?, ?)";
	      
	      pstmt = con.prepareStatement(sql);
	      
	      pstmt.setString(1, rb.getF_grv_mem_id());
	      pstmt.setString(2, rb.getF_grv_mem_nick());	      
	      pstmt.setString(3, rb.getF_grv_img());
	      pstmt.setString(4, rb.getF_grv_star());
	      pstmt.setString(5, rb.getF_grv_content());
	      pstmt.setInt(6, num);
	      pstmt.setInt(7, rb.getF_grv_gr_num());	      
	      
	      pstmt.executeUpdate();
	      
	      // 맛집 리뷰 카운터
	      sql = "update f_goodres set f_gr_rv_cnt = f_gr_rv_cnt + 1 where f_gr_num = ?";
	      
	      pstmt = con.prepareStatement(sql);
	      
	      pstmt.setInt(1, rb.getF_grv_gr_num());
	      
	      pstmt.executeUpdate();
	      
	      System.out.println("DAO : 리뷰 DB 저장 완료");
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      closeDB();
	    }
	  }
	// insertReview(rb)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// ratingAvg(rb.getF_grv_gr_num())
	public void ratingAvg(int gr_num) {
		String avgNum;
		try {
			con = getCon();
			sql = "select avg(f_grv_star) from f_grreview group by f_grv_gr_num having f_grv_gr_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gr_num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				avgNum = rs.getString(1);
				sql = "update f_goodres set f_gr_starAvg=? where f_gr_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, avgNum);
				pstmt.setInt(2, gr_num);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// ratingAvg(rb.getF_grv_gr_num())
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// searchGrNum(id)
	public List<Integer> searchGrNum(String id){
	  List<Integer> GrNum = new ArrayList<Integer>();
	  int count = 0;
	  
	  try {
      con = getCon();
      sql = "select f_grv_gr_num from f_grreview where f_grv_mem_id=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      while(rs.next()){
        System.out.println("GrNum"+(count+1)+":"+rs.getInt(1));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
	  
	  return GrNum;
	}
	// searchGrNum(id)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// reviewDelete(grv_num)
	public void reviewDelete(int grv_num) {
	  try {
      con = getCon();
      sql = "delete from f_grreview where f_grv_num=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, grv_num);
      pstmt.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
	}
	// reviewDelete(grv_num)
	////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////////
	// myReviewList(id)
	public List<ReviewBean> myReviewList(String id){
	  List<ReviewBean> myReviewList = new ArrayList<ReviewBean>();
	  
	  try {
      con = getCon();
      sql = "select * from f_grreview where f_grv_mem_id=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      while(rs.next()){
        ReviewBean rb = new ReviewBean();
        
        rb.setF_grv_content(rs.getString("f_grv_content"));
        rb.setF_grv_date(rs.getDate("f_grv_date"));
        rb.setF_grv_gr_num(rs.getInt("f_grv_gr_num"));
        rb.setF_grv_img(rs.getString("f_grv_img"));
        rb.setF_grv_mem_id(rs.getString("f_grv_mem_id"));
        rb.setF_grv_mem_nick(rs.getString("f_grv_mem_nick"));
        rb.setF_grv_num(rs.getInt("f_grv_num"));
        rb.setF_grv_star(rs.getString("f_grv_star"));
        
        myReviewList.add(rb);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
	  
	  return myReviewList;
	}
	// myReviewList(id)
	////////////////////////////////////////////////////////////////////////////////////
	
}
