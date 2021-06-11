package com.learn.test;

import com.learn.bean.User;
import com.learn.bean.UserExample;
import com.learn.dao.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/19 10:53<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public class testMBG {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
    //生成javaBean对象，Mapper接口和sql映射文件
    @Test
    public void testMBG() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }
    @Test
    public void test1() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = openSession.getMapper(UserMapper.class);
            User user = mapper.selectByPrimaryKey(1);
            System.out.println(user);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test2() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = openSession.getMapper(UserMapper.class);
            List<User> list = mapper.selectByExample(null);
            for (User users : list) {
                System.out.println(users);
            }
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test3() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = openSession.getMapper(UserMapper.class);
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andUsernameLike("%李%");
            criteria.andPasswordLike("%123%");
            List<User> list = mapper.selectByExample(example);
            for (User user :list) {
                System.out.println(user);
            }
        }finally {
            openSession.close();
        }
    }

}
