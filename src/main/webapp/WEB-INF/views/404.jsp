<%--
  Created by IntelliJ IDEA.
  User: xueaohui
  Date: 2016/1/14
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE HTML>
<html>
<head>
  <title>页面未找到</title>

  <link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"  media="all" />
</head>
<body>
<!--start-wrap--->
<div class="wrap">
  <!---start-header---->
  <div class="header">
    <div class="logo">
      <h1><a href="#">页面不存在</a></h1>
    </div>
  </div>
  <!---End-header---->
  <!--start-content------>
  <div class="content">
    <img src="images/404error.png" title="error" />
    <p>您访问页面不存在</p>
    <a href="#" onclick="window.history.go(-1)">返回</a>
    <div class="copy-right">
      <p>&#169 All rights Reserved | Designed by <a href="#">fastadmin</a></p>
    </div>
  </div>
  <!--End-Cotent------>
</div>
<!--End-wrap--->
</body>
</html>


