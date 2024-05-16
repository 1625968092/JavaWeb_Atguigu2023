package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.pojo.SysSchedule;
import com.atguigu.schedule.service.SysScheduleService;
import com.atguigu.schedule.service.impl.SysScheduleServiceImpl;
import com.atguigu.schedule.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 增加日程   //add
 * 删除日程   //delete
 * 修改日程   //update
 * 查找日程   //find
 */


@WebServlet("/schedule/*")
public class SysScheduleController extends BaseController {
    private SysScheduleService sysScheduleService=new SysScheduleServiceImpl();


    protected void findAllSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求传来的uid参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        // 查询用户的所有日程记录
        List<SysSchedule> itemList=sysScheduleService.findItemListByUid(uid);

        // 将用户所有日程放入一个resault对象
        Map data=new HashMap();
        data.put("itemList",itemList);

        Result result = Result.ok(data);
        //将resault对象转换为一个json响应给前端
        WebUtil.writeJson(resp,result);
    }


    //添加一条没有内容的记录
    protected void addDefaultSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求传来的uid参数
        int uid = Integer.parseInt(req.getParameter("uid"));
        //调用服务层方法，向数据库中增加一条空记录
        sysScheduleService.addDefault(uid);

        WebUtil.writeJson(resp,Result.ok(null));
    }


    //根据发送来的事件sid更新 事件内容
    protected void updateSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求体中的JSON串，转换为一个schedule对象
        SysSchedule sysSchedule = WebUtil.readJson(req, SysSchedule.class);
        //调用服务层方法 将信息更新进入数据库
        sysScheduleService.updateSchedule(sysSchedule);

        WebUtil.writeJson(resp,Result.ok(null));
    }

    //根据sid移除日程
    protected void removeSchedule(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Stringsid = req.getParameter("sid");
        int sid=Integer.parseInt(Stringsid);

        //调用服务层方法删除数据
        sysScheduleService.removeSchedule(sid);
        //响应成功信息
        WebUtil.writeJson(resp,Result.ok(null));
    }
}
