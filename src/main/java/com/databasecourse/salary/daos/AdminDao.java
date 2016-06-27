package com.databasecourse.salary.daos;

import com.databasecourse.salary.entities.Admin;

/**
 * date:2016-05-25 13:49
 */
public interface AdminDao {
    /**
     * 从数据库根据姓名密码获取用户
     * @return 返回获取的用户
     */
    public Admin getUserByNameandPass(Admin admin);
}
