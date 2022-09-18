package com.admin.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.order.db.OrderBean;

public class AdminOrderDAO {

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비 연결 - 커넥션 풀 (라이브러리 설치 해야함)
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/foodDB");
		con = ds.getConnection();

		System.out.println("DAO : 디비 연결 성공! -> " + con);

		return con;
	}
	// 디비 연결 끝

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
	// 자원해제

	///////////////////////////////////////////////////////////
	
	
	// getAdminOrderList()
	public List getAdminOrderList(){
		
		List orderAllList = new ArrayList();
	
		try {
			// 1.2 디비 연결
			con = getCon();
			
			// 3. sql (주문정보) / 그룹 o_trade_num, (내림차순 정렬)
			// => sum(o_sum_money) as o_sum_money
			
			sql = "select f_or_trade_num,f_or_gs_name,f_or_bas_amount,"
					+ "sum(f_or_sum) as f_or_sum,"
					+ "f_or_trans_num,f_or_date,f_or_status,f_or_trade_type,f_or_mem_id from f_order "
					+ "group by f_or_trade_num order by f_or_trade_num desc";
			
			pstmt = con.prepareStatement(sql);
			
			//4. sql 실행
			rs = pstmt.executeQuery();
			
			//5. 데이터 처리
			while(rs.next()){
				OrderBean ob = new OrderBean();
				
				ob.setF_or_date(rs.getDate("f_or_date"));
				ob.setF_or_bas_amount(rs.getInt("f_or_bas_amount"));
				ob.setF_or_gs_name(rs.getString("f_or_gs_name"));
				ob.setF_or_trade_num(rs.getString("f_or_trade_num"));
				ob.setF_or_trans_num(rs.getString("f_or_trans_num"));
				ob.setF_or_sum(rs.getInt("f_or_sum"));
				ob.setF_or_status(rs.getInt("f_or_status"));
				ob.setF_or_trade_type(rs.getString("f_or_trade_type"));
				ob.setF_or_mem_id(rs.getString("f_or_mem_id"));
				
				orderAllList.add(ob);
			}//while
			
			System.out.println("DAO : 주문 목록 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return orderAllList;
	}
	
	
	// getAdminOrderList()
	
	
	// getAdminOrderList(trade_num)
	public List getAdminOrderList(String trade_num){
		List adminOrderDetail = new ArrayList();
	
		try {
		
			// 1.2. 디비 연결
			con = getCon();
		
			// 3. sql
			sql = "select * from f_order where f_or_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, trade_num);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()){
				OrderBean ob = new OrderBean();
				
				ob.setF_or_date(rs.getDate("f_or_date"));
				ob.setF_or_bas_amount(rs.getInt("f_or_bas_amount"));
				ob.setF_or_gs_name(rs.getString("f_or_gs_name"));
				ob.setF_or_trade_num(rs.getString("f_or_trade_num"));
				ob.setF_or_trans_num(rs.getString("f_or_trans_num"));
				ob.setF_or_sum(rs.getInt("f_or_sum"));
				ob.setF_or_status(rs.getInt("f_or_status"));
				ob.setF_or_trade_type(rs.getString("f_or_trade_type"));
				ob.setF_or_mem_id(rs.getString("f_or_mem_id"));
				
				ob.setF_or_rec_name(rs.getString("f_or_rec_name"));
				ob.setF_or_rec_phone(rs.getString("f_or_rec_phone"));
				ob.setF_or_rec_postcode(rs.getString("f_or_rec_postcode"));
				ob.setF_or_rec_address(rs.getString("f_or_rec_address"));
				ob.setF_or_rec_detailAddress(rs.getString("f_or_rec_detailAddress"));
				ob.setF_or_rec_extraAddress(rs.getString("f_or_rec_extraAddress"));
				ob.setF_or_memo(rs.getString("f_or_memo"));

				ob.setF_or_trade_payer(rs.getString("f_or_trade_payer"));
				
				adminOrderDetail.add(ob);
			} // while
			
			System.out.println("DAO : 관리자 주문 디테일 페이지 정보 저장");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	
		return adminOrderDetail;
	}	
	// getAdminOrderList(trade_num)
	
	
	// updateOrder(ob)
	public void updateOrder(OrderBean ob){
				
		try {
		
			// 1.2. 디비 연결
			con = getCon();
			
			// 3. sql (주문 번호에 해당하는 주문의 주문상태와 운송장 번호를 수정)
			sql = "update f_order set f_or_status=?,f_or_trans_num=? "
					+ "where f_or_trade_num=?";
			
			pstmt = con.prepareStatement(sql);
						
			pstmt.setInt(1, ob.getF_or_status());
			pstmt.setString(2, ob.getF_or_trans_num());
			pstmt.setString(3, ob.getF_or_trade_num());
			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 주문번호에 해당하는 주문 상태 및 운송장번호 수정 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// updateOrder(ob)
	
	
	
	
}
