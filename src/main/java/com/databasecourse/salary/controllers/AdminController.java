package com.databasecourse.salary.controllers;

/**
 * date:2016-05-25 14:19
 */

import com.databasecourse.salary.converters.DateFormatter;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.databasecourse.salary.entities.Admin;
import com.databasecourse.salary.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("prototype")
public class AdminController {
    private Map dataMap;
    private AdminService adminService;

    public AdminService getAdminService() {
        return this.adminService;
    }

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
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
            return "index";
        }


        Admin anwser = adminService.LoginByNameandPass(new Admin(username, password));

        if(anwser!=null) {

            session.setAttribute("loginUser", anwser);
            session.setAttribute("UserId", anwser.getId());

            return "redirect:/main.html";
        }else{
            model.addAttribute("err", "用户名或密码错误");
            return "index";
        }
    }

    @RequestMapping("editPass")
    @ResponseBody
    public Map editPass(HttpSession session,HttpServletRequest request){

        Map map = new HashMap();
        Admin admin =(Admin)session.getAttribute("loginUser");

        System.out.println("editPass....");
        if(admin.getPass().equals(request.getParameter("originPass"))){
            admin.setPass(request.getParameter("newPass"));
            adminService.saveAdmin(admin);
            map.put("message", "修改成功");
        }else{
            map.put("message", "旧密码错误");
        }
        return map;
    }

    @RequestMapping(value = {"logout"})
    public String logout(HttpSession session){
        session.removeAttribute("loginUser");
        session.removeAttribute("userId");
        return "redirect:/index.html";
    }
}