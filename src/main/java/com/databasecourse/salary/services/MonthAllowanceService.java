package com.databasecourse.salary.services;

/**
 * date:2016-05-26 23:26
 */

import com.databasecourse.salary.daos.Impl.DepartmentDaoImpl;
import com.databasecourse.salary.daos.Impl.MonthAllowanceDaoImpl;
import com.databasecourse.salary.daos.Impl.PositionDaoImpl;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.MonthAllowanceDao;
import com.databasecourse.salary.entities.MonthAllowance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service
public class MonthAllowanceService extends BaseService<MonthAllowance, Integer> {
    private MonthAllowanceDaoImpl monthAllowanceDao;

    public MonthAllowanceDaoImpl getMonthAllowanceDao() {
        return this.monthAllowanceDao;
    }

    @Autowired
    public void setMonthAllowanceDao(MonthAllowanceDaoImpl monthAllowanceDao) {
        super.setBaseDao(monthAllowanceDao);
        this.monthAllowanceDao = monthAllowanceDao;
    }
    @Autowired
    private PositionDaoImpl positionDao;
    @Autowired
    private DepartmentDaoImpl departmentDao;

    public Map findByParams(int department, int position, int start, int length){
        return monthAllowanceDao.findByParams(department,position,start,length);
    }

    public int saveMonthAllowance(MonthAllowance monthAllowance, int department, int position) {
        int result = 1;
        if(monthAllowance.getId() == null){
            if(monthAllowanceDao.findByParams(department,position) != null){
                return 0;
            }
        }
        monthAllowance.setDepartmentByDepartmentId(departmentDao.findById(department));
        monthAllowance.setPositionByPositionId(positionDao.findById(position));
        saveOrUpdate(monthAllowance);
        return result;
    }
}