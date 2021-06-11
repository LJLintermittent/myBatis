package com.learn.dao;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/20 11:18<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args = java.sql.Statement.class)
})
public class FirstPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println(invocation.getMethod());
        Object target = invocation.getTarget();
        System.out.println("拦截到的对象："+target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("sql语句用的参数是："+value);
        metaObject.setValue("parameterHandler.parameterObject",5);
        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println(target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件的配置信息"+properties);
    }
}
