package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-26 22:03
 */

import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.DepartmentDao;
import com.databasecourse.salary.entities.Department;

@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Integer> implements DepartmentDao {

}