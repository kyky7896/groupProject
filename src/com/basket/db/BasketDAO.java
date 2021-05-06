package com.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.admin.goods.db.GoodsBean;

public class BasketDAO {
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  String sql = "";
  
  public Connection getCon() throws Exception {
    Context initCTX = new InitialContext();
    DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/foodDB");
    con = ds.getConnection();
    
    return con;
  }
  
  public void closeDB() {
    try {
      if(rs != null){ rs.close(); }
      if(pstmt != null){ pstmt.close(); }
      if(con != null){ con.close(); }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  
  //checkGoods(basket)
  public int checkGoods(BasketBean basb){
    int check = -1;
    
    try {
      con = getCon();
      sql = "select * from f_basket "
          + "where f_bas_mem_id=? and f_bas_gs_num=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, basb.getF_bas_mem_id());
      pstmt.setInt(2, basb.getF_bas_gs_num());
      rs = pstmt.executeQuery();
      if(rs.next()){
        // 장바구니에 동일한 상품이 존재 → 수량만 증가(update)
        check = 1;
        sql = "update f_basket set f_bas_amount=f_bas_amount+? "
            + "where f_bas_mem_id=? and f_bas_gs_num=?";
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, basb.getF_bas_amount());
        pstmt.setString(2, basb.getF_bas_mem_id());
        pstmt.setInt(3, basb.getF_bas_gs_num());
        pstmt.executeUpdate();
         
        System.out.println("DAO : 동일 상품 수량 수정 완료!");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
     
    return check;
  }
  // checkGoods(basket)

  //basketAdd(basket)
  public void basketAdd(BasketBean basb){
    int bas_num = 0;
    
    try {
      con = getCon();
      sql = "select max(f_bas_num) from f_basket";
      pstmt = con.prepareStatement(sql);
      rs = pstmt.executeQuery();
      if(rs.next()){
        // rs.getInt("max(b_num)")+1;
        bas_num = rs.getInt(1)+1;
      }
      System.out.println("DAO : 장바구니 번호 "+bas_num);
      
      sql = "insert into f_basket(f_bas_num, f_bas_mem_id, f_bas_gs_num, "
      		+ "f_bas_amount, f_bas_date) values(?, ?, ?, ?, now())";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, bas_num);
      pstmt.setString(2, basb.getF_bas_mem_id());
      pstmt.setInt(3, basb.getF_bas_gs_num());
      pstmt.setInt(4, basb.getF_bas_amount());
      pstmt.executeUpdate();
      System.out.println("DAO : 장바구니 저장 완료");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
  }
  // basketAdd(basket)
  
  // getBasketList(id)
  public Vector getBasketList(String id){
    // 상품정보 + 장바구니 정보 저장
    Vector totalList = new Vector();
    
    // 장바구니 정보 저장(상품번호, 구매수량, 옵션 ...)
    List basketList = new ArrayList();
    
    // 상품정보 저장(상품이미지, 가격, 이름 ...)
    List goodsList = new ArrayList();
    
    try {
      con = getCon();
      sql = "select * from f_basket where f_bas_mem_id=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      rs = pstmt.executeQuery();
      while(rs.next()){
        // 장바구니 정보를 저장(bean → List)
        BasketBean basket = new BasketBean();
        basket.setF_bas_date(rs.getString("f_bas_date"));
        basket.setF_bas_amount(rs.getInt("f_bas_amount"));
        basket.setF_bas_gs_num(rs.getInt("f_bas_gs_num"));
        basket.setF_bas_size(rs.getString("f_bas_size"));
        basket.setF_bas_mem_id(rs.getString("f_bas_mem_id"));
        basket.setF_bas_num(rs.getInt("f_bas_num"));
        basketList.add(basket);
        
        // 장바구니에 저장된 상품의 정보를 DB에서 가져오기
        sql = "select * from f_goods where f_gs_num=?";
        PreparedStatement pstmt2 = con.prepareStatement(sql);
        pstmt2.setInt(1, basket.getF_bas_gs_num());
        ResultSet rs2 = pstmt2.executeQuery();
        if(rs2.next()){
          GoodsBean gb = new GoodsBean();
          gb.setF_gs_name(rs2.getString("f_gs_name"));
          gb.setF_gs_price(rs2.getInt("f_gs_price"));
          gb.setF_gs_img(rs2.getString("f_gs_img"));
          gb.setF_gs_amount(rs2.getInt("f_gs_amount"));
          // ... 그 외 나머지 정보
          goodsList.add(gb);
        }// if 끝
      }// while 끝
      System.out.println("DAO : 장바구니 + 상품정보 저장 완료(List)");
      
      totalList.add(basketList);
      totalList.add(goodsList);
      
      System.out.println("DAO : 벡터0-장바구니리스트, 벡터1-상품리스트");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
    
    return totalList;
  }
  // getBasketList(id)  
  
  // basketDelete(f_bas_num)
  public void basketDelete(int f_bas_num){
    try {
      con = getCon();
      sql = "delete from f_basket where f_bas_num=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setInt(1, f_bas_num);
      pstmt.executeUpdate();
      
      System.out.println("DAO : 장바구니 삭제 완료");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
  }
  // basketDelete(f_bas_num)
  
  // basketDelete(id)
  public void basketDelete(String id){
    try {
      con = getCon();
      sql = "delete from f_basket where f_bas_mem_id=?";
      pstmt = con.prepareStatement(sql);
      pstmt.setString(1, id);
      pstmt.executeUpdate();
      System.out.println("DAO : 주문 후 장바구니 삭제 완료");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeDB();
    }
  }
  // basketDelete(id)
  
}
