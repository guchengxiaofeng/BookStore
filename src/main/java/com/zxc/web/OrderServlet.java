package com.zxc.web;

import com.google.gson.Gson;
import com.zxc.pojo.Cart;
import com.zxc.pojo.Page;
import com.zxc.pojo.User;
import com.zxc.service.OrderService;
import com.zxc.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhu
 * @create 2021-09-03 21:57
 */
public class OrderServlet extends BaseServlet {
    private OrderService orderService = new OrderServiceImpl();

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User loginUser = (User) req.getSession().getAttribute("user");
        String orderId = "";
        if (cart != null) {
            orderId = orderService.createOrder(cart, loginUser.getId());
        }
        req.getSession().setAttribute("orderId", orderId);
        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");

        Integer userId = user.getId();
        Gson gson = new Gson();

        String json=gson.toJson(orderService.showMyOrders(userId));

        resp.getWriter().write(json);
    }
    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();

        String json=gson.toJson(orderService.showAllOrders());

        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().write(json);
    }
    protected void sendOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId=req.getParameter("orderId");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        if(orderService.sendOrder(orderId)!=0){
            boolean isSuccess=true;
            Gson gson=new Gson();
            String json=gson.toJson(isSuccess);
            resp.getWriter().write(json);
            resp.sendRedirect(req.getContextPath()+"/manager/orderServlet?action=page&pageNo=1");
        }else {
            boolean isSuccess=false;
            Gson gson=new Gson();
            String json=gson.toJson(isSuccess);
            resp.getWriter().write(json);
        }

    }
    protected void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId=req.getParameter("orderId");
        Gson gson = new Gson();

        String json=gson.toJson(orderService.showOrderDetail(orderId));
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.getWriter().write(json);

    }
    protected void receiveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId=req.getParameter("orderId");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        if(orderService.receiveOrder(orderId)!=0){
            boolean isSuccess=true;
            Gson gson=new Gson();
            String json=gson.toJson(isSuccess);
            resp.getWriter().write(json);
        }else {
            boolean isSuccess=false;
            Gson gson=new Gson();
            String json=gson.toJson(isSuccess);
            resp.getWriter().write(json);
        }
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo=1;
        if(req.getParameter("pageNo")!=null){
            pageNo=Integer.parseInt(req.getParameter("pageNo"));
        }
        int pageSize= Page.PAGE_SIZE;
        Page page=orderService.page(pageNo,pageSize);
        page.setUrl("manager/orderServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);
    }
}
