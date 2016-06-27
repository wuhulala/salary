package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 22:11
 */

import com.databasecourse.salary.services.DepartmentService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class DepartmentController {
    private Map dataMap;
    private DepartmentService departmentService;

    public DepartmentService getDepartmentService() {
        return this.departmentService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
        dataMap = new HashMap();
    }

    @RequestMapping("getalldepartment.json")
    @ResponseBody
    public Map getAllPosition(){
        dataMap.put("all",departmentService.findAll());
        return dataMap;
    }
}