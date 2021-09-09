package com.zxc.pojo;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @author zhu
 * @create 2021-09-03 20:59
 */
public class Order {

  private String order_Id;
  private Date create_Time;
  private BigDecimal price;
  private int status=0;
  //0未发货，1已发货，2已完成
  private int user_Id;

  public Order() {
  }

  public Order(String order_Id, Date create_Time, BigDecimal price, int status, int user_Id) {
    this.order_Id = order_Id;
    this.create_Time = create_Time;
    this.price = price;
    this.status = status;
    this.user_Id = user_Id;
  }

  @Override
  public String toString() {
    return "Order{" +
            "order_Id='" + order_Id + '\'' +
            ", create_Time=" + create_Time +
            ", price=" + price +
            ", status=" + status +
            ", user_Id=" + user_Id +
            '}';
  }

  public String getOrder_Id() {
    return order_Id;
  }

  public void setOrder_Id(String order_Id) {
    this.order_Id = order_Id;
  }

  public Date getCreate_Time() {
    return create_Time;
  }

  public void setCreate_Time(Date create_Time) {
    this.create_Time = create_Time;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getUser_Id() {
    return user_Id;
  }

  public void setUser_Id(int user_Id) {
    this.user_Id = user_Id;
  }
}
