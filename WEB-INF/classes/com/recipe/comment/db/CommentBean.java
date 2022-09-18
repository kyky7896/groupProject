package com.recipe.comment.db;

import java.sql.Date;

public class CommentBean {
	
	private int f_rcpc_no; //댓글 번호 (pk)
	private int f_rcpc_rcp_num; //글 번호 (fk)
	private Date f_rcpc_date; //댓글 작성일
	private String f_rcpc_mem_id; //아이디 (fk)
	private String f_rcpc_mem_nick; //닉네임 (fk)
	private String f_rcpc_content; //댓글내용
	private int f_rcpc_ref; // 글번호(그룹번호)
	private int f_rcpc_lev; // 대댓글 깊이 정도(클래스 번호)
	private int f_rcpc_seq; // 대댓글 내 순서(클래스 내 순서)
	private String f_rcpc_ip; //ip
	private String f_rcpc_img; //댓글 사진
	
	
	public int getF_rcpc_no() {
		return f_rcpc_no;
	}
	public void setF_rcpc_no(int f_rcpc_no) {
		this.f_rcpc_no = f_rcpc_no;
	}
	public int getF_rcpc_rcp_num() {
		return f_rcpc_rcp_num;
	}
	public void setF_rcpc_rcp_num(int f_rcpc_rcp_num) {
		this.f_rcpc_rcp_num = f_rcpc_rcp_num;
	}
	public Date getF_rcpc_date() {
		return f_rcpc_date;
	}
	public void setF_rcpc_date(Date f_rcpc_date) {
		this.f_rcpc_date = f_rcpc_date;
	}
	public String getF_rcpc_mem_id() {
		return f_rcpc_mem_id;
	}
	public void setF_rcpc_mem_id(String f_rcpc_mem_id) {
		this.f_rcpc_mem_id = f_rcpc_mem_id;
	}
	public String getF_rcpc_mem_nick() {
		return f_rcpc_mem_nick;
	}
	public void setF_rcpc_mem_nick(String f_rcpc_mem_nick) {
		this.f_rcpc_mem_nick = f_rcpc_mem_nick;
	}
	public String getF_rcpc_content() {
		return f_rcpc_content;
	}
	public void setF_rcpc_content(String f_rcpc_content) {
		this.f_rcpc_content = f_rcpc_content;
	}
	public int getF_rcpc_ref() {
		return f_rcpc_ref;
	}
	public void setF_rcpc_ref(int f_rcpc_ref) {
		this.f_rcpc_ref = f_rcpc_ref;
	}
	public int getF_rcpc_lev() {
		return f_rcpc_lev;
	}
	public void setF_rcpc_lev(int f_rcpc_lev) {
		this.f_rcpc_lev = f_rcpc_lev;
	}
	public int getF_rcpc_seq() {
		return f_rcpc_seq;
	}
	public void setF_rcpc_seq(int f_rcpc_seq) {
		this.f_rcpc_seq = f_rcpc_seq;
	}
	public String getF_rcpc_ip() {
		return f_rcpc_ip;
	}
	public void setF_rcpc_ip(String f_rcpc_ip) {
		this.f_rcpc_ip = f_rcpc_ip;
	}
	public String getF_rcpc_img() {
		return f_rcpc_img;
	}
	public void setF_rcpc_img(String f_rcpc_img) {
		this.f_rcpc_img = f_rcpc_img;
	}
	
	
	@Override
	public String toString() {
		return "CommentBean [f_rcpc_no=" + f_rcpc_no + ", f_rcpc_rcp_num=" + f_rcpc_rcp_num + ", f_rcpc_date="
				+ f_rcpc_date + ", f_rcpc_mem_id=" + f_rcpc_mem_id + ", f_rcpc_mem_nick=" + f_rcpc_mem_nick
				+ ", f_rcpc_content=" + f_rcpc_content + ", f_rcpc_ref=" + f_rcpc_ref + ", f_rcpc_lev=" + f_rcpc_lev
				+ ", f_rcpc_seq=" + f_rcpc_seq + ", f_rcpc_ip=" + f_rcpc_ip + ", f_rcpc_img=" + f_rcpc_img + "]";
	}
	
	
}
