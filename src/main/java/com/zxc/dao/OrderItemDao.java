package com.zxc.dao;

import com.zxc.pojo.OrderItem;

import java.util.List;

/**
 * @author zhu
 * @create 2021-09-03 21:25
 */
public interface OrderItemDao {
    int saveOrderItem(OrderItem orderItem);

    List<OrderItem> queryOrderItemByOrderId(String orderId);
}
