package com.zxc.dao;

import com.zxc.pojo.Order;

import java.util.List;

/**
 * @author zhu
 * @create 2021-09-03 21:11
 */
public interface OrderDao {
     int saveOrder(Order order);

     List<Order> queryOrdersByUserId(Integer userId);

     List<Order> queryOrders();

     int changeOrderStatus(String orderId,Integer status);

     int queryForPageTotalCount();

     List<Order> queryForItems(int begin, int pageSize);

}
