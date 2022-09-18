package com.goodres.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.glass.ui.CommonDialogs.Type;

public class GoodResDAO {
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
	// getKeywordSearch(keyword) goodres_list 뿌려질 데이터
	public List<GoodResBoardBean> getKeywordSearch(String keyword) {		
		GoodResBoardBean drbb = null;
		List<GoodResBoardBean> keywordList = new ArrayList<GoodResBoardBean>();
		
		try {
			// 1. DB연결
			con = getCon();
			
			// 2, 3. sql 작성 & pstmt 객체 생성
			sql = "select * from f_goodres where f_gr_name like ? or f_gr_gu like ? or f_gr_addr like ? or f_gr_foodtype like ?";
			
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			pstmt.setString(4, "%" + keyword + "%");
			
			// 4. 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				drbb = new GoodResBoardBean();
				
				drbb.setF_gr_addr(rs.getString("f_gr_addr"));
				drbb.setF_gr_cnt(rs.getInt("f_gr_cnt"));
				drbb.setF_gr_content(rs.getString("f_gr_content"));
				drbb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
				drbb.setF_gr_eximg(rs.getString("f_gr_eximg"));
				drbb.setF_gr_gu(rs.getString("f_gr_gu"));
				drbb.setF_gr_hp(rs.getString("f_gr_hp"));
				drbb.setF_gr_img(rs.getString("f_gr_img"));
				drbb.setF_gr_lati(rs.getDouble("f_gr_lati"));				
				drbb.setF_gr_long(rs.getDouble("f_gr_long"));
				drbb.setF_gr_menu(rs.getString("f_gr_menu"));
				drbb.setF_gr_name(rs.getString("f_gr_name"));
				drbb.setF_gr_num(rs.getInt("f_gr_num"));
				drbb.setF_gr_open(rs.getString("f_gr_open"));
				drbb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
				drbb.setF_gr_staravg(rs.getDouble("f_gr_staravg"));
				drbb.setF_gr_tel(rs.getString("f_gr_tel"));
				drbb.setF_gr_thumb(rs.getString("f_gr_thumb"));
				drbb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));				
				
