package com.databasecourse.salary.services;

/**
 * date:2016-05-26 18:54
 */

import com.databasecourse.salary.daos.Impl.EmployeeDaoImpl;
import com.databasecourse.salary.daos.Impl.MonthSignDaoImpl;
import com.databasecourse.salary.entities.Employee;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.MonthSignDao;
import com.databasecourse.salary.entities.MonthSign;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class MonthSignService extends BaseService<MonthSign, Integer> {
    private MonthSignDaoImpl monthSignDao;

    public MonthSignDaoImpl getMonthSignDao() {
        return this.monthSignDao;
    }

    @Autowired
    public void setMonthSignDao(MonthSignDaoImpl monthSignDao) {
        super.setBaseDao(monthSignDao);
        this.monthSignDao = monthSignDao;
    }

    @Autowired
    private EmployeeDaoImpl employeeDao;

    public Map findByParams(Date timeStart, String searchInfo, int department, int position,  int start, int length) {
        Calendar time = Calendar.getInstance();
        time.setTime(timeStart);
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH)+1;
        return monthSignDao.findByParams(year, month, searchInfo, department, position, start, length);
    }

    /**
     * 每月28号9:00插入新的一个月签到表
     */
    @Scheduled(cron="0 0 21 28 * ?")
    public void insertMonthSigns(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        List<MonthSign> monthSigns = new ArrayList<MonthSign>();
        List<Employee>employees = employeeDao.findActiveEmployees();
        System.out.println(employees);
        for(Employee employee : employees){
            MonthSign monthSign = new MonthSign(employee,month + 1,year);
            monthSigns.add(monthSign);
        }
        monthSignDao.saveOrUpdate(monthSigns);
    }
}