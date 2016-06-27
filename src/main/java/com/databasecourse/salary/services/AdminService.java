package com.databasecourse.salary.services;

/**
 * date:2016-05-25 14:07
 */

import com.databasecourse.salary.daos.Impl.AdminDaoImpl;
import org.springframework.stereotype.Service;
import com.databasecourse.salary.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AdminService extends BaseService<Admin, Integer> {
    private AdminDaoImpl adminDao;

    public AdminDaoImpl getAdminDao() {
        return this.adminDao;
    }

    @Autowired
    public void setAdminDao(AdminDaoImpl AdminDao) {
        super.setBaseDao(AdminDao);
        this.adminDao = AdminDao;
    }

    /**
     * 通过用户名、密码登陆
     * @param admin 登陆用户
     * @return 返回用户信息
     */
    public Admin LoginByNameandPass(Admin admin){


        Admin anwser = adminDao.getUserByNameandPass(admin);

        System.out.print("userService.....");
        System.out.println(""+anwser);

        return  anwser;
    }

    /**
     * 保存修改密码后的管理员
     */
    public void saveAdmin(Admin admin){
        adminDao.saveOrUpdate(admin);
    }

}