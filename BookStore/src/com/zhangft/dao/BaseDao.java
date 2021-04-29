package com.zhangft.dao;

import com.zhangft.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

/**
 * Dao层的根层
 * @param <T>
 */
public class BaseDao<T> {

    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSourse());
    private Class<T> type;

    public BaseDao() {
        //获取父类的类型,父类是带参数的
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        //获取参数的类型
        type = (Class<T>) superclass.getActualTypeArguments()[0];
    }

    /**
     * 获取一个对象
     * @param sql 传进来的SQL语句
     * @param params 传进来的SQL语句中的占位符
     * @return 返回一个查询的对象
     */
    public T getBean(String sql,Object...params){
        T result = null;
        try {
            result = queryRunner.query(sql,new BeanHandler<>(type),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * 获取一个对象类的集合
     * @param sql 传进来的SQL语句
     * @param params 传进来的SQL语句中的占位符
     * @return 返回一个查询对象的list集合
     */
    public List<T> getBeanList(String sql,Object...params){
        List<T> result = null;
        try {
            result = queryRunner.query(sql,new BeanListHandler<>(type),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * 进行增删改的操作
     * @param sql 传进来的SQL语句
     * @param params 传进来的SQL语句的占位符
     * @return
     */
    public int update(String sql,Object...params){
        int result = 0;
        try {
            result = queryRunner.update(sql,params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * 对单个数据的查询
     * @param sql 传进来的SQL语句
     * @param params 传进来的SQL语句的占位符
     * @return 返回查询的单个数据
     */
    public Object getSingleValue(String sql,Object...params){
        Object result = null;
        try {
            result = queryRunner.query(sql,new ScalarHandler<>(),params);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }
}
