package com.zhangft.dao.daoImpl;

import com.zhangft.bean.User;
import com.zhangft.dao.BaseDao;
import com.zhangft.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

    /**
     * 注册的方法
     * @param user 表单传进来的数据
     * @return 是否注册成功
     */
    @Override
    public boolean regist(User user) {
        String sql = "insert into t_user (username,password,email) values (?,?,?)";
        Object[] params = {user.getUsername(),user.getPassword(),user.getEmail()};
        int result = this.update(sql,params);
        return result > 0;
    }

    /**
     * 登录的方法
     * @param user 表单传进来的数据
     * @return 返回是否有此数据
     */
    @Override
    public User login(User user) {
        String sql = "select * from t_user where username=? and password=?";
        Object[] params = {user.getUsername(),user.getPassword()};
        User resltUser = this.getBean(sql,params);
        return resltUser;
    }

    /**
     * 通过用户名称获得对象的方法
     * @param user
     * @return
     */
    @Override
    public User queryByName(User user) {
        String sql = "select * from t_user where username=?";
        User resultUser = this.getBean(sql,user.getUsername());
        return resultUser;
    }
}
