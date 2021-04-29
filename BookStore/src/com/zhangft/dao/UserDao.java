package com.zhangft.dao;

import com.zhangft.bean.User;

public interface UserDao{

    /**
     * 用户注册的方法
     * @param user
     * @return
     */
    public boolean regist(User user);

    /**
     * 用户登录的方法
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 通过名称获取一个对象的方法
     * @param user
     * @return
     */
    public User queryByName(User user);
}
