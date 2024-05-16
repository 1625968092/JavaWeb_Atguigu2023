package com.atguigu.schedule.service.impl;

import com.atguigu.schedule.dao.SysUserDao;
import com.atguigu.schedule.dao.impl.SysUserDaoImpl;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.util.MD5Util;


public class SysUserServiceImpl implements SysUserService {

    private SysUserDao userDao=new SysUserDaoImpl();

    @Override
    public int regist(SysUser sysUser) {
        //将用户的明文密码转换为密文密码
//        String userPwd = sysUser.getUserPwd();
//        String encrypt = MD5Util.encrypt(userPwd);
//        sysUser.setUserPwd(encrypt);
        sysUser.setUserPwd(MD5Util.encrypt(sysUser.getUserPwd()));

        //调用dao层做数据库操作 将sysUser存入数据库
        return userDao.addSysUser(sysUser);
    }

    /**
     * 通过用户名查找用户信息
     * @param username 用户名
     * @return 成功返回sysuser对象 失败返回null
     */
    @Override
    public SysUser findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
