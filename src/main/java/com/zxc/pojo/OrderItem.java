package com.zxc.pojo;

import java.math.BigDecimal;

/**
 * @author zhu
 * @create 2021-09-03 21:04
 */
public class OrderItem {
     private int id;
     private String name;
     private int count;
     private BigDecimal price;
     private BigDecimal total_Price;
     private String order_Id;

    public OrderItem() {
    }

    public OrderItem(int id, String name, int count, BigDecimal price, BigDecimal total_Price, String order_Id) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.total_Price = total_Price;
        this.order_Id = order_Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal_Price() {
        return total_Price;
    }

    public void setTotal_Price(BigDecimal total_Price) {
        this.total_Price = total_Price;
    }

    public String getOrder_Id() {
        return order_Id;
    }

    public void setOrder_Id(String order_Id) {
        this.order_Id = order_Id;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", total_Price=" + total_Price +
                ", order_Id='" + order_Id + '\'' +
                '}';
    }
}
