package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 18:55
 */

import com.databasecourse.salary.entities.MonthSalary;
import com.databasecourse.salary.services.MonthSalaryService;
import com.databasecourse.salary.services.MonthSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
public class MonthSalaryController {
    private Map dataMap;
    private MonthSalaryService monthSalaryService;


    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        dateFormatter.setLenient(false);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormatter,true));
    }

    @Autowired
    public void setMonthSalaryService(MonthSalaryService monthSalaryService) {
        this.monthSalaryService = monthSalaryService;
        dataMap = new HashMap();
    }

    @RequestMapping("getallmonthsalary.json")
    @ResponseBody
    public Map getAllDaySign(int start,int length,Date timeStart,String searchInfo,int department,int position){
        dataMap = monthSalaryService.findByParams(timeStart,searchInfo,department,position,start,length);
        return dataMap;
    }
}