package com.atguigu.schedule.dao;

import com.atguigu.schedule.pojo.SysSchedule;

import java.util.List;

public interface SysScheduleDao {
    /**
     * 该方法用于向数据库中增加一条日程记录
     * @param schedule 数据以Schedule实体类对象形式传入
     * @return  返回影响数据库记录条数，0失败，大于0成功
     */
    int addSchedule(SysSchedule schedule);

    List <SysSchedule> findAll();

    List<SysSchedule> findItemListByUid(int uid);

    Integer addDefault(int uid);

    Integer updateSchedule(SysSchedule sysSchedule);

    Integer removeSchedule(int sid);
}
