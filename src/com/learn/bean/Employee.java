package com.learn.bean;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/16 16:23<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
@Alias("emp")
public class Employee implements Serializable {

    private  Integer id;
    private String last_name;
    private String gender;
    private String email;
    private Department department;

    public Employee() {
        super();
    }

    public Employee(Integer id, String last_name, String gender, String email) {
        this.id = id;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
    }

    public Employee(Integer id, String last_name, String gender, String email, Department department) {
        super();
        this.id = id;
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public Employee( String last_name, String gender, String email) {
        super();
        this.last_name = last_name;
        this.gender = gender;
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", last_name='" + last_name + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
