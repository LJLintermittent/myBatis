package com.learn.dao;

import com.learn.bean.Department;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/17 20:38<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);

}
