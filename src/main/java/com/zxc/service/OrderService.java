package com.zxc.service;

import com.zxc.pojo.Cart;
import com.zxc.pojo.Order;
import com.zxc.pojo.OrderItem;
import com.zxc.pojo.Page;

import java.util.List;

/**
 * @author zhu
 * @create 2021-09-03 21:34
 */
public interface OrderService {
     String createOrder(Cart cart,Integer userId);
     List<Order> showMyOrders(Integer userId);

     List<Order> showAllOrders();

     List<OrderItem>showOrderDetail(String orderId);

     int sendOrder(String orderId);

     int receiveOrder(String orderId);

     Page page(int pageNo, int pageSize);
}
