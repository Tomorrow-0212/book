package com.zhangft.test;

import com.zhangft.bean.User;
import com.zhangft.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class JDBCUtilsTest {
    @Test
    public void testJDBCUtils(){
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourse());
        String sql = "update t_book set price=? where id=?";
        Object[] params = {12.12,21};
        try {
            int result = queryRunner.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void query(){
        QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourse());
        String sql = "select * from t_user";
        try {
            List<User> result = queryRunner.query(sql,new BeanListHandler<User>(User.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
