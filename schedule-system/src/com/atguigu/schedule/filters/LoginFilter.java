package com.atguigu.schedule.filters;

import com.atguigu.schedule.pojo.SysUser;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

//@WebFilter(urlPatterns = {"/showSchedule.html","/schedule/*"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        //获得session域对象
        HttpSession session = request.getSession();
        //从session获得用户登录的域对象
        SysUser sysUser = (SysUser)session.getAttribute("sysUser");
        //判断域对象是否为空
        if(null==sysUser){
            //没登录到login.html
            response.sendRedirect("login.html");
        }else {
//           登陆过放行
            filterChain.doFilter(request,response);
        }
    }
}
