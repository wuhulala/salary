<%--
  Created by IntelliJ IDEA.
  User: xueaohui
  Date: 2016/1/13
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>头部</title>
    <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/head.css" rel="stylesheet">
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="height: 53px">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse"
                data-target="#navbar"><span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span
                class="icon-bar"></span> <span class="icon-bar"></span></button>
        <a class="navbar-brand">
            <span>企业通用工资管理系统</span>
        </a>
    </div>
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbar">
            <ul class="nav navbar-nav ">
            </ul>
            <ul class="nav navbar-nav navbar-right ">
                <li class="dropdown">
                    <bbb id="dLabel"   data-toggle="dropdown" class="dropdown-toggle " aria-haspopup="true"
                       aria-expanded="false"  style="padding-right: 30px;cursor: pointer">
                        <img src="images/touxiang.jpg" width="50" height="50" class="img-rounded">
                        <span class="nav-username">${sessionScope.loginUser.name} &nbsp;<small><span class="icon-angle-down"></span></small></span>
                    </bbb>
                    <ul class="dropdown-menu nav-dropdown ">
                        <li><a href="main.html">个人资料中心</a></li>
                        <li><a href="editpass.html">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="#" onclick="logout()">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</div>
<div id="preloader" >
    <div id="status"><i class="icon-spinner icon-spin "></i></div>
</div>
<div id="alert-info" hidden class="alert alert-success alert-right">
    <a href="#" class="close" data-dismiss="alert">&times;</a>
    <p>删除成功</p>
</div>
</body>
<script type="text/javascript">
    jQuery(window).load(function() {
        // Page Preloader
        jQuery('#preloader').delay(250).fadeOut(function(){
            jQuery('body').delay(250).css({'overflow':'visible'});
        });
    });

    function logout(){
        if(confirm("确认要退出本系统吗")){
            window.location.href = "logout";
        }
    }
</script>
</html>
