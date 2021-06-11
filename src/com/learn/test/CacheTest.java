package com.learn.test;

import com.learn.bean.Employee;
import com.learn.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/18 20:46<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class CacheTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    //测试一级缓存
    @Test
    public void testFirstLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        SqlSession openSession1 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee emp1 = mapper.getEmpById(5);
            System.out.println(emp1);
            EmployeeMapper mapper1 = openSession1.getMapper(EmployeeMapper.class);
//            openSession.clearCache();
            Employee emp2 = mapper.getEmpById(5);
            System.out.println(emp2);
            System.out.println(emp1==emp2);
        }finally {
            openSession.close();
        }
    }
    //测试二级缓存
    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        SqlSession openSession1 = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper1 = openSession1.getMapper(EmployeeMapper.class);

            Employee emp1 = mapper.getEmpById(6);
            System.out.println(emp1);
            openSession.close();

            Employee emp2 = mapper1.getEmpById(6);
            System.out.println(emp2);
            openSession1.close();
        }finally {
            openSession.close();
        }
    }
}
