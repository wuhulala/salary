package com.databasecourse.salary.services;

/**
 * date:2016-05-26 22:08
 */

import com.databasecourse.salary.daos.Impl.DepartmentDaoImpl;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.daos.DepartmentDao;
import com.databasecourse.salary.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DepartmentService extends BaseService<Department, Integer> {
    private DepartmentDaoImpl departmentDao;

    public DepartmentDaoImpl getDepartmentDao() {
        return this.departmentDao;
    }

    @Autowired
    public void setDepartmentDao(DepartmentDaoImpl departmentDao) {
        super.setBaseDao(departmentDao);
        this.departmentDao = departmentDao;
    }
}