package com.learn.dao;

import com.learn.bean.Employee;

import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/17 15:22<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer deptId);
}
