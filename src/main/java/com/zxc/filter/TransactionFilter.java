package com.zxc.filter;

import com.zxc.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author zhu
 * @create 2021-09-05 16:40
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest, servletResponse);

            JdbcUtils.commitAndClose();
        }catch (Exception throwables){
            JdbcUtils.rollbackAndClose();
            throwables.printStackTrace();
        }
    }

     @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
