package com.zhangft.test;

import com.zhangft.bean.User;
import com.zhangft.dao.daoImpl.UserDaoImpl;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void userDaoTest(){
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = new User(1,"admin","admin","zhangsan@163.com");
        //boolean b = userDaoImpl.regist(user);
        //System.out.println(b);
        System.out.println(userDaoImpl.login(user));
    }

    @Test
    public void userDaoTest2(){
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        User user = new User(1,"12312","admin","zhangsan@163.com");
        System.out.println(userDaoImpl.queryByName(user));
    }
}
