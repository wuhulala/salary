package com.databasecourse.salary.services;

/**
 * date:2016-05-28 13:24
 */

import com.databasecourse.salary.daos.EmployeeDao;
import com.databasecourse.salary.daos.Impl.*;
import com.databasecourse.salary.entities.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.MonthSalaryDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class MonthSalaryService extends BaseService<MonthSalary, Integer> {
    private MonthSalaryDaoImpl monthSalaryDao;

    public MonthSalaryDaoImpl getMonthSalaryDao() {
        return this.monthSalaryDao;
    }

    @Autowired
    public void setMonthSalaryDao(MonthSalaryDaoImpl monthSalaryDao) {
        super.setBaseDao(monthSalaryDao);
        this.monthSalaryDao = monthSalaryDao;
    }

    private MonthSignDaoImpl monthSignDao;

    public MonthSignDaoImpl getMonthSignDao() {
        return monthSignDao;
    }

    @Autowired
    public void setMonthSignDao(MonthSignDaoImpl monthSignDao) {
        this.monthSignDao = monthSignDao;
    }

    private EmployeeDaoImpl employeeDao;

    public EmployeeDaoImpl getEmployeeDao() {
        return employeeDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    private MonthAllowanceDaoImpl monthAllowanceDao;

    public MonthAllowanceDaoImpl getMonthAllowanceDao() {
        return monthAllowanceDao;
    }

    @Autowired
    public void setMonthAllowanceDao(MonthAllowanceDaoImpl monthAllowanceDao) {
        this.monthAllowanceDao = monthAllowanceDao;
    }

    @Autowired
    private YearSalaryDaoImpl yearSalaryDao;

    public Map findByParams(Date timeStart, String searchInfo, int department, int position, int start, int length) {
        Calendar time = Calendar.getInstance();
        time.setTime(timeStart);
        int year = time.get(Calendar.YEAR);
        int month = time.get(Calendar.MONTH) + 1;
        return monthSalaryDao.findByParams(searchInfo, year, month, department, position, start, length);
    }

    /**
     * 每月第一天凌晨三点计算工资
     */
    @Scheduled(cron="0 59 17 1 * ?")
    public void insertMonthSalary(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) ;
        System.out.println("计算"+year+"年"+month+"月的工资");

        List<MonthSign> monthSigns = monthSignDao.findByParams(year, month);

        List<MonthSalary>monthSalaries = new ArrayList<MonthSalary>();

        for(MonthSign monthSign:monthSigns) {
            Employee employee = monthSign.getEmployeeByEmployeeId();

            MonthAllowance monthAllowance =  monthAllowanceDao.findByParams(employee.getDepartmentByDepartmentId().getId(), employee.getPositionByPositionId().getId());
            float lateMoney = monthSign.getLate()*monthAllowance.getLateMoney();
            float leaveMoney = monthSign.getLeave()*monthAllowance.getLateMoney();
            float absentMoney = monthSign.getAbsent()*monthAllowance.getAbsentMoney();
            float overtimeMoney = monthAllowance.getOvertimeMoney()*monthSign.getOvertime();
            float award = monthAllowance.getAward()+monthAllowance.getHousing();
            float salary = employee.getBaseSalary() + award +  overtimeMoney - absentMoney - lateMoney - leaveMoney ;
            System.out.println("旷工工资"+absentMoney);
            MonthSalary monthSalary = new MonthSalary(employee,year,month,salary,employee.getBaseSalary(),monthAllowance.getAward(),monthAllowance.getHousing(),overtimeMoney,lateMoney,leaveMoney,absentMoney);
            YearSalary yearSalary = yearSalaryDao.findByEmployeeId(employee.getId());
            String monthSalarys = month==1?""+salary:","+salary;
            yearSalary.setEveryMonthSalary(yearSalary.getEveryMonthSalary() + monthSalarys);
            yearSalary.setTotalSalary(yearSalary.getTotalSalary() + salary);
            yearSalaryDao.saveOrUpdate(yearSalary);
            monthSalaries.add(monthSalary);

        }
        monthSalaryDao.saveOrUpdate(monthSalaries);
    }

    public MonthSalary findByParams(int year, int month, Integer id) {
        return monthSalaryDao.findByParams(year,month,id);
    }
}