package com.order.db;

import java.sql.Date;

public class OrderBean {
	
    private int f_or_num;  			//주문번호(DB테이블)
    private String f_or_trade_num;  //주문번호(사용자확인용)
    
    private int f_or_gs_num;		// 주문상품 번호
    private String f_or_gs_name;	// 주문상품 이름
    private int f_or_bas_amount; 	// 주문 수량
    
    private String f_or_mem_postcode;	// 회원가입 우편번호
    private String f_or_mem_address;	// 회원가입한 주소 1
    private String f_or_mem_detailAddress;	// 회원가입한 주소 2
    private String f_or_mem_extraAddress;	// 회원가입한 주소 2
    private String f_or_mem_id; 	// 주문한 사람 ID
   
	private String f_or_rec_name; 	// 받는 사람 이름
    private String f_or_rec_postcode; 	// 받는 사람 우편번호
    private String f_or_rec_address; 	// 받는 사람 주소
    private String f_or_rec_detailAddress; 	// 받는 사람 주소2
    private String f_or_rec_extraAddress; 	// 받는 사람 주소2
    private String f_or_rec_phone;  // 받는 사람 전화번호
    private String f_or_memo;  		// 배송 메세지
    
    private int f_or_sum;  		 	// 총합 금액
    private String f_or_trade_type;	// 결제 타입
    private String f_or_trade_payer; // 결제자 
    private Date f_or_trade_date;  	// 결제시간
    
