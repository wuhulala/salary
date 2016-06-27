package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.MonthSalary;

import java.util.Map;

/**
 * date:2016-05-28 13:13
 */
public interface MonthSalaryDao {
    Map findByParams(String searchInfo, int year, int month, int department, int position, int start, int length);

    MonthSalary findByParams(int year, int month, Integer id);
}