package com.learn.test;

import com.learn.bean.Department;
import com.learn.bean.Employee;
import com.learn.dao.EmployeeMapperDynamicSQL;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/18 13:55<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class DynamicSQLTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    //测试if判断动态查询
    @Test
    public void testGetEmpsByConditionIf() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null, "%李%", null, null);
            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
            for (Employee employee1 :emps){
                System.out.println(employee1);
            }
        }finally {
            openSession.close();
        }
    }
    //测试choose分支动态查询
    @Test
    public void testGetEmpsByConditionChoose() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(null,"%李%",null,null);
            List<Employee> employees = mapper.getEmpsByConditionChoose(employee);
            for (Employee employee1 :employees){
                System.out.println(employee1);
            }
        }finally {
            openSession.close();
        }
    }
    //测试Set和If标签动态修改
    @Test
    public void testUpdateEmp() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            Employee employee = new Employee(2,"钱昌松","女",null);
            mapper.updateEmp(employee);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    //测试批量插入
    @Test
    public void testAddEmps() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            ArrayList<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null,"李佳乐","女","LJL168@qq.com",new Department(1)));
            emps.add(new Employee(null,"张嘉豪","女","ZJH168@qq.com",new Department(2)));
            mapper.addEmps(emps);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }

}
