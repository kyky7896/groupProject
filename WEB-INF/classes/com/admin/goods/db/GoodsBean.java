package com.admin.goods.db;

import java.sql.Date;

public class GoodsBean { // f_gs 테이블
	

	
	private int f_gs_num;  // 상품번호
	private String f_gs_cate; // 카테고리
	private String f_gs_name; // 상품 이름
	private int f_gs_price; // 가격
	private int f_gs_amount; // 재고 수량
	private String f_gs_date; // 상품등록날짜
	private String f_gs_msg; // 상품 메세지
	private String f_gs_img; // 상품 이미지
	private int f_gs_hit; // 인기상품 여부
	
	
	
	public int getF_gs_num() {
		return f_gs_num;
	}
	public void setF_gs_num(int f_gs_num) {
		this.f_gs_num = f_gs_num;
	}
	public String getF_gs_cate() {
		return f_gs_cate;
	}
	public void setF_gs_cate(String f_gs_cate) {
		this.f_gs_cate = f_gs_cate;
	}
	public String getF_gs_name() {
		return f_gs_name;
	}
	public void setF_gs_name(String f_gs_name) {
		this.f_gs_name = f_gs_name;
	}
	public int getF_gs_price() {
		return f_gs_price;
	}
	public void setF_gs_price(int f_gs_price) {
		this.f_gs_price = f_gs_price;
	}
	public int getF_gs_amount() {
		return f_gs_amount;
	}
	public void setF_gs_amount(int f_gs_amount) {
		this.f_gs_amount = f_gs_amount;
	}
	public String getF_gs_date() {
		return f_gs_date;
	}
	public void setF_gs_date(String f_gs_date) {
		this.f_gs_date = f_gs_date;
	}
	public String getF_gs_msg() {
		return f_gs_msg;
	}
	public void setF_gs_msg(String f_gs_msg) {
		this.f_gs_msg = f_gs_msg;
	}
	public String getF_gs_img() {
		return f_gs_img;
	}
	public void setF_gs_img(String f_gs_img) {
		this.f_gs_img = f_gs_img;
	}
	public int getF_gs_hit() {
		return f_gs_hit;
	}
	public void setF_gs_hit(int f_gs_hit) {
		this.f_gs_hit = f_gs_hit;
	}
	
	
	@Override
	public String toString() {
		return "GoodsBean [f_gs_num=" + f_gs_num + ", f_gs_cate=" + f_gs_cate + ", f_gs_name=" + f_gs_name
				+ ", f_gs_price=" + f_gs_price + ", f_gs_amount=" + f_gs_amount + ", f_gs_date=" + f_gs_date
				+ ", f_gs_msg=" + f_gs_msg + ", f_gs_img=" + f_gs_img + ", f_gs_hit=" + f_gs_hit + "]";
	}
	
}
