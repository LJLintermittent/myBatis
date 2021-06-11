package com.learn.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learn.bean.Employee;
import com.learn.bean.User;
import com.learn.dao.EmployeeMapper;
import com.learn.dao.EmployeeMapperAnnotation;
import com.learn.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/16 16:35<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class MyBatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void test() throws IOException {

        // 2、获取sqlSession实例，能直接执行已经映射的sql语句
        // sql的唯一标识：statement Unique identifier matching the statement to use.
        // 执行sql要用的参数：parameter A parameter object to pass to the statement.
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            Employee employee = openSession.selectOne(
                    "com.learn.dao.EmployeeMapper.getEmpById", 2);
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }
    //测试通过id查询的方法
    @Test
    public void test01() throws IOException {
        // 1、获取sqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        // 2、获取sqlSession对象
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            // 3、获取接口的实现类对象
            //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(30);
            System.out.println(mapper.getClass());
            System.out.println(employee);
        } finally {
            openSession.close();
        }

    }
    //测试注解实现sql语句的编写
    @Test
    public void test02() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(2);
            System.out.println(employee);
        }finally {
            openSession.close();
        }

    }
    //测试增加的方法
    @Test
    public void testAddEmp() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null,"毛毛", "女", "MM163@qq.com");
            mapper.addEmp(employee);
            System.out.println(employee.getId());
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
   // 测试修改的方法
    @Test
    public void testUpdateEmp() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(2, "李佳乐", "男", "LJL168@qq.com");
            mapper.updateEmp(employee);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    //测试删除的方法
    @Test
    public void testDeleteEmpById() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            boolean deleteEmpById = mapper.deleteEmpById(4);
            System.out.println(deleteEmpById);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }
    //测试条件查询
    @Test
    public void testGetEmpByIdAndLastName() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(6, "徐欣裕");
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }
    //测试map
    @Test
    public void testGetEmpByMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id",2);
            map.put("last_name","李佳乐");
            map.put("tableName","tbl_employee");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }
    //测试返回值为list的查询
    @Test
    public void testGetEmpByLastNameLike() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            List<Employee> employees = mapper.getEmpByLastNameLike("%李%");
            for(Employee employee :employees){
                System.out.println(employee);
            }
        }finally {
            openSession.close();
        }
    }
    //测试返回值为map的查询(返回一条记录的map)
    @Test
    public void testGetEmpByReturnMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = mapper.getEmpByReturnMap(2);
            System.out.println(map);
        }finally {
            openSession.close();
        }
    }
    //测试返回值为map的查询(返回多条记录的map)
    @Test
    public void testGetEmpByLastNameLikeReturnMap() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<String, Employee> map = mapper.getEmpByLastNameLikeReturnMap("%李%");
            System.out.println(map);
        }finally {
            openSession.close();
        }
    }
    //测试pageHandler分页插件
    @Test
    public void testGetEmpsByPageHandler() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Page<Object> page = PageHelper.startPage(2, 5);
            List<Employee> empsByPageHandler = mapper.getEmpsByPageHandler();
            PageInfo<Employee> pageInfo = new PageInfo<>(empsByPageHandler);
            for (Employee employee :empsByPageHandler) {
                System.out.println(employee);
            }
            System.out.println("总记录数："+pageInfo.getTotal());
            System.out.println("当前页码："+pageInfo.getPageNum());
            System.out.println("总页码："+pageInfo.getPages());
            System.out.println("每页记录数："+pageInfo.getPageSize());
        }finally {
            openSession.close();
        }
    }
    //测试Batch批量添加操作
    @Test
    public void testAddEmps() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            for (int i = 0; i < 5; i++) {
                mapper.addEmps(new Employee(UUID.randomUUID().toString().substring(0,5),"女","1157221853@qq.com"));
            }
            openSession.commit();
        }finally {
            openSession.close();
        }
    }

    @Test
    public void testAddUser() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        try{
            UserMapper mapper = openSession.getMapper(UserMapper.class);
            for (int i = 0; i < 5; i++) {
                mapper.insert(new User(UUID.randomUUID().toString().substring(0,5),"123456789000"));
            }
            openSession.commit();
        }finally {
            openSession.close();
        }

    }

}

