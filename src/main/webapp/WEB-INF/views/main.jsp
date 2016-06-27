<%--
  Created by IntelliJ IDEA.
  User: xueaohui
  Date: 2016/1/16
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/root.js"></script>

</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container-fluid">
    <div class="row ">
            <jsp:include page="sidebar.jsp"/>
        <div class="content " id="content">
            <div class="container-fluid" id="cells">


                <div class="col-sm-12">
                        <img src="images/touxiang.jpg" style="width: 50px;height: 50px">
                        <label style="font-size: small">用户名:&nbsp;&nbsp;</label>${sessionScope.loginUser.name}
                </div>

                <hr/>

            </div>

        </div>
    </div>

</div>

</body>
</html>
