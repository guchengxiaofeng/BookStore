package com.zxc.dao.impl;

import com.zxc.dao.BaseDao;
import com.zxc.dao.OrderDao;
import com.zxc.pojo.Order;

import java.util.List;

/**
 * @author zhu
 * @create 2021-09-03 21:18
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {

 @Override
 public int saveOrder(Order order) {
      String sql="insert into t_order(order_id,create_time,price,status,user_id) values(?,?,?,?,?)";
      return update(sql,order.getOrder_Id(),order.getCreate_Time(),order.getPrice(),order.getStatus(),order.getUser_Id());
 }

    @Override
    public List<Order> queryOrdersByUserId(Integer userId) {
        String sql="select order_id,create_time,price,status,user_id  from t_order where user_id=?";
        return queryForList(Order.class,sql,userId);
    }

    @Override
    public List<Order> queryOrders() {
        String sql="select order_id,create_time,price,status,user_id  from t_order";
        return queryForList(Order.class,sql);
    }

    @Override
    public int changeOrderStatus(String orderId, Integer status) {
        String sql="update t_order set status=? where order_id=?";
        return update(sql,status,orderId);
    }

    @Override
    public int queryForPageTotalCount() {
        String sql="select count(*) from t_order";
        return ((Long)queryForValue(sql)).intValue();
    }

    @Override
    public List<Order> queryForItems(int begin, int pageSize) {
        String sql="select order_id,create_time,price,status,user_id from t_order limit ?,?";
        return queryForList(Order.class,sql,begin,pageSize);
    }
}
