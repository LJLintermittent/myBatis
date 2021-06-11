package com.learn.test;

import com.learn.bean.Department;
import com.learn.bean.Employee;
import com.learn.dao.DepartmentMapper;
import com.learn.dao.EmployeeMapperPlus;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/17 15:24<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class MyBatisTestPlus {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testGetEmpById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpById(2);
            System.out.println(employee);
        } finally {
            openSession.close();
        }
    }

    //级联查询
    @Test
    public void testGetEmpAndDept() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee empAndDept = mapper.getEmpAndDept(6);
            System.out.println(empAndDept);
            System.out.println(empAndDept.getDepartment());
        } finally {
            openSession.close();
        }

    }
    //测试department表的查询
    @Test
    public void testGetDeptById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptById(2);
            System.out.println(department);
        }finally {
            openSession.close();
        }
    }
    //测试分步查询
    @Test
    public void testGetEmpByIdStep() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpByIdStep(5);
            System.out.println(employee);
            System.out.println(employee.getDepartment());
        }finally {
            openSession.close();
        }
    }
    //测试集合类型属性封装
    @Test
    public void testGetDeptByIdPlus() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdPlus(1);
            System.out.println(department);
            System.out.println(department.getEmps());
        }finally {
            openSession.close();
        }
    }
    //测试集合类型的分步查询
    @Test
    public void testGetDeptByIdStep() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
            Department department = mapper.getDeptByIdStep(1);
            System.out.println(department);
            System.out.println(department.getEmps());
        }finally {
            openSession.close();
        }
    }
}