				keywordList.add(drbb);				
			}
			System.out.println("검색결과 리스트 저장 완료!");
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return keywordList;
	}
	// getKeywordSearch(keyword) goodres_list 뿌려질 데이터
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// getGoodResList()
	public HashMap<String, List> getGoodResList() {
		List<GoodResBoardBean> GoodResList = null;

		HashMap<String, List> totalList = new HashMap<>();
		GoodResBoardBean grb = null;

		String[] gu = { "강서구", "금정구", "기장군", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구",
				"중구", "해운대구" };

		try {
			// 1.2. 디비연결
			con = getCon();

			for (int i = 0; i < gu.length; i++) {

				GoodResList = new ArrayList<GoodResBoardBean>();

				// 3. sql구문 작성 & pstmt 객체 생성
				sql = "select * from f_goodres where f_gr_gu=?";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, gu[i]);

				// 4. sql구문 실행
				rs = pstmt.executeQuery();

				// 5. 데이터 처리
				while (rs.next()) {
					grb = new GoodResBoardBean();

					grb.setF_gr_addr(rs.getString("f_gr_addr"));
					grb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
					grb.setF_gr_content(rs.getString("f_gr_content"));
					grb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
					grb.setF_gr_eximg(rs.getString("f_gr_eximg"));
					grb.setF_gr_gu(rs.getString("f_gr_gu"));
					grb.setF_gr_hp(rs.getString("f_gr_hp"));
					grb.setF_gr_img(rs.getString("f_gr_img"));
					grb.setF_gr_lati(rs.getDouble("f_gr_lati"));					
					grb.setF_gr_long(rs.getDouble("f_gr_long"));
					grb.setF_gr_menu(rs.getString("f_gr_menu"));
					grb.setF_gr_name(rs.getString("f_gr_name"));
					grb.setF_gr_num(rs.getInt("f_gr_num"));
					grb.setF_gr_open(rs.getString("f_gr_open"));
					grb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
					grb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
					grb.setF_gr_tel(rs.getString("f_gr_tel"));
					grb.setF_gr_thumb(rs.getString("f_gr_thumb"));
					grb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));

					// 리스트 한칸에 맛집 1곳의 정보를 저장
					GoodResList.add(grb);
				} // while

				totalList.put(gu[i], GoodResList);
			}

			System.out.println("DAO : 맛집 리스트 저장완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return totalList;
	}
	// getGoodResList()
	////////////////////////////////////////////////////////////////////////////////////

	// getGuList(gu)
	public List<GoodResBoardBean> getGuList(String gu) {

		List<GoodResBoardBean> guList = new ArrayList();
		GoodResBoardBean grb = null;

		try {
			con = getCon();

			sql = "select * from f_goodres where f_gr_gu=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gu);

			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				grb = new GoodResBoardBean();

				grb.setF_gr_addr(rs.getString("f_gr_addr"));
				grb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
				grb.setF_gr_content(rs.getString("f_gr_content"));
				grb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
				grb.setF_gr_eximg(rs.getString("f_gr_eximg"));
				grb.setF_gr_gu(rs.getString("f_gr_gu"));
				grb.setF_gr_hp(rs.getString("f_gr_hp"));
				grb.setF_gr_img(rs.getString("f_gr_img"));
				grb.setF_gr_lati(rs.getDouble("f_gr_lati"));				
				grb.setF_gr_long(rs.getDouble("f_gr_long"));
				grb.setF_gr_menu(rs.getString("f_gr_menu"));
				grb.setF_gr_name(rs.getString("f_gr_name"));
				grb.setF_gr_num(rs.getInt("f_gr_num"));
				grb.setF_gr_open(rs.getString("f_gr_open"));
				grb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
				grb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
				grb.setF_gr_tel(rs.getString("f_gr_tel"));
				grb.setF_gr_thumb(rs.getString("f_gr_thumb"));
				grb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));

				guList.add(grb);
			}

			// System.out.println(guList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return guList;
	}
	// getGuList(gu)

	////////////////////////////////////////////////////////////////////////////////////
	// getGoodResTypeList()
	public HashMap<String, List> getGoodResTypeList() {
		List<GoodResBoardBean> goodResTypeList = null;

		HashMap<String, List> totalTypeList = new HashMap<>();
		GoodResBoardBean grb = null;

		String[] type = { "카페", "밀면", "국밥", "중식", "스시", "정식", "베이커리", "만두", "돈까스" };

		try {
			// 1.2. 디비연결
			con = getCon();

			for (int i = 0; i < type.length; i++) {

				goodResTypeList = new ArrayList<GoodResBoardBean>();

				// 3. sql구문 작성 & pstmt 객체 생성
				sql = "select * from f_goodres where f_gr_foodtype=?";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, type[i]);

				// 4. sql구문 실행
				rs = pstmt.executeQuery();

				// 5. 데이터 처리
				while (rs.next()) {
					grb = new GoodResBoardBean();

					grb.setF_gr_name(rs.getString("f_gr_name"));
					grb.setF_gr_menu(rs.getString("f_gr_menu"));
					grb.setF_gr_img(rs.getString("f_gr_img"));
					grb.setF_gr_gu(rs.getString("f_gr_gu"));
					grb.setF_gr_addr(rs.getString("f_gr_addr"));
					grb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
					grb.setF_gr_content(rs.getString("f_gr_content"));
					grb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
					grb.setF_gr_eximg(rs.getString("f_gr_eximg"));
					grb.setF_gr_hp(rs.getString("f_gr_hp"));
					grb.setF_gr_lati(rs.getDouble("f_gr_lati"));
					grb.setF_gr_long(rs.getDouble("f_gr_long"));
					grb.setF_gr_num(rs.getInt("f_gr_num"));
					grb.setF_gr_open(rs.getString("f_gr_open"));
					grb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
					grb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
					grb.setF_gr_tel(rs.getString("f_gr_tel"));
					grb.setF_gr_thumb(rs.getString("f_gr_thumb"));
					grb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));

					// 리스트 한칸에 맛집 1곳의 정보를 저장
					goodResTypeList.add(grb);
				} // while

				totalTypeList.put(type[i], goodResTypeList);
			}

			System.out.println("DAO : 메뉴별 맛집 리스트 저장완료");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return totalTypeList;
	}
	// getGoodResTypeList()
	////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////////////////////////
	// getTypeList(type)
	public List<GoodResBoardBean> getTypeList(String foodtype) {

		List<GoodResBoardBean> typeList = new ArrayList();
		GoodResBoardBean grb = null;

		try {
			con = getCon();

			sql = "select * from f_goodres where f_gr_foodtype=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, foodtype);

			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			while (rs.next()) {
				grb = new GoodResBoardBean();

				grb.setF_gr_addr(rs.getString("f_gr_addr"));
				grb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
				grb.setF_gr_content(rs.getString("f_gr_content"));
				grb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
				grb.setF_gr_eximg(rs.getString("f_gr_eximg"));
				grb.setF_gr_gu(rs.getString("f_gr_gu"));
				grb.setF_gr_hp(rs.getString("f_gr_hp"));
				grb.setF_gr_img(rs.getString("f_gr_img"));
				grb.setF_gr_lati(rs.getDouble("f_gr_lati"));				
				grb.setF_gr_long(rs.getDouble("f_gr_long"));
				grb.setF_gr_menu(rs.getString("f_gr_menu"));
				grb.setF_gr_name(rs.getString("f_gr_name"));
				grb.setF_gr_num(rs.getInt("f_gr_num"));
				grb.setF_gr_open(rs.getString("f_gr_open"));
				grb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
				grb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
				grb.setF_gr_tel(rs.getString("f_gr_tel"));
				grb.setF_gr_thumb(rs.getString("f_gr_thumb"));
				grb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));

				typeList.add(grb);
			}

			System.out.println(typeList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return typeList;
	}
	// getTypeList(type)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// getBoardBean(goodres_num)
	public GoodResBoardBean getBoardBean(int goodres_num) {
		GoodResBoardBean grbb = null;
		
		try {
			con = getCon();

			sql = "select * from f_goodres where f_gr_num=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, goodres_num);

			rs = pstmt.executeQuery();

			// 5. 데이터 처리
			if(rs.next()) {
				grbb = new GoodResBoardBean();

				grbb.setF_gr_addr(rs.getString("f_gr_addr"));
				grbb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
				grbb.setF_gr_content(rs.getString("f_gr_content"));
				grbb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
				grbb.setF_gr_eximg(rs.getString("f_gr_eximg"));
				grbb.setF_gr_gu(rs.getString("f_gr_gu"));
				grbb.setF_gr_hp(rs.getString("f_gr_hp"));
				grbb.setF_gr_img(rs.getString("f_gr_img"));
				grbb.setF_gr_lati(rs.getDouble("f_gr_lati"));				
				grbb.setF_gr_long(rs.getDouble("f_gr_long"));
				grbb.setF_gr_menu(rs.getString("f_gr_menu"));
				grbb.setF_gr_name(rs.getString("f_gr_name"));
				grbb.setF_gr_num(rs.getInt("f_gr_num"));
				grbb.setF_gr_open(rs.getString("f_gr_open"));
				grbb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
				grbb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
				grbb.setF_gr_tel(rs.getString("f_gr_tel"));
				grbb.setF_gr_thumb(rs.getString("f_gr_thumb"));
				grbb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));				
			}

			// System.out.println(guList);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return grbb;
	}
	// getBoardBean(goodres_num)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// getGoodResStarAvgList()
	public List getGoodResStarAvgList() {
		List<GoodResBoardBean> starAvgList = new ArrayList<GoodResBoardBean>();
		GoodResBoardBean grbb = null;
		
		try {
			con = getCon();

			sql = "select * from f_goodres order by f_gr_starAvg desc";

			pstmt = con.prepareStatement(sql);		

			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			while(rs.next()) {
				grbb = new GoodResBoardBean();

				grbb.setF_gr_addr(rs.getString("f_gr_addr"));
				grbb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
				grbb.setF_gr_content(rs.getString("f_gr_content"));
				grbb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
				grbb.setF_gr_eximg(rs.getString("f_gr_eximg"));
				grbb.setF_gr_gu(rs.getString("f_gr_gu"));
				grbb.setF_gr_hp(rs.getString("f_gr_hp"));
				grbb.setF_gr_img(rs.getString("f_gr_img"));
				grbb.setF_gr_lati(rs.getDouble("f_gr_lati"));				
				grbb.setF_gr_long(rs.getDouble("f_gr_long"));
				grbb.setF_gr_menu(rs.getString("f_gr_menu"));
				grbb.setF_gr_name(rs.getString("f_gr_name"));
				grbb.setF_gr_num(rs.getInt("f_gr_num"));
				grbb.setF_gr_open(rs.getString("f_gr_open"));
				grbb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
				grbb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
				grbb.setF_gr_tel(rs.getString("f_gr_tel"));
				grbb.setF_gr_thumb(rs.getString("f_gr_thumb"));
				grbb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));
				
				starAvgList.add(grbb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return starAvgList;
	}
	// getGoodResStarAvgList()
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// getGoodResFoodTypeList();
	public HashMap<String, List> getGoodResFoodTypeMap() {
		
		HashMap<String, List> goodResFoodTypeMap = new HashMap<>();
		List<GoodResBoardBean> goodResFoodTypeList = null;
		GoodResBoardBean grbb = null;
		List<String> foodType = new ArrayList<String>();

		try {
			// 1.2. 디비연결
			con = getCon();
			
			// 3. sql구문 작성 & pstmt 객체 생성
			sql = "select distinct f_gr_foodtype from f_goodres order by f_gr_foodtype";

			pstmt = con.prepareStatement(sql);

			// 4. sql구문 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {		
				foodType.add(rs.getString("f_gr_foodtype"));	
			}
			
			for (int i = 0; i < foodType.size(); i++) {
				
				goodResFoodTypeList = new ArrayList<GoodResBoardBean>();

				// 3. sql구문 작성 & pstmt 객체 생성
				sql = "select * from f_goodres where f_gr_foodtype=?";

				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, foodType.get(i));

				// 4. sql구문 실행
				rs = pstmt.executeQuery();

				// 5. 데이터 처리
				while (rs.next()) {
					grbb = new GoodResBoardBean();

					grbb.setF_gr_name(rs.getString("f_gr_name"));
					grbb.setF_gr_menu(rs.getString("f_gr_menu"));
					grbb.setF_gr_img(rs.getString("f_gr_img"));
					grbb.setF_gr_gu(rs.getString("f_gr_gu"));
					grbb.setF_gr_addr(rs.getString("f_gr_addr"));
					grbb.setF_gr_cnt(rs.getInt("F_gr_cnt"));
					grbb.setF_gr_content(rs.getString("f_gr_content"));
					grbb.setF_gr_detailAddr(rs.getString("f_gr_detailAddr"));
					grbb.setF_gr_eximg(rs.getString("f_gr_eximg"));
					grbb.setF_gr_hp(rs.getString("f_gr_hp"));
					grbb.setF_gr_lati(rs.getDouble("f_gr_lati"));
					grbb.setF_gr_long(rs.getDouble("f_gr_long"));
					grbb.setF_gr_num(rs.getInt("f_gr_num"));
					grbb.setF_gr_open(rs.getString("f_gr_open"));
					grbb.setF_gr_rv_cnt(rs.getInt("f_gr_rv_cnt"));
					grbb.setF_gr_staravg(rs.getFloat("f_gr_staravg"));
					grbb.setF_gr_tel(rs.getString("f_gr_tel"));
					grbb.setF_gr_thumb(rs.getString("f_gr_thumb"));
					grbb.setF_gr_foodtype(rs.getString("f_gr_foodtype"));

					// 리스트 한칸에 맛집 1곳의 정보를 저장
					goodResFoodTypeList.add(grbb);
				} // while

				goodResFoodTypeMap.put(foodType.get(i), goodResFoodTypeList);
			}

			System.out.println("DAO : 메뉴별 맛집 리스트 저장완료");
			System.out.println("foodType>>>>>>>>>>>>>>>>>>" + foodType);
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	
		return goodResFoodTypeMap;
	}
	// getGoodResFoodTypeList();
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	// updateReadCount(goodres_num)
	public void updateReadCount(int goodres_num) {

		try {
			// 1.2. 디비연결
			con = getCon();

			// 3. sql 쿼리(update) & pstmt 객체
			// 기존의 조회수를 1증가
			sql = "update f_goodres set f_gr_cnt=f_gr_cnt+1 where f_gr_num=?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, goodres_num);

			// 4. sql 실행
			pstmt.executeUpdate();

			System.out.println("DAO : 조회수 1증가!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// updateReadCount(goodres_num)
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//
	//
	////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//
	//
	////////////////////////////////////////////////////////////////////////////////////
}
