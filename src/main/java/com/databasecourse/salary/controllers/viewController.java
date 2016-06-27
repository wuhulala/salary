package com.databasecourse.salary.controllers;

/**
 * Created by xueaohui on 2016/2/9.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class viewController {
    @RequestMapping("/test")
    public String Test(){
        System.out.println("test .....");
        return "table";
    }

    @RequestMapping("{name}.html")
    public String viewResolver(@PathVariable("name")String name){

        System.out.println("跳转到"+name+"页面");
        return name;
    }

    @RequestMapping("getprogressinfo.json")
    @ResponseBody
    public Map  getProgressInfo(HttpSession session){
        Map map = new HashMap();
        Object percent  = session.getAttribute("progress");
        map.put("percent",percent);
        return map;
    }
}
