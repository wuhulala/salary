package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.MonthAllowance;

import java.util.Map;

/**
 * date:2016-05-26 23:20
 */
public interface MonthAllowanceDao {
    Map findByParams(int department, int position, int start, int length);

    MonthAllowance findByParams(int department, int position);
}