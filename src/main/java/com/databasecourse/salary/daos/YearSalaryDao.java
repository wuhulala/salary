package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.YearSalary;

import java.util.Map;

/**
 * date:2016-05-28 14:55
 */
public interface YearSalaryDao {

    YearSalary findByEmployeeId(int id);

    Map findByParams(String searchInfo, int year, int department, int position, int start, int length);
}
