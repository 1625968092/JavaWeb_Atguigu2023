package com.atguigu.schedule.dao;


import com.atguigu.schedule.pojo.SysUser;

/**
 *
 */
public interface SysUserDao {

    /**
     * 向数据库增加一条用户信息
     * @param sysUser 用户信息userName和userPwd封装在sysUser中
     * @return 成功返回1 失败返回0
     */
    int addSysUser(SysUser sysUser);

    SysUser findByUsername(String username);
}
