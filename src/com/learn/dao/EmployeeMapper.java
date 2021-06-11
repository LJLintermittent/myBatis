package com.learn.dao;

import com.learn.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 一些声明信息
 * Description: <br/>
 * date: 2020/7/16 17:07<br/>
 *
 * @author ${李佳乐}<br/>
 * @since JDK 1.8
 */
public interface EmployeeMapper {
     @MapKey("last_name")
     public Map<String,Employee> getEmpByLastNameLikeReturnMap(String last_name);

     public Map<String,Object> getEmpByReturnMap(Integer id);

     public List<Employee> getEmpByLastNameLike(String last_name);

     public Employee getEmpByMap(Map<String,Object> map);

     public Employee getEmpByIdAndLastName(@Param("id") Integer id,@Param("last_name") String last_name);

     public Employee getEmpById(Integer id);

     public void addEmp(Employee employee);

     public void updateEmp(Employee employee);

     public boolean deleteEmpById(Integer id);

     public List<Employee> getEmpsByPageHandler();

     public Long addEmps(Employee employee);


}
