package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 23:30
 */

import com.databasecourse.salary.entities.Employee;
import com.databasecourse.salary.entities.MonthAllowance;
import com.databasecourse.salary.services.MonthAllowanceService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class MonthAllowanceController {
    private Map dataMap;
    private MonthAllowanceService adminService;

    public MonthAllowanceService getMonthAllowanceService() {
        return this.adminService;
    }

    @Autowired
    public void setMonthAllowanceService(MonthAllowanceService adminService) {
        this.adminService = adminService;
        dataMap = new HashMap();
    }

    @RequestMapping("getallmonthallowance.json")
    @ResponseBody
    public Map getAllDaySign(int start,int length,int department,int position){
        dataMap = adminService.findByParams(department,position,start,length);
        return dataMap;
    }

    @RequestMapping("getmonthallowancebyid.json")
    @ResponseBody
    public Map getAllDaySign(int id){
        dataMap .put("data", adminService.findById(id));
        return dataMap;
    }

    @RequestMapping("save_monthallowance")
    @ResponseBody
    public Map getEmployee(MonthAllowance monthAllowance,@RequestParam("department")int department,@RequestParam("position")int position){
        int i = adminService.saveMonthAllowance(monthAllowance, department, position);
        dataMap.put("code",i);
        if(i==1){
            dataMap.put("message","保存成功");
        }else{
            dataMap.put("message","部门-职位-补贴项已存在");
        }
        return dataMap;
    }

    @RequestMapping("del_monthallowance")
    @ResponseBody
    public Map getEmployee(@RequestParam("chk")ArrayList<Integer> chk){
        adminService.del(chk);
        dataMap.put("message", "删除成功");
        return dataMap;
    }
}