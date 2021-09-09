package com.zxc.service.impl;

import com.zxc.dao.OrderDao;
import com.zxc.dao.impl.OrderDaoImpl;
import com.zxc.dao.OrderItemDao;
import com.zxc.dao.impl.OrderItemDaoImpl;
import com.zxc.pojo.*;
import com.zxc.service.BookService;
import com.zxc.service.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhu
 * @create 2021-09-03 21:36
 */
public class OrderServiceImpl implements OrderService {
   private OrderDao orderDao = new OrderDaoImpl();
   private OrderItemDao orderItemDao=new OrderItemDaoImpl();
   private BookService bookService=new BookServiceImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(System.currentTimeMillis()), cart.getTotalPrice(), 0, userId);
        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder_Id(orderId);
            orderItem.setName(entry.getValue().getName());
            orderItem.setCount(entry.getValue().getCount());
            orderItem.setPrice(entry.getValue().getPrice());
            orderItem.setTotal_Price(entry.getValue().getTotalPrice());
            orderItemDao.saveOrderItem(orderItem);


            Book book=bookService.queryBookById(entry.getValue().getId());
            book.setSales(book.getSales()+orderItem.getCount());
            book.setStock(book.getStock()-orderItem.getCount());
            bookService.updateBook(book);
        }
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showMyOrders(Integer userId) {
       return orderDao.queryOrdersByUserId(userId);
    }

    @Override
    public List<Order> showAllOrders() {
        return orderDao.queryOrders();
    }

    @Override
    public List<OrderItem> showOrderDetail(String orderId) {
        return orderItemDao.queryOrderItemByOrderId(orderId);
    }

    @Override
    public int sendOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId,1);
    }

    @Override
    public int receiveOrder(String orderId) {
        return orderDao.changeOrderStatus(orderId,2);
    }

    public Page page(int pageNo, int pageSize) {
        Page<Order> page = new Page();
        page.setPageTotalCount(orderDao.queryForPageTotalCount());
        page.setPageSize(pageSize);
        page.setPageTotal();
        page.setPageNo(pageNo);
        page.setItems(orderDao.queryForItems((page.getPageNo()-1)*pageSize,pageSize));

        return page;
    }
}
