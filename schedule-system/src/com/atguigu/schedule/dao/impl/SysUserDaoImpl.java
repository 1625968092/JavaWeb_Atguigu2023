package com.atguigu.schedule.dao.impl;

import com.atguigu.schedule.dao.BaseDao;
import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.pojo.SysUser;

import java.util.List;

public class SysUserDaoImpl extends BaseDao implements SysUserDao {

    /**
     * 添加用户信息
     * @param sysUser 用户信息userName和userPwd封装在sysUser中
     * @return
     */
    @Override
    public int addSysUser(SysUser sysUser) {
        String sql="insert into sys_user values(DEFAULT,?,?)";
        int rows = baseUpdate(sql, sysUser.getUsername(), sysUser.getUserPwd());
        return rows;
    }

    @Override
    public SysUser findByUsername(String username) {
        String sql="select uid,username,user_pwd userPwd from sys_user where username=?";
        List<SysUser> sysUserList=baseQuery(SysUser.class,sql,username);
        return sysUserList!=null && sysUserList.size()>0 ?sysUserList.get(0):null;
    }
}
