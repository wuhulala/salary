package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 18:55
 */

import com.databasecourse.salary.services.DaySignService;
import com.databasecourse.salary.services.MonthSignService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class MonthSignController {
    private Map dataMap;
    private MonthSignService monthSignService;

    public MonthSignService getMonthSignService() {
        return this.monthSignService;
    }
    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        dateFormatter.setLenient(false);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormatter,true));
    }
    @Autowired
    public void setMonthSignService(MonthSignService adminService) {
        this.monthSignService = adminService;
        dataMap = new HashMap();
    }

    @RequestMapping("getallmonthsign.json")
     @ResponseBody
     public Map getAllDaySign(int start,int length,Date timeStart,String searchInfo,int department,int position){
        dataMap = monthSignService.findByParams(timeStart,searchInfo,department,position,start,length);
        return dataMap;
    }
}