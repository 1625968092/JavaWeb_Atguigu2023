package com.atguigu.schedule.service;

import com.atguigu.schedule.pojo.SysUser;

/**
 * 定义了以Sys_user表格为核心的业务处理
 */
public interface SysUserService {
    /**
     * 注册用户的方法
     * @param sysUser 要注册的用户名和密码以SysUser对象形式接收
     * @return 成功返回1 失败返回0
     */
    int regist(SysUser sysUser);

    /**
     * 根据用户名获取用户完整信息的方法
     * @param username 用户名
     * @return 找到返回 sysuser对象 找不到 返回null
     */
    SysUser findByUsername(String username);
}
