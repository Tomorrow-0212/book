package com.zhangft.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    public static<T> T params2bean(HttpServletRequest request, T t){
        // 方法一：稍微有点复杂
        // 通过反射得到参数对象的所有属性值
        Field[] fields = t.getClass().getDeclaredFields();
        // 遍历所有属性值，并为属性赋值且封装到对象中
        for (Field field : fields) {
            String name = field.getName();
            String value = request.getParameter(name);
            try {
                BeanUtils.setProperty(t,name,value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        /*
        // 方法二：死活行不通
        // 获取所有的请求参数封装到map集合中
        Map parameterMap = request.getParameterMap();
        try {
            // 调用BeanUtils工具类的populate方法即可
            BeanUtils.populate(t, parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        return t;
    }
}
