package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.Employee;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * date:2016-05-25 20:17
 */
public interface EmployeeDao {
    /**
     * 查询员工
     * @param timestart
     * @param timeEnd
     * @param searchInfo
     * @param department
     * @param position
     * @param start
     * @param length
     * @return
     */
    Map findByParams(Date timestart, Date timeEnd, String searchInfo, int department, int position, int start, int length);

    List findActiveEmployees();

    /**
     * 员工登录
     * @param username
     * @param password
     * @return
     */
    Employee findByNameandPass(String username, String password);
}
