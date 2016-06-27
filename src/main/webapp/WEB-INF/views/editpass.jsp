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
    <title>密码修改</title>
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
        <div class="col-md-10 content" >
            <div class="container-fluid" id="cells">

                <hr/>
                <div class="col-sm-12">
                    <form class="form-horizontal"  id="passForm">
                        <input hidden value="${loginUser.id}" name="userid">
                        <div class="form-group ">
                            <label class="col-sm-2  control-label">原密码</label>
                            <div class=" col-sm-6">
                                <input id="originPass" type="password" name="originPass" placeholder="请输入原密码" class="form-control" onblur="checkLength0()">

                            </div>
                            <p  class="form-control-static" id="Message0"></p>
                        </div>
                        <div class="form-group ">
                            <label class="col-sm-2 control-label">新密码</label>
                            <div class=" col-sm-6">
                                <input id="newPass" type="password" name="newPass" placeholder="请输入新密码" class="form-control" onblur="checkLength1()">
                            </div>
                            <p  class="form-control-static" id="Message1"></p>

                        </div>
                        <div class="form-group">
                            <label class=" col-sm-2 control-label">确认密码</label>
                            <div class=" col-sm-6">
                                <input id="rePass" type="password"  placeholder="请确认密码" class="form-control" onblur="checkLength2()" >
                            </div>
                            <p class="form-control-static" id="Message2"></p>


                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-6">
                                <p  id="errorMessage"></p>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-6">
                                <input name="" type="button" value="修改" onclick="editPass1()" class="form-control btn btn-info" >
                            </div>
                        </div>
                    </form>
                </div>

            </div>

        </div>
    </div>

</div>

</body>
</html>
