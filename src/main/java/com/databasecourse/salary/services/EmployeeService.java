package com.databasecourse.salary.services;

/**
 * date:2016-05-25 20:18
 */

import com.databasecourse.salary.daos.Impl.*;
import com.databasecourse.salary.entities.MonthSign;
import com.databasecourse.salary.entities.YearSalary;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.EmployeeDao;
import com.databasecourse.salary.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class EmployeeService extends BaseService<Employee, Integer> {
    private EmployeeDaoImpl employeeDao;

    public EmployeeDaoImpl getEmployeeDao() {
        return this.employeeDao;
    }

    @Autowired
    public void setEmployeeDao(EmployeeDaoImpl employeeDao) {
        super.setBaseDao(employeeDao);
        this.employeeDao = employeeDao;
    }
    @Autowired
    private PositionDaoImpl positionDao;
    @Autowired
    private DepartmentDaoImpl departmentDao;
    @Autowired
    private MonthSignDaoImpl monthSignDao;
    @Autowired
    private YearSalaryDaoImpl yearSalaryDao;
    public Map findByParams(Date timeStart, Date timeEnd, String searchInfo, int department, int position, int start, int length) {
        return employeeDao.findByParams(timeStart,timeEnd,searchInfo,department,position,start,length);
    }

    public void saveEmployee(Employee employee, int department, int position) {
        boolean flag =false;
        Employee employee1 = null;
        if(employee.getId() == null){
            flag = true;
            employee.setPass("000000");
        }else{
            employee1 = findById(employee.getId());
            employee.setPass(employee1.getPass());
         }
        employee.setPositionByPositionId(positionDao.findById(position));
        employee.setDepartmentByDepartmentId(departmentDao.findById(department));
        employeeDao.getSession().merge(employee);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( employee.getJoinTime());
        if(flag){
            int month = calendar.get(Calendar.MONTH) + 1;
            int year = calendar.get(Calendar.YEAR);
            monthSignDao.saveOrUpdate(new MonthSign(employee, month, year));
            String monthSalarys = "";
            for(int i = 1 ; i < month ; i ++ ){
                monthSalarys += "0";
                if( i != month - 1){
                    monthSalarys += ",";
                }
            }
            yearSalaryDao.saveOrUpdate(new YearSalary(employee, year, monthSalarys));
        }

    }

    public Employee LoginByNameandPass(String username, String password) {
        return employeeDao.findByNameandPass(username,password);
    }
}