    private String f_or_trans_num;  //택배 운송장번호 
    private Date f_or_date; 		// 시간정보(DB테이블)
    private int f_or_status; 		// 주문상태

    
    public int getF_or_num() {
		return f_or_num;
	}
	public void setF_or_num(int f_or_num) {
		this.f_or_num = f_or_num;
	}
	public String getF_or_trade_num() {
		return f_or_trade_num;
	}
	public void setF_or_trade_num(String f_or_trade_num) {
		this.f_or_trade_num = f_or_trade_num;
	}
	public int getF_or_gs_num() {
		return f_or_gs_num;
	}
	public void setF_or_gs_num(int f_or_gs_num) {
		this.f_or_gs_num = f_or_gs_num;
	}
	public String getF_or_gs_name() {
		return f_or_gs_name;
	}
	public void setF_or_gs_name(String f_or_gs_name) {
		this.f_or_gs_name = f_or_gs_name;
	}
	public int getF_or_bas_amount() {
		return f_or_bas_amount;
	}
	public void setF_or_bas_amount(int f_or_bas_amount) {
		this.f_or_bas_amount = f_or_bas_amount;
	}
	public String getF_or_mem_postcode() {
		return f_or_mem_postcode;
	}
	public void setF_or_mem_postcode(String f_or_mem_postcode) {
		this.f_or_mem_postcode = f_or_mem_postcode;
	}
	public String getF_or_mem_address() {
		return f_or_mem_address;
	}
	public void setF_or_mem_address(String f_or_mem_address) {
		this.f_or_mem_address = f_or_mem_address;
	}
	public String getF_or_mem_detailAddress() {
		return f_or_mem_detailAddress;
	}
	public void setF_or_mem_detailAddress(String f_or_mem_detailAddress) {
		this.f_or_mem_detailAddress = f_or_mem_detailAddress;
	}
	public String getF_or_mem_extraAddress() {
		return f_or_mem_extraAddress;
	}
	public void setF_or_mem_extraAddress(String f_or_mem_extraAddress) {
		this.f_or_mem_extraAddress = f_or_mem_extraAddress;
	}
	public String getF_or_mem_id() {
		return f_or_mem_id;
	}
	public void setF_or_mem_id(String f_or_mem_id) {
		this.f_or_mem_id = f_or_mem_id;
	}
	public String getF_or_rec_name() {
		return f_or_rec_name;
	}
	public void setF_or_rec_name(String f_or_rec_name) {
		this.f_or_rec_name = f_or_rec_name;
	}
	public String getF_or_rec_postcode() {
		return f_or_rec_postcode;
	}
	public void setF_or_rec_postcode(String f_or_rec_postcode) {
		this.f_or_rec_postcode = f_or_rec_postcode;
	}
	public String getF_or_rec_address() {
		return f_or_rec_address;
	}
	public void setF_or_rec_address(String f_or_rec_address) {
		this.f_or_rec_address = f_or_rec_address;
	}
	public String getF_or_rec_detailAddress() {
		return f_or_rec_detailAddress;
	}
	public void setF_or_rec_detailAddress(String f_or_rec_detailAddress) {
		this.f_or_rec_detailAddress = f_or_rec_detailAddress;
	}
	public String getF_or_rec_extraAddress() {
		return f_or_rec_extraAddress;
	}
	public void setF_or_rec_extraAddress(String f_or_rec_extraAddress) {
		this.f_or_rec_extraAddress = f_or_rec_extraAddress;
	}
	public String getF_or_rec_phone() {
		return f_or_rec_phone;
	}
	public void setF_or_rec_phone(String f_or_rec_phone) {
		this.f_or_rec_phone = f_or_rec_phone;
	}
	public String getF_or_memo() {
		return f_or_memo;
	}
	public void setF_or_memo(String f_or_memo) {
		this.f_or_memo = f_or_memo;
	}
	public int getF_or_sum() {
		return f_or_sum;
	}
	public void setF_or_sum(int f_or_sum) {
		this.f_or_sum = f_or_sum;
	}
	public String getF_or_trade_type() {
		return f_or_trade_type;
	}
	public void setF_or_trade_type(String f_or_trade_type) {
		this.f_or_trade_type = f_or_trade_type;
	}
	public String getF_or_trade_payer() {
		return f_or_trade_payer;
	}
	public void setF_or_trade_payer(String f_or_trade_payer) {
		this.f_or_trade_payer = f_or_trade_payer;
	}
	public Date getF_or_trade_date() {
		return f_or_trade_date;
	}
	public void setF_or_trade_date(Date f_or_trade_date) {
		this.f_or_trade_date = f_or_trade_date;
	}
	public String getF_or_trans_num() {
		return f_or_trans_num;
	}
	public void setF_or_trans_num(String f_or_trans_num) {
		this.f_or_trans_num = f_or_trans_num;
	}
	public Date getF_or_date() {
		return f_or_date;
	}
	public void setF_or_date(Date f_or_date) {
		this.f_or_date = f_or_date;
	}
	public int getF_or_status() {
		return f_or_status;
	}
	public void setF_or_status(int f_or_status) {
		this.f_or_status = f_or_status;
	}
	@Override
	public String toString() {
		return "OrderBean [f_or_num=" + f_or_num + ", f_or_trade_num=" + f_or_trade_num + ", f_or_gs_num=" + f_or_gs_num
				+ ", f_or_gs_name=" + f_or_gs_name + ", f_or_bas_amount=" + f_or_bas_amount + ", f_or_mem_postcode="
				+ f_or_mem_postcode + ", f_or_mem_address=" + f_or_mem_address + ", f_or_mem_detailAddress="
				+ f_or_mem_detailAddress + ", f_or_mem_extraAddress=" + f_or_mem_extraAddress + ", f_or_mem_id="
				+ f_or_mem_id + ", f_or_rec_name=" + f_or_rec_name + ", f_or_rec_postcode=" + f_or_rec_postcode
				+ ", f_or_rec_address=" + f_or_rec_address + ", f_or_rec_detailAddress=" + f_or_rec_detailAddress
				+ ", f_or_rec_extraAddress=" + f_or_rec_extraAddress + ", f_or_rec_phone=" + f_or_rec_phone
				+ ", f_or_memo=" + f_or_memo + ", f_or_sum=" + f_or_sum + ", f_or_trade_type=" + f_or_trade_type
				+ ", f_or_trade_payer=" + f_or_trade_payer + ", f_or_trade_date=" + f_or_trade_date
				+ ", f_or_trans_num=" + f_or_trans_num + ", f_or_date=" + f_or_date + ", f_or_status=" + f_or_status
				+ "]";
	}
    
}
