package com.zxc.web;

import com.zxc.pojo.User;
import com.zxc.service.UserService;
import com.zxc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhu
 * @create 2021-08-20 20:52
 */
public class RegistServlet extends HttpServlet {
    private UserService userService=new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        if("abcde".equalsIgnoreCase(code)){
             if(!userService.existUsername(username)){
                  userService.registUser(new User(null,username,password,email));
                 req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
             }else {
                 req.setAttribute("msg","用户名已存在");
                 req.setAttribute("username",username);
                 req.setAttribute("email",email);
                 System.out.println("用户名已存在");
                 req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
             }
        }else {
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }

    }
}
