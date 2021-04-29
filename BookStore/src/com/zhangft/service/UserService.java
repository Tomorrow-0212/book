package com.zhangft.service;

import com.zhangft.bean.User;
import com.zhangft.dao.UserDao;
import com.zhangft.dao.daoImpl.UserDaoImpl;

public class UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 用户注册的方法
     * @param user
     * @return
     */
    public boolean regist(User user){
        return userDao.regist(user);
    }

    /**
     * 用户登录的方法
     * @param user
     * @return
     */
    public User login(User user){
        return userDao.login(user);
    }

    /**
     * 通过用户的名称获得对象的方法
     * @param user
     * @return
     */
    public User queryByName(User user){
        return userDao.queryByName(user);
    }
}
