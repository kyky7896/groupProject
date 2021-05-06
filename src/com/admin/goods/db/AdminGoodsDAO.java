package com.admin.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminGoodsDAO {

	// 메서드 작업에 필요한 참조변수 준비
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String SQL = "";

	// ----------------------------------------------------------------------------------Connection
	// getCon()----------------------------------

	private Connection getCon() throws Exception { // 연결 정보를 리턴하는 메서드

		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/foodDB"); // java:comp/env/
																					// :
																					// 고정
																					// +
																					// context.xml
																					// 의
																					// name
																					// 정보
																					// 붙여넣기
		con = ds.getConnection();

		System.out.println("[goodsDAO] : 디비 연결 성공! >> " + con);
		return con;

	}// getCon()

	// ---------------------------------------------------------------------------------void
	// closeDB()-------------------------------------

	// 디비 자원 해제하는 메서드 closeDB()
	// 복붙 가능하다 = 공통적으로 사용되는 부분이다 = 설계시 반복되는 부분은 따로 빼낼 수 있어야

	private void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// closeDB()

	// -------------------------------------------------------------------------------------
	// void insertGoods(GoodsBean gb)

	public void insertGoods(
			GoodsBean gb) { /* 작업 내용 : 판매할 상품 상세 정보를 디비 테이블에 넣는다 */

		int num = 0;

		try {
			con = getCon();

			// (디비에 데이터 넣기 전!!) 상품 번호 계산 !! (select)
			SQL = "select max(f_gs_num) from f_goods";
			pstmt = con.prepareStatement(SQL);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1) + 1; // rs.getInt(1)의 값이 null이라면(=게시글 없음) 0을
										// 반환한다.
			}

			// 디비에 상품 정보 저장하기(insert into)
			SQL = "insert into f_goods" + " values(?,?,?,?,?,now(),?,?,?)";
			pstmt = con.prepareStatement(SQL);
			pstmt.setInt(1, num);
			pstmt.setString(2, gb.getF_gs_cate());
			pstmt.setString(3, gb.getF_gs_name());
			pstmt.setInt(4, gb.getF_gs_price());
			pstmt.setInt(5, gb.getF_gs_amount());
			pstmt.setString(6, gb.getF_gs_msg());
			pstmt.setString(7, gb.getF_gs_img());
			pstmt.setInt(8, gb.getF_gs_hit());
			pstmt.executeUpdate();

			System.out.println("[AdminGoodsDAO] : 판매할 상품 정보 _디비에 넣기 완료!!");

		} catch (Exception e) {

			System.out.println("[AdminGoodsDAO] : 상품 정보 저장_쿼리 구문 실행 실패 == " + e.getMessage());
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// -------------------------------------------------------------------------------------
	// void insertGoods(GoodsBean gb)

	public List<GoodsBean> getAdminGoodsList(
			String item) { /*
							 * 작업 내용 : 디비에 저장되어 있는 모든 상품 데이터를 꺼내서 goodsBean ->
							 * List에 담는다
							 */

		StringBuffer SQL = new StringBuffer();

		List<GoodsBean> adminGoodsList = new ArrayList<GoodsBean>();

		try {
			con = getCon();

			// 쿼리 작성(all,best,category 종류에 나눠서 작성)
			SQL.append("select * from f_goods");

			if (item.equals("all")) {
				// 위에 작성해놓은 쿼리 실행
			} else if (item.equals("best")) {
				SQL.append(" where f_goods_hit=?");
			} else {
				SQL.append(" where f_goods_cate=?");
			}

			// pstmt 객체 생성
			pstmt = con.prepareStatement(SQL.toString());

			// 물음표 채우기
			if (item.equals("all")) {

			} else if (item.equals("best")) {
				pstmt.setInt(1, 1); // 인기 상품의 경우는 1의 값을 가질 것이고, 일반 상품은 0의 값을 가질
									// 것이라고 지정했기 때문에 1을 임의로 줄 수 있음
			} else {
				pstmt.setString(1, item);
			}

			// 쿼리 실행
			rs = pstmt.executeQuery();

			// 결과에 따른 처리
			while (rs.next()) {
				GoodsBean gb = new GoodsBean();

				gb.setF_gs_num(rs.getInt("f_gs_num"));
				gb.setF_gs_cate(rs.getString("f_gs_cate"));
				gb.setF_gs_name(rs.getString("f_gs_name"));
				gb.setF_gs_price(rs.getInt("f_gs_price"));
				gb.setF_gs_amount(rs.getInt("f_gs_amount"));
				gb.setF_gs_msg(rs.getString("f_gs_msg"));
				gb.setF_gs_img(rs.getString("f_gs_img"));
				gb.setF_gs_date(rs.getString("f_gs_date"));
				gb.setF_gs_hit(rs.getInt("f_gs_hit"));

				adminGoodsList.add(gb);
			} // while

			System.out.println("[AdminGoodsDAO] : 판매할 상품 정보 _List에 저장 완료!!");

		} catch (Exception e) {
			System.out.println("[AdminGoodsDAO] : 상품 정보 가져오기_쿼리 실행 실패>> " + e.getMessage());
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return adminGoodsList;
	}

	// -------------------------------------------------------------------------------------
	// getGoods(num)
	public GoodsBean getGoods(int num) {
		GoodsBean gb = null;

		try {
			// 1.2.디비연결
			con = getCon();
			// 3. sql 생성 & pstmt 객체
			SQL = "select * from f_goods where f_gs_num=?";
			pstmt = con.prepareStatement(SQL);

			pstmt.setInt(1, num);
			// 4. sql 실행
			rs = pstmt.executeQuery();

			// 5. 데이터처리
			if (rs.next()) {
				gb = new GoodsBean();

				gb.setF_gs_num(rs.getInt("f_gs_num"));
				gb.setF_gs_cate(rs.getString("f_gs_cate"));
				gb.setF_gs_name(rs.getString("f_gs_name"));
				gb.setF_gs_price(rs.getInt("f_gs_price"));
				gb.setF_gs_amount(rs.getInt("f_gs_amount"));
				gb.setF_gs_msg(rs.getString("f_gs_msg"));
				gb.setF_gs_img(rs.getString("f_gs_img"));
				gb.setF_gs_date(rs.getString("f_gs_date"));
				gb.setF_gs_hit(rs.getInt("f_gs_hit"));

			}

			System.out.println("DAO : 상품정보 저장완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return gb;
	}

	// getGoods(num)
	// modifyGoods(gb) 시작
	public void modifyGoods(GoodsBean gb) {

		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql (update) & pstmt 객체 생성
			SQL = "update f_goods set " + "f_gs_cate=?,f_gs_name=?,f_gs_price=?,f_gs_amount=?,"
					+ "f_gs_msg=?,f_gs_hit=? " + "where f_gs_num=?";

			pstmt = con.prepareStatement(SQL);

			pstmt.setString(1, gb.getF_gs_cate());
			pstmt.setString(2, gb.getF_gs_name());
			pstmt.setInt(3, gb.getF_gs_price());
			pstmt.setInt(4, gb.getF_gs_amount());
			pstmt.setString(5, gb.getF_gs_msg());
			pstmt.setInt(6, gb.getF_gs_hit());
			pstmt.setInt(7, gb.getF_gs_num());

			// 4. sql구문 실행
			pstmt.executeUpdate();

			System.out.println("DAO : 관리자 상품정보 수정완료!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	// modifyGoods(gb) 끝
	// deleteGoods(f_gs_num) 시작
	public void deleteGoods(int f_gs_num) {

		try {
			// 1.2 디비연결
			con = getCon();
			// 3 .sql 작성 & pstmt 생성
			SQL = "delete from f_goods where f_gs_num=?";
			pstmt = con.prepareStatement(SQL);

			pstmt.setInt(1, f_gs_num);
			// 4. sql 실행
			pstmt.executeUpdate();
			System.out.println("DAO : 관리자 상품 삭제 ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	// deleteGoods(f_gs_num) 끝

}
