package com.databasecourse.salary.controllers.Employee;

/**
 * date:2016-05-25 14:19
 */

import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.entities.Employee;
import com.databasecourse.salary.entities.MonthSalary;
import com.databasecourse.salary.services.AdminService;
import com.databasecourse.salary.services.DaySignService;
import com.databasecourse.salary.services.EmployeeService;
import com.databasecourse.salary.services.MonthSalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("employee/")
public class EmployeesController {
    private Map dataMap;
    private EmployeeService employeeService;

    @InitBinder
    public void InitBinder(WebDataBinder dataBinder){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        dateFormatter.setLenient(false);
        dataBinder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormatter,true));
    }
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
        dataMap = new HashMap();
    }


    /**
     * 登陆
     * @param request
     * @return 返回页面
     */
    @RequestMapping(value = {"login"})
    public String Login(HttpServletRequest request,HttpServletResponse response,Model model){

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession(false);
        String username = request.getParameter("username");
        System.out.println(username);
        String password = request.getParameter("password");
        //判断验证码
        String Client_code =  request.getParameter("randomcode");
        String Server_code = (String)session.getAttribute("randCheckCode");
        if(!Client_code.equals(Server_code)) {
            session.removeAttribute("randCheckCode");
            model.addAttribute("err", "验证码错误");
            return "redirect:/employeeinfo.html";
        }


        Employee anwser = employeeService.LoginByNameandPass(username,password);

        if(anwser!=null) {

            session.setAttribute("loginUser", anwser);

            return "redirect:/employeeinfo.html";
        }else{
            model.addAttribute("err", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("editPass")
    @ResponseBody
    public Map editPass(HttpSession session,HttpServletRequest request){

        Map map = new HashMap();
        Employee employee =(Employee)session.getAttribute("loginUser");

        System.out.println("editPass....");
        if(employee.getPass().equals(request.getParameter("originPass"))){
            employee.setPass(request.getParameter("newPass"));
            employeeService.saveOrUpdate(employee);
            map.put("message", "修改成功");
        }else{
            map.put("message", "旧密码错误");
        }
        return map;
    }

    @RequestMapping(value = {"logout"})
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        return "redirect:/index.html";
    }
    @Autowired
    private DaySignService daySignService;
    @Autowired
    private MonthSalaryService monthSalaryService;

    @RequestMapping("getmonthsign.json")
    @ResponseBody
    public List getMonthSign(Date start, Date end,HttpSession session){
        Employee employee =(Employee)session.getAttribute("loginUser");
        return daySignService.findByParams(start,end,employee.getId());
    }

    @RequestMapping("getmonthsalary.json")
    @ResponseBody
    public Map getMonthSign(int year, int month ,HttpSession session){
        Employee employee =(Employee)session.getAttribute("loginUser");
        if(monthSalaryService.findByParams(year,month,employee.getId())==null){
            dataMap.put("data","null") ;
        }else{
            dataMap.put("data",monthSalaryService.findByParams(year,month,employee.getId())) ;

        }
        return dataMap;
    }


    @RequestMapping("adddaysign")
    @ResponseBody
    public Map getMonthSign(HttpSession session){

        Employee employee =(Employee)session.getAttribute("loginUser");
        Date date = new Date();
        daySignService.editState(date, employee.getId());
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTime(date);
        int hour = calendar.get(calendar.HOUR_OF_DAY);
        if(hour<10&&hour>=8){
            dataMap.put("message","签到成功");
        }else{
            dataMap.put("message", "签到失败:签到时间段为8：00-10:00");
        }
        return dataMap;
    }
}