package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysScheduleService;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysScheduleServiceImpl;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import com.atguigu.schedule.util.MD5Util;
import com.atguigu.schedule.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController extends BaseController {

    /**
     * 注册时接收用户名检查是否占用
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名
        String username = req.getParameter("username");
        //调用服务层用户处理方法，查询该用户名是否有对应的用户
        SysUser byUsername = userService.findByUsername(username);

        Result result=Result.ok(null);

        //username不为空 表示已经注册过了
        if(null!=byUsername){
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        //将resault转换为JSON字符串给前端
        WebUtil.writeJson(resp,result);

//        //objectMapper
//        ObjectMapper objectMapper=new ObjectMapper();
//        String info = objectMapper.writeValueAsString(result);
//
//        //告诉客户端响应的是JSON字符串
//        resp.setContentType("application/json;charset=utf-8");
//
//        resp.getWriter().write(info);
    }

    /**
     * 无需每次调用都创建一个service
     * 先创建一个service用来调用
     */

    private SysUserService userService= new SysUserServiceImpl();
    private SysScheduleService scheduleService=new SysScheduleServiceImpl();


    /**
     * 接收用户登录请求，完成登录的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收用户名和密码
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);


        SysUser user = new SysUser(null, sysUser.getUsername(), sysUser.getUserPwd());
        //2. 调用服务层方法，根据用户名查询找到用户信息
        SysUser loginUser =userService.findByUsername(user.getUsername());

        Result result=Result.ok(null);

        if(null==loginUser){
            //找不到用户名
            result=Result.build(null,ResultCodeEnum.USERNAME_ERROR );

        }else if(!MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            //找不到用户名
            result=Result.build(null,ResultCodeEnum.PASSWORD_ERROR );

        }else {
            //登录成功，将用户的uid和username响应给客户端
            Map data= new HashMap();
            loginUser.setUserPwd("");
            data.put("loginUser",loginUser);

            //没有错误就登录成功
            result = Result.ok(data);

        }
        //将响应转给客户端
        WebUtil.writeJson(resp,result);

    }

    /**
     * 接收用户注册请求的接口(不是interface，是前端向后端的接口)
     * @param req
     * @param resp
     */
    protected void regist(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        //1.接收前端的JSON字符串参数，并转换为User对象获取信息
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);

        //2.服务层调用方法
        int rows = userService.regist(sysUser);
        //3.注册成功（失败）返回给前端
        Result result = Result.ok(null);
        if(rows<1){
            result = Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        WebUtil.writeJson(resp,result);
    }



    protected void add (HttpServletRequest req,HttpServletResponse resp){
        System.out.println("===================add====================");
    }
}
