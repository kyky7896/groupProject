package com.goodres.db;

public class GoodResBoardBean {	
	
	private int f_gr_num;			// 글번호
	private String f_gr_name;		// 상호명
	private String f_gr_gu;			// 구군
	private String f_gr_addr;		// 주소
	private String f_gr_detailAddr;	// 상세주소
	private double f_gr_lati;		// 위도
	private double f_gr_long;		// 경도
	private String f_gr_tel;		// 연락처
	private String f_gr_hp;			// 홈페이지 주소
	private String f_gr_open;		// 영업시간
	private String f_gr_menu;		// 대표메뉴 / 메뉴
	private String f_gr_img;		// 이미지 URL
	private String f_gr_thumb;		// 썸네일 이미지 URL
	private String f_gr_eximg;		// 사진추가	
	private double f_gr_staravg;	// 별점평균
	private int f_gr_cnt;			// 조회수
	private int f_gr_rv_cnt;		// 리뷰수
	private String f_gr_content;	// 글상세내용
	private String f_gr_foodtype;   // 업종
		
	
	public int getF_gr_num() {
		return f_gr_num;
	}



	public void setF_gr_num(int f_gr_num) {
		this.f_gr_num = f_gr_num;
	}



	public String getF_gr_name() {
		return f_gr_name;
	}



	public void setF_gr_name(String f_gr_name) {
		this.f_gr_name = f_gr_name;
	}



	public String getF_gr_gu() {
		return f_gr_gu;
	}



	public void setF_gr_gu(String f_gr_gu) {
		this.f_gr_gu = f_gr_gu;
	}



	public String getF_gr_addr() {
		return f_gr_addr;
	}



	public void setF_gr_addr(String f_gr_addr) {
		this.f_gr_addr = f_gr_addr;
	}



	public String getF_gr_detailAddr() {
		return f_gr_detailAddr;
	}



	public void setF_gr_detailAddr(String f_gr_detailAddr) {
		this.f_gr_detailAddr = f_gr_detailAddr;
	}



	public double getF_gr_lati() {
		return f_gr_lati;
	}



	public void setF_gr_lati(double f_gr_lati) {
		this.f_gr_lati = f_gr_lati;
	}



	public double getF_gr_long() {
		return f_gr_long;
	}



	public void setF_gr_long(double f_gr_long) {
		this.f_gr_long = f_gr_long;
	}



	public String getF_gr_tel() {
		return f_gr_tel;
	}



	public void setF_gr_tel(String f_gr_tel) {
		this.f_gr_tel = f_gr_tel;
	}



	public String getF_gr_hp() {
		return f_gr_hp;
	}



	public void setF_gr_hp(String f_gr_hp) {
		this.f_gr_hp = f_gr_hp;
	}



	public String getF_gr_open() {
		return f_gr_open;
	}



	public void setF_gr_open(String f_gr_open) {
		this.f_gr_open = f_gr_open;
	}



	public String getF_gr_menu() {
		return f_gr_menu;
	}



	public void setF_gr_menu(String f_gr_menu) {
		this.f_gr_menu = f_gr_menu;
	}



	public String getF_gr_img() {
		return f_gr_img;
	}



	public void setF_gr_img(String f_gr_img) {
		this.f_gr_img = f_gr_img;
	}



	public String getF_gr_thumb() {
		return f_gr_thumb;
	}



	public void setF_gr_thumb(String f_gr_thumb) {
		this.f_gr_thumb = f_gr_thumb;
	}



	public String getF_gr_eximg() {
		return f_gr_eximg;
	}



	public void setF_gr_eximg(String f_gr_eximg) {
		this.f_gr_eximg = f_gr_eximg;
	}



	public double getF_gr_staravg() {
		return f_gr_staravg;
	}



	public void setF_gr_staravg(double f_gr_staravg) {
		this.f_gr_staravg = f_gr_staravg;
	}



	public int getF_gr_cnt() {
		return f_gr_cnt;
	}



	public void setF_gr_cnt(int f_gr_cnt) {
		this.f_gr_cnt = f_gr_cnt;
	}



	public int getF_gr_rv_cnt() {
		return f_gr_rv_cnt;
	}



	public void setF_gr_rv_cnt(int f_gr_rv_cnt) {
		this.f_gr_rv_cnt = f_gr_rv_cnt;
	}



	public String getF_gr_content() {
		return f_gr_content;
	}



	public void setF_gr_content(String f_gr_content) {
		this.f_gr_content = f_gr_content;
	}



	public String getF_gr_foodtype() {
		return f_gr_foodtype;
	}



	public void setF_gr_foodtype(String f_gr_foodtype) {
		this.f_gr_foodtype = f_gr_foodtype;
	}



	@Override
	public String toString() {
		return "GoodResBoardBean [f_gr_num=" + f_gr_num + ", f_gr_name=" + f_gr_name + ", f_gr_gu=" + f_gr_gu
				+ ", f_gr_addr=" + f_gr_addr + ", f_gr_detailAddr=" + f_gr_detailAddr + ", f_gr_lati=" + f_gr_lati
				+ ", f_gr_long=" + f_gr_long + ", f_gr_tel=" + f_gr_tel + ", f_gr_hp=" + f_gr_hp + ", f_gr_open="
				+ f_gr_open + ", f_gr_menu=" + f_gr_menu + ", f_gr_img=" + f_gr_img + ", f_gr_thumb=" + f_gr_thumb
				+ ", f_gr_eximg=" + f_gr_eximg + ", f_gr_staravg=" + f_gr_staravg + ", f_gr_cnt=" + f_gr_cnt
				+ ", f_gr_rv_cnt=" + f_gr_rv_cnt + ", f_gr_content=" + f_gr_content + ", f_gr_foodtype=" + f_gr_foodtype
				+ "]";
	}

	
}
