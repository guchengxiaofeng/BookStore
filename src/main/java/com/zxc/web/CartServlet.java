package com.zxc.web;

import com.google.gson.Gson;
import com.zxc.pojo.Book;
import com.zxc.pojo.Cart;
import com.zxc.pojo.CartItem;
import com.zxc.service.BookService;
import com.zxc.service.impl.BookServiceImpl;
import com.zxc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhu
 * @create 2021-09-03 18:59
 */
public class CartServlet extends BaseServlet {
     private BookService bookService=new BookServiceImpl();
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book=bookService.queryBookById(WebUtils.parseInt(req.getParameter("id"),0));
        CartItem cartItem=new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart==null){
            cart = new Cart();
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

        Gson gson=new Gson();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("lastName",cartItem.getName());
        resultMap.put("totalCount",cart.getTotalCount());
        String json=gson.toJson(resultMap);
        resp.getWriter().write(json);


    }

    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.deleteItem(WebUtils.parseInt(req.getParameter("id"),0));

            resp.sendRedirect(req.getHeader("Referer"));

        }


    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){
            cart.clear();
            resp.sendRedirect(req.getHeader("Referer"));

        }
    }

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart= (Cart) req.getSession().getAttribute("cart");
        if(cart!=null){

            cart.updateCount(WebUtils.parseInt(req.getParameter("id"),0),WebUtils.parseInt(req.getParameter("count"),0));
            resp.sendRedirect(req.getHeader("Referer"));

        }
    }
}
