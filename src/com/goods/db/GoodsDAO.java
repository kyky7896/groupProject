package com.goods.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.admin.goods.db.GoodsBean;
import com.basket.db.BasketBean;

public class GoodsDAO {
	

	// 메서드 작업에 필요한 참조변수 준비 
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

//----------------------------------------------------------------------------------Connection getCon()----------------------------------
		
		
	private Connection getCon() throws Exception{ // 연결 정보를 리턴하는 메서드
		
		Context initCTX = new InitialContext(); 
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/foodDB"); //java:comp/env/ : 고정 + context.xml 의 name 정보 붙여넣기
		con = ds.getConnection();
			
		System.out.println("[goodsDAO] : 디비 연결 성공! >> "+con);
		return con;
		
	}//getCon()

//---------------------------------------------------------------------------------void closeDB()-------------------------------------
		
	
	private void closeDB(){
		try {
			if(rs != null ) rs.close();
			if(pstmt != null ) pstmt.close();
			if(con != null ) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// closeDB()
	
	//getGoodsList(item, type)
  public List<GoodsBean> getGoodsList(String item,String type){ 
    
    /* 선택한 카테고리 & 옵션값(select값)에 부합되는 상품 정보 가져오기   */
  
  StringBuffer SQL = new StringBuffer();
  GoodsBean gb = null;
  List<GoodsBean> goodsList = new ArrayList<GoodsBean>();
  
      try {
        con = getCon();
        
        SQL.append("select * from f_goods");
        
        if(item.equals("all")){
          if(type.equals("newest")){
            SQL.append(" order by f_gs_date desc");
          }else if(type.equals("lowPrice")){
            SQL.append(" order by f_gs_price");
          }else if(type.equals("highPrice")){
            SQL.append(" order by f_gs_price desc");
          }else if(type.equals("hit")){
            SQL.append(" order by f_gs_hit desc");
          }
        }else{
          if(type.equals("newest")){
            SQL.append(" where f_gs_cate=? order by f_gs_date desc");
          }else if(type.equals("lowPrice")){
            SQL.append(" where f_gs_cate=? order by f_gs_price");
          }else if(type.equals("highPrice")){
            SQL.append(" where f_gs_cate=? order by f_gs_price desc");
          }else if(type.equals("hit")){
            SQL.append(" where f_gs_cate=? order by f_gs_hit desc");
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
          gb = new GoodsBean();
          gb.setF_gs_num(rs.getInt("f_gs_num"));
          gb.setF_gs_cate(rs.getString("f_gs_cate"));
          gb.setF_gs_name(rs.getString("f_gs_name"));
          gb.setF_gs_price(rs.getInt("f_gs_price"));
          gb.setF_gs_amount(rs.getInt("f_gs_amount"));
          gb.setF_gs_date(rs.getString("f_gs_date"));
          gb.setF_gs_msg(rs.getString("f_gs_msg"));
          gb.setF_gs_img(rs.getString("f_gs_img"));
          gb.setF_gs_hit(rs.getInt("f_gs_hit"));
          
      // 자바빈 객체들을 list 자료구조에 저장
          goodsList.add(gb); // 자바빈 객체 한 개씩 arraylist저장공간에 담기 (순서대로)
        }// while
        
        System.out.println("[GoodsDAO] :  디비에 저장되어 있는 모든 상품 정보 _리스트에 담기 완료!!");
        
      } catch (Exception e) {
        System.out.println("[GoodsDAO] : 디비로부터 상품정보 가져오는 쿼리 구문 실행 실패 >> "+e.getMessage());
        e.printStackTrace();
      }finally{
        closeDB();
      }
  
    // 작업결과인 list 배열 반환하기
      return goodsList;
  }
  //
	
	
	public GoodsBean getGoods(int num){
		GoodsBean gb=null;
		
		try {
			//1.2. 디비연결
			con=getCon();
			//3.sql & pstmt
			sql="select * from f_goods where f_gs_num=?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				gb=new GoodsBean();
				gb.setF_gs_amount(rs.getInt("f_gs_amount"));
				gb.setF_gs_cate(rs.getString("f_gs_cate"));
				gb.setF_gs_date(rs.getString("f_gs_date"));
				gb.setF_gs_hit(rs.getInt("f_gs_hit"));
				gb.setF_gs_img(rs.getString("f_gs_img"));
				gb.setF_gs_msg(rs.getString("f_gs_msg"));
				gb.setF_gs_name(rs.getString("f_gs_name"));
				gb.setF_gs_num(rs.getInt("f_gs_num"));
				gb.setF_gs_price(rs.getInt("f_gs_price"));
				
			}
			
			System.out.println("DAO : 상품정보 저장완료!");
		} catch (Exception e) {
			e.printStackTrace();
			
		}finally {
			closeDB();
		}
		return gb;
	}
	
	//
	
	// updateAmount(basketList)

	public void updateAmount(List basketList){
		
		try {
			con = getCon();
			
			// sql -> 장바구니에 있는 정보 중에서 각 상품의 개수만큼 감소
			// => 반복 (장바구니 물품 개수만큼)
			
			for(int i=0; i<basketList.size(); i++){
				BasketBean bk = (BasketBean) basketList.get(i);
				
				sql = "update f_goods set f_gs_amount=f_gs_amount-? "
						+ "where f_gs_num=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, bk.getF_bas_amount());
				pstmt.setInt(2, bk.getF_bas_gs_num());
			
				pstmt.executeUpdate();
			
			}
			
			System.out.println("DAO : 구매 후 판매수량 수정 완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	
	// updateAmount(basketList)  - 구매 후 작동 
	
	

}
