package com.member.db;

import java.sql.Date;

public class MemberBean {
	private int f_mem_num;
	private String f_mem_id;
	private String f_mem_pw;
	private String f_mem_name;
	private String f_mem_phone;
	private String f_mem_postcode;
	private String f_mem_address;
	private String f_mem_detailAddress;
	private String f_mem_extraAddress;
	private String f_mem_email;
	private String f_mem_nick;
	private Date f_mem_date;
	private String f_mem_ip;
	
	public int getF_mem_num() {
		return f_mem_num;
	}
	public void setF_mem_num(int f_mem_num) {
		this.f_mem_num = f_mem_num;
	}
	public String getF_mem_id() {
		return f_mem_id;
	}
	public void setF_mem_id(String f_mem_id) {
		this.f_mem_id = f_mem_id;
	}
	public String getF_mem_pw() {
		return f_mem_pw;
	}
	public void setF_mem_pw(String f_mem_pw) {
		this.f_mem_pw = f_mem_pw;
	}
	public String getF_mem_name() {
		return f_mem_name;
	}
	public void setF_mem_name(String f_mem_name) {
		this.f_mem_name = f_mem_name;
	}
	public String getF_mem_phone() {
		return f_mem_phone;
	}
	public void setF_mem_phone(String f_mem_phone) {
		this.f_mem_phone = f_mem_phone;
	}
	public String getF_mem_postcode() {
		return f_mem_postcode;
	}
	public void setF_mem_postcode(String f_mem_postcode) {
		this.f_mem_postcode = f_mem_postcode;
	}
	public String getF_mem_address() {
		return f_mem_address;
	}
	public void setF_mem_address(String f_mem_address) {
		this.f_mem_address = f_mem_address;
	}
	public String getF_mem_detailAddress() {
		return f_mem_detailAddress;
	}
	public void setF_mem_detailAddress(String f_mem_detailAddress) {
		this.f_mem_detailAddress = f_mem_detailAddress;
	}
	public String getF_mem_extraAddress() {
		return f_mem_extraAddress;
	}
	public void setF_mem_extraAddress(String f_mem_extraAddress) {
		this.f_mem_extraAddress = f_mem_extraAddress;
	}
	public String getF_mem_email() {
		return f_mem_email;
	}
	public void setF_mem_email(String f_mem_email) {
		this.f_mem_email = f_mem_email;
	}
	public String getF_mem_nick() {
		return f_mem_nick;
	}
	public void setF_mem_nick(String f_mem_nick) {
		this.f_mem_nick = f_mem_nick;
	}
	public Date getF_mem_date() {
		return f_mem_date;
	}
	public void setF_mem_date(Date f_mem_date) {
		this.f_mem_date = f_mem_date;
	}
	public String getF_mem_ip() {
		return f_mem_ip;
	}
	public void setF_mem_ip(String f_mem_ip) {
		this.f_mem_ip = f_mem_ip;
	}	
	
	@Override
	public String toString() {
		return "MemberBean [f_mem_num=" + f_mem_num + ", f_mem_id=" + f_mem_id + ", f_mem_pw=" + f_mem_pw
				+ ", f_mem_name=" + f_mem_name + ", f_mem_phone=" + f_mem_phone + ", f_mem_postcode=" + f_mem_postcode
				+ ", f_mem_address=" + f_mem_address + ", f_mem_detailAddress=" + f_mem_detailAddress
				+ ", f_mem_extraAddress=" + f_mem_extraAddress + ", f_mem_email=" + f_mem_email + ", f_mem_nick="
				+ f_mem_nick + ", f_mem_date=" + f_mem_date + ", f_mem_ip=" + f_mem_ip + "]";
	}

}
