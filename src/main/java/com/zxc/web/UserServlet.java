package com.zxc.web;

import com.google.gson.Gson;
import com.zxc.pojo.User;
import com.zxc.service.UserService;
import com.zxc.service.impl.UserServiceImpl;
import com.zxc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author zhu
 * @create 2021-08-22 21:59
 */
public class UserServlet extends BaseServlet {
    private UserService userService=new UserServiceImpl();

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username=req.getParameter("username");
        boolean existsUsername=userService.existUsername(username);
        Gson gson=new Gson();

        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("existsUsername",existsUsername);
        String json=gson.toJson(resultMap);

        resp.getWriter().write(json);
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          req.getSession().invalidate();
          resp.sendRedirect(req.getContextPath());
    }

    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        User loginUser = WebUtils.copyParamToBean(req.getParameterMap(),new User());
        User loginUserForCheck=userService.loginUser(loginUser);
        if(loginUserForCheck!=null){
            HttpSession session=req.getSession();
            session.setAttribute("user",loginUserForCheck);
//            Cookie cookie = new Cookie("JSESSIONID", session.getId());
//            resp.addCookie(cookie);


            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else {

            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",loginUser.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        String token=(String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code=req.getParameter("code");
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());

        if(token!=null &&token.equalsIgnoreCase(code)){
            if(!userService.existUsername(user.getUsername())){
                userService.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
            }else {
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
                System.out.println("用户名已存在");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        }else {
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

}
