package com.databasecourse.salary.controllers;

/**
 * date:2016-05-26 18:55
 */

import com.databasecourse.salary.services.MonthSalaryService;
import com.databasecourse.salary.services.YearSalaryService;
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
public class YearSalaryController {
    private Map dataMap;
    private YearSalaryService yearSalaryService;


    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM");
        dateFormatter.setLenient(false);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormatter,true));
    }

    @Autowired
    public void setYearSalaryService(YearSalaryService yearSalaryService) {
        this.yearSalaryService = yearSalaryService;
        dataMap = new HashMap();
    }

    @RequestMapping("getallyearsalary.json")
    @ResponseBody
    public Map getAllDaySign(int start,int length,int timeStart,String searchInfo,int department,int position){
        dataMap = yearSalaryService.findByParams(searchInfo,timeStart,department,position,start,length);
        return dataMap;
    }
}