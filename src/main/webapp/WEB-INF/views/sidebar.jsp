<%--
  Created by IntelliJ IDEA.
  User: xueaohui
  Date: 2016/1/16
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/root.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/sidebar.js"></script>

</head>
<body >
<div class="content col-md-2"  id="sidebar" >
<ul id="main-nav" class="nav nav-tab nav-stacked" >
  <li>
      <h3 style="color: white;display: inline">控制台<small></small></h3>
      <span class="pull-right"><i class=" icon-double-angle-left " style="cursor: pointer" onclick="hideSideBar()" ></i></span>
  </li>
    <li >
    <a href="#systemSetting" class="nav-header collapsed first-menu" data-toggle="collapse">
      <i class="glyphicon glyphicon-user"></i>
      个人中心
      <span><i  class="pull-right glyphicon glyphicon-plus"></i></span>
    </a>
    <ul id="systemSetting" class=" nav nav-list collapse secondmenu" >
      <li><a href="main.html" id="mainMenu">资料</a></li>
      <li><a href="editpass.html" id="editpassMenu">密码修改</a></li>
    </ul>
  </li>
  <li>
    <a href="#zujian"  class="nav-header collapsed first-menu" data-toggle="collapse">
      <i class="icon-briefcase"></i>
      工作中心
      <span><i  class="pull-right glyphicon glyphicon-plus"></i></span>
    </a>
    <ul id="zujian" class="nav nav-list collapse secondmenu" aria-expanded="true" >
      <li><a href="employee.html" id="employeeMenu"  >员工管理</a></li>
      <li><a href="monthallowance.html" id="monthallowanceMenu"  >月津贴管理</a></li>
      <li><a href="daysign.html" id="daysignMenu"  >日签到管理</a></li>
      <li><a href="monthsign.html" id="monthsignMenu"  >月考勤管理</a></li>
      <li><a href="monthsalary.html" id="monthsalaryMenu"  >月工资报表</a></li>
      <li><a href="yearsalary.html" id="yearsalaryMenu"  >年工资报表</a></li>

    </ul>
  </li>

</ul>
</div>
<div style="position:fixed;top:50%;color: white ;text-align: center" id="show-sidebar" hidden><i class=" icon-double-angle-right " style="cursor: pointer" onclick="showSideBar()" ></i></div>

</body>
</html>
