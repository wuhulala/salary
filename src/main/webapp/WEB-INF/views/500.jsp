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
  <title>内部服务器出错</title>

  <link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"  media="all" />
</head>
<body>
<!--start-wrap--->
<div class="wrap">
  <!---start-header---->
  <div class="header">
    <div class="logo">
      <h1><a href="#">内部服务器出错</a></h1>
    </div>
  </div>
  <!---End-header---->
  <!--start-content------>
  <div class="content">
    <img src="images/500error.png" title="error" />
    <p>1.网络未连接或网速过慢导致连接服务器出错</p>
    <p>2.服务器在忙,无暇接受您的请求</p>
    <a href="javascript:void(0)" onclick="window.location.reload()">刷新试试</a>
    <a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
    <div class="copy-right">
      <p>&#169 All rights Reserved | Designed by <a href="#">fastadmin</a></p>
    </div>
  </div>
  <!--End-Cotent------>
</div>
<!--End-wrap--->
</body>
</html>


