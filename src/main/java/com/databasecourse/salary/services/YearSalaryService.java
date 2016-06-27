package com.databasecourse.salary.services;

/**
 * date:2016-05-28 15:56
 */

import com.databasecourse.salary.daos.Impl.YearSalaryDaoImpl;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.YearSalaryDao;
import com.databasecourse.salary.entities.YearSalary;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service
public class YearSalaryService extends BaseService<YearSalary, Integer> {
    private YearSalaryDaoImpl yearSalaryDao;

    public YearSalaryDaoImpl getYearSalaryDao() {
        return this.yearSalaryDao;
    }

    @Autowired
    public void setYearSalaryDao(YearSalaryDaoImpl yearSalaryDao) {
        super.setBaseDao(yearSalaryDao);
        this.yearSalaryDao = yearSalaryDao;
    }

    public Map findByParams(String searchInfo, int year, int department, int position, int start, int length){
        return yearSalaryDao.findByParams(searchInfo,year,department,position,start,length);
    }


}