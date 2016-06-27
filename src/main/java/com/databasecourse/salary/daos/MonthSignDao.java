package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.MonthSign;

import java.util.List;
import java.util.Map;

/**
 * date:2016-05-26 15:18
 */
public interface MonthSignDao {
    MonthSign findByEmployeeId(Integer id,int year,int month);

    Map findByParams(int year, int month, String searchInfo, int department, int position, int start, int length);

    List findByParams(int year, int month);
}
