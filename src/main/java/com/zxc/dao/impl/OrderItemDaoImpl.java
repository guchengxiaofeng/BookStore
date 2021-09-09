package com.zxc.dao.impl;

import com.zxc.dao.BaseDao;
import com.zxc.dao.OrderItemDao;
import com.zxc.pojo.OrderItem;

import java.util.List;

/**
 * @author zhu
 * @create 2021-09-03 21:25
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql="insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotal_Price(),orderItem.getOrder_Id());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql="select id,name,count,price,total_price,order_id from t_order_item where order_id=?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
