package com.basket.db;

public class BasketBean {
  private int f_bas_num;
  private String f_bas_mem_id;
  private int f_bas_gs_num;
  private int f_bas_amount;
  private String f_bas_size;
  private String f_bas_date;
  
  public int getF_bas_num() {
    return f_bas_num;
  }
  public void setF_bas_num(int f_bas_num) {
    this.f_bas_num = f_bas_num;
  }
  public String getF_bas_mem_id() {
    return f_bas_mem_id;
  }
  public void setF_bas_mem_id(String f_bas_mem_id) {
    this.f_bas_mem_id = f_bas_mem_id;
  }
  public int getF_bas_gs_num() {
    return f_bas_gs_num;
  }
  public void setF_bas_gs_num(int f_bas_gs_num) {
    this.f_bas_gs_num = f_bas_gs_num;
  }
  public int getF_bas_amount() {
    return f_bas_amount;
  }
  public void setF_bas_amount(int f_bas_amount) {
    this.f_bas_amount = f_bas_amount;
  }
  public String getF_bas_size() {
    return f_bas_size;
  }
  public void setF_bas_size(String f_bas_size) {
    this.f_bas_size = f_bas_size;
  }
  public String getF_bas_date() {
    return f_bas_date;
  }
  public void setF_bas_date(String f_bas_date) {
    this.f_bas_date = f_bas_date;
  }
  
  @Override
  public String toString() {
    return "BasketDAO [f_bas_num=" + f_bas_num + ", f_bas_mem_id=" + f_bas_mem_id + ", f_bas_gs_num=" + f_bas_gs_num
        + ", f_bas_amount=" + f_bas_amount + ", f_bas_size=" + f_bas_size + ", f_bas_date=" + f_bas_date + "]";
  }
}
