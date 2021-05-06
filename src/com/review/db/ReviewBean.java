package com.review.db;

import java.sql.Date;

public class ReviewBean {
	private String f_grv_mem_id;	// 작성자
	private String f_grv_mem_nick;	// 작성자 닉네임
	private Date f_grv_date;		// 리뷰 작성일
	private String f_grv_img;		// 사진
	private String f_grv_star;			// 별점
	private String f_grv_content;	// 리뷰 내용
	private int f_grv_num;			// 리뷰번호
	
	private int f_grv_gr_num;		// 맛집번호
	
	
	public int getF_grv_gr_num() {
		return f_grv_gr_num;
	}
	public void setF_grv_gr_num(int f_grv_gr_num) {
		this.f_grv_gr_num = f_grv_gr_num;
	}
	public int getF_grv_num() {
		return f_grv_num;
	}
	public void setF_grv_num(int f_grv_num) {
		this.f_grv_num = f_grv_num;
	}
	public String getF_grv_mem_id() {
		return f_grv_mem_id;
	}
	public void setF_grv_mem_id(String f_grv_mem_id) {
		this.f_grv_mem_id = f_grv_mem_id;
	}
	public String getF_grv_mem_nick() {
		return f_grv_mem_nick;
	}
	public void setF_grv_mem_nick(String f_grv_mem_nick) {
		this.f_grv_mem_nick = f_grv_mem_nick;
	}
	public Date getF_grv_date() {
		return f_grv_date;
	}
	public void setF_grv_date(Date f_grv_date) {
		this.f_grv_date = f_grv_date;
	}
	public String getF_grv_img() {
		return f_grv_img;
	}
	public void setF_grv_img(String f_grv_img) {
		this.f_grv_img = f_grv_img;
	}
	public String getF_grv_star() {
		return f_grv_star;
	}
	public void setF_grv_star(String f_grv_star) {
		this.f_grv_star = f_grv_star;
	}
	public String getF_grv_content() {
		return f_grv_content;
	}
	public void setF_grv_content(String f_grv_content) {
		this.f_grv_content = f_grv_content;
	}
	
	@Override
	public String toString() {
		return "ReviewBean [f_grv_mem_id=" + f_grv_mem_id + ", f_grv_mem_nick=" + f_grv_mem_nick + ", f_grv_date="
				+ f_grv_date + ", f_grv_img=" + f_grv_img + ", f_grv_star=" + f_grv_star + ", f_grv_content="
				+ f_grv_content + ", f_grv_num=" + f_grv_num + ", f_grv_gr_num=" + f_grv_gr_num + "]";
	}
	
}
