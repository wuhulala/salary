package com.databasecourse.salary.daos.Impl;

/**
 * date:2016-05-25 13:52
 */

import org.springframework.stereotype.Repository;
import com.databasecourse.salary.daos.AdminDao;
import com.databasecourse.salary.entities.Admin;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin, Integer> implements AdminDao {

    public Admin getUserByNameandPass(Admin admin){

        String username = admin.getName();
        String password = admin.getPass();
        System.out.println("u:"+username+"p:"+password);
        String hql = "from Admin as u where  u.name= :username and u.pass = :pass";
        Admin admin1 = (Admin) getSession().createQuery(hql).setString("username", username).setString("pass",password).uniqueResult();
        return admin1;
    }
}