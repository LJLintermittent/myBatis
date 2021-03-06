package com.learn.bean;

import org.apache.ibatis.type.Alias;
import java.io.Serializable;
import java.util.List;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/17 15:41<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
@Alias("dept")
public class Department implements Serializable {
    private Integer id;
    private String departmentName;
    private List<Employee> emps;

    public Department() {
    }

    public Department(Integer id) {
        this.id = id;
    }

    public List<Employee> getEmps() {
        return emps;
    }

    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
