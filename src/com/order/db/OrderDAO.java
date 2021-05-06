package com.order.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.admin.goods.db.GoodsBean;
import com.basket.db.BasketBean;

public class OrderDAO {

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
	
	// addOrder(ob,basketList,goodsList)
	public void addOrder(OrderBean ob,ArrayList basketList,ArrayList goodsList){
		int o_num = 0; //주문번호(일련번호)
		int trade_num = 0; //주문번호 (사용자 확인)
		
		// 주문번호 생성
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		try {
			//1.2. 디비연결
			con = getCon();
			
			//3. sql( o_num 일련번호 계산 )
			sql = "select max(f_or_num) from f_order";
			pstmt = con.prepareStatement(sql);
			
			//4. sql실행
			rs = pstmt.executeQuery();
			
			//5. 데이터처리
			if(rs.next()){
				o_num = rs.getInt(1)+1;
			}
			
			trade_num = o_num;
			
			System.out.println("DAO : 일련번호(f_or_num) "+o_num+", 주문번호 (f_or_trade_num): "+trade_num);
			
			// 상품정보를 저장(order 테이블)
			
			
			for(int i=0;i<basketList.size();i++){
				BasketBean bk =(BasketBean) basketList.get(i);
				GoodsBean gb = (GoodsBean) goodsList.get(i);				
				
				// 3. sql 작성 & pstmt 객체 (insert)
				sql="insert into f_order "
						+ "values (?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "?,?,?,?,?,"
						+ "now(),?,now(),?)";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, o_num);
				pstmt.setString(2, 
						sdf.format(cal.getTime())+"-"+trade_num);
				pstmt.setInt(3, bk.getF_bas_gs_num());
				pstmt.setString(4, gb.getF_gs_name());
				pstmt.setInt(5, bk.getF_bas_amount());
				
				pstmt.setString(6, ob.getF_or_mem_postcode());
				pstmt.setString(7, ob.getF_or_mem_address());
				pstmt.setString(8, ob.getF_or_mem_detailAddress());
				pstmt.setString(9, ob.getF_or_mem_extraAddress());
				pstmt.setString(10, ob.getF_or_mem_id());
				pstmt.setString(11, ob.getF_or_rec_name());
				pstmt.setString(12, ob.getF_or_rec_postcode());
				pstmt.setString(13, ob.getF_or_rec_address());
				pstmt.setString(14, ob.getF_or_rec_detailAddress());
				pstmt.setString(15, ob.getF_or_rec_extraAddress());
				pstmt.setString(16, ob.getF_or_rec_phone());
				pstmt.setString(17, ob.getF_or_memo());
				
				pstmt.setInt(18, bk.getF_bas_amount() * gb.getF_gs_price());
				pstmt.setString(19, ob.getF_or_trade_type());
				pstmt.setString(20, ob.getF_or_trade_payer());
				
				pstmt.setString(21, ""); // 운송장번호 - 주문시 공백문자
				pstmt.setInt(22, 0); // 주문상태
				
				// 4. sql 실행
				pstmt.executeUpdate();
				
				o_num++; // 일련번호 증가 
				
			}// for
			
			System.out.println("DAO : 주문정보 저장 완료");			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// addOrder(ob,basketList,goodsList)
	
	// getOrderList(id)
	public List getOrderList(String id){
		List orderList = new ArrayList();
		
		try {
			//1.2. 디비연결
			con = getCon();
			//3. sql
			sql = "select f_or_trade_num,f_or_gs_name,f_or_bas_amount,"
					+ "f_or_sum,"
					+ "f_or_trans_num,f_or_date,f_or_status,f_or_trade_type from f_order "
					+ "where f_or_mem_id=? order by f_or_trade_num desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
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
				
				orderList.add(ob);
			}//while
			
			System.out.println("DAO : 주문 목록 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return orderList;
	}
	// getOrderList(id)
	
	// orderDetail(trade_num)
	public List orderDetail(String trade_num){
		List orderDetailList = new ArrayList();
		
		try {
			//1.2. 드라이버 로드
			con = getCon();
			//3. sql 구문
			sql = "select * from f_order where f_or_trade_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, trade_num);
			
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
				ob.setF_or_rec_postcode(rs.getString("f_or_rec_postcode"));
				ob.setF_or_rec_address(rs.getString("f_or_rec_address"));
				ob.setF_or_rec_detailAddress(rs.getString("f_or_rec_detailAddress"));
				ob.setF_or_rec_extraAddress(rs.getString("f_or_rec_extraAddress"));
				ob.setF_or_rec_name(rs.getString("f_or_rec_name"));
				ob.setF_or_memo(rs.getString("f_or_memo"));

				orderDetailList.add(ob);
				
			}// while

			System.out.println("DAO: 주문 상세페이지 정보 저장완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return orderDetailList;
	}
	// orderDetail(trade_num)

	
}