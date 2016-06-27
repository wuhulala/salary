package com.databasecourse.salary.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 *
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {

    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object arg2) throws Exception {

        //获取url地址
        String reqUrl=request.getRequestURI().replace(request.getContextPath(), "");
        //当url地址为登录的url的时候跳过拦截器
        if(reqUrl.contains("index")||reqUrl.contains("/login")){
            return true;
        }else{
            HttpSession session=request.getSession();
            Object obj=session.getAttribute("loginUser");
            if(obj==null||"".equals(obj.toString())){
                response.sendRedirect("index.html");
                return false;
            }
        }
        return true;
    }



}