package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.DaySign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * date:2016-05-26 10:57
 */
public interface DaySignDao {
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
    Map findByParams(Date timestart, Date timeEnd, String searchInfo, int department, int position, int state,int start, int length);

    void updateDao(ArrayList<Integer> chks, int state);

    List findByDate(Date date);

    List findByParams(Date start, Date end, Integer id);

    DaySign findByDate(Date date, Integer id);
}
