package com.learn.dao;

import com.learn.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/16 20:07<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id =#{id}")
    Employee getEmpById(Integer id);
}
