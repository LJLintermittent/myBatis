package com.learn.dao;

import com.learn.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/18 13:36<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public void addEmps(@Param("emps") List<Employee> emps);

}
