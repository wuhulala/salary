package com.databasecourse.salary.controllers;

/**
 * date:2016-05-25 20:38
 */

import com.databasecourse.salary.entities.Employee;
import com.databasecourse.salary.services.EmployeeService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class EmployeeController {
    private Map dataMap;
    private EmployeeService employeeService;

    public EmployeeService getEmployeeService() {
        return this.employeeService;
    }




    @Autowired
    public void setEmployeeService(EmployeeService adminService) {
        this.employeeService = adminService;
        dataMap = new HashMap();
    }

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormatter,true));
    }

    @RequestMapping("getallemployee.json")
    @ResponseBody
    public Map getAllEmployee(int start,int length,Date timeStart,Date timeEnd,String searchInfo,int department,int position){
        dataMap = employeeService.findByParams(timeStart,timeEnd,searchInfo,department,position,start,length);
        return dataMap;
    }

    @RequestMapping("getemployeebyid.json")
    @ResponseBody
    public Map getEmployee(@RequestParam("id")int id){
        dataMap.put("data",employeeService.findById(id));
        return dataMap;
    }

    @RequestMapping("save_employee")
    @ResponseBody
    public Map getEmployee(Employee employee,@RequestParam("department")int department,@RequestParam("position")int position){
         employeeService.saveEmployee(employee,department,position);
        return dataMap;
    }

    @RequestMapping("del_employee")
    @ResponseBody
    public Map getEmployee(@RequestParam("chk")ArrayList<Integer>chk){
        employeeService.del(chk);
        dataMap.put("message","删除成功");
        return dataMap;
    }


}