package com.recipe.board.db;

import java.util.Date;

/**
 * @author oiio6
 *
 */
public class RecipeBean {
	private int f_rcp_num; // 글번호
	private String f_rcp_id; // 작성자ID
	private String f_rcp_nick; // 작성자 닉네임
	private String f_rcp_title; // 글제목
	private String f_rcp_content; // 요리설명(짧은)
	private int f_rcp_count; // 조회수
	private String f_rcp_th_img; // 대표이미지
	private Date f_rcp_date; // 올린날짜
	private String f_rcp_cate; // 카테고리
	private String f_rcp_imgs; // 요리 완성 이미지
	private String f_rcp_ingre; // 재료
	private String f_rcp_ord; //주문순서 내용
	private String f_rcp_ord_img; //주문순서 이미지
	private String f_rcp_hashtag;
	
	public int getF_rcp_num() {
		return f_rcp_num;
	}
	public void setF_rcp_num(int f_rcp_num) {
		this.f_rcp_num = f_rcp_num;
	}
	public String getF_rcp_id() {
		return f_rcp_id;
	}
	public void setF_rcp_id(String f_rcp_id) {
		this.f_rcp_id = f_rcp_id;
	}
	public String getF_rcp_nick() {
		return f_rcp_nick;
	}
	public void setF_rcp_nick(String f_rcp_nick) {
		this.f_rcp_nick = f_rcp_nick;
	}
	public String getF_rcp_title() {
		return f_rcp_title;
	}
	public void setF_rcp_title(String f_rcp_title) {
		this.f_rcp_title = f_rcp_title;
	}
	public String getF_rcp_content() {
		return f_rcp_content;
	}
	public void setF_rcp_content(String f_rcp_content) {
		this.f_rcp_content = f_rcp_content;
	}
	public int getF_rcp_count() {
		return f_rcp_count;
	}
	public void setF_rcp_count(int f_rcp_count) {
		this.f_rcp_count = f_rcp_count;
	}
	public String getF_rcp_th_img() {
		return f_rcp_th_img;
	}
	public void setF_rcp_th_img(String f_rcp_th_img) {
		this.f_rcp_th_img = f_rcp_th_img;
	}
	public Date getF_rcp_date() {
		return f_rcp_date;
	}
	public void setF_rcp_date(Date f_rcp_date) {
		this.f_rcp_date = f_rcp_date;
	}
	public String getF_rcp_cate() {
		return f_rcp_cate;
	}
	public void setF_rcp_cate(String f_rcp_cate) {
		this.f_rcp_cate = f_rcp_cate;
	}
	public String getF_rcp_imgs() {
		return f_rcp_imgs;
	}
	public void setF_rcp_imgs(String f_rcp_imgs) {
		this.f_rcp_imgs = f_rcp_imgs;
	}
	public String getF_rcp_ingre() {
		return f_rcp_ingre;
	}
	public void setF_rcp_ingre(String f_rcp_ingre) {
		this.f_rcp_ingre = f_rcp_ingre;
	}
	public String getF_rcp_ord() {
		return f_rcp_ord;
	}
	public void setF_rcp_ord(String f_rcp_ord) {
		this.f_rcp_ord = f_rcp_ord;
	}
	public String getF_rcp_ord_img() {
		return f_rcp_ord_img;
	}
	public void setF_rcp_ord_img(String f_rcp_ord_img) {
		this.f_rcp_ord_img = f_rcp_ord_img;
	}
	
	public String getF_rcp_hashtag() {
		return f_rcp_hashtag;
	}
	public void setF_rcp_hashtag(String f_rcp_hashtag) {
		this.f_rcp_hashtag = f_rcp_hashtag;
	}
	@Override
	public String toString() {
		return "RecipeBean [f_rcp_num=" + f_rcp_num + ", f_rcp_id=" + f_rcp_id + ", f_rcp_nick=" + f_rcp_nick
				+ ", f_rcp_title=" + f_rcp_title + ", f_rcp_content=" + f_rcp_content + ", f_rcp_count=" + f_rcp_count
				+ ", f_rcp_th_img=" + f_rcp_th_img + ", f_rcp_date=" + f_rcp_date + ", f_rcp_cate=" + f_rcp_cate
				+ ", f_rcp_imgs=" + f_rcp_imgs + ", f_rcp_ingre=" + f_rcp_ingre + ", f_rcp_ord=" + f_rcp_ord
				+ ", f_rcp_ord_img=" + f_rcp_ord_img + ", f_rcp_hashtag=" + f_rcp_hashtag + "]";
	}
	
	
	
	
	
	
	
	

}
