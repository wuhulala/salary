<%--

  Date: 2015/12/10
  Time: 16:25
--%>
<%
  if(session.getAttribute("loginUser")!=null){
    response.sendRedirect("main.html");
  }
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>企业通用后台管理系统</title>

  <meta name="renderer" content="webkit">
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" />

  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

  <script type="text/javascript">
    function reload() {
      document.getElementById("CreateCheckCode").src = document
                      .getElementById("CreateCheckCode").src
              + "?nocache=" + new Date().getTime();

    }

    $(function(){
      var url = window.location.search;
      var loc = url.substring(url.lastIndexOf('=')+1, url.length);
      if(parseInt(loc)==1){
        $("#message").html("验证码错误");
      }
      $(":submit").click(function(){
        if($("#randomcode").val()&&$("#username").val()&&$("#password").val()){
          $(":submit").attr("disabled","disabled");
          $(":submit").val("正在登陆...");
          $("#loginForm").submit();
        }
      });

    })
  </script>
</head>

<body>
<nav class="navbar navbar-default" role="navigation">
  <div class="navbar-header">
    <a class="navbar-brand" href="#"><span><img src="${pageContext.request.contextPath}/images/logo.png" width="35" height="35" class="img-rounded"></span>企业通用工资管理系统</a>
  </div>
  <div>
    <ul class="nav navbar-nav">
      <%--导航栏--%>
    </ul>
  </div>
</nav>



<div class="col-md-12">
  <div class="col-md-6">

  </div>

  <div class="col-md-4">
  
      <div class="tab-pane fade in active" id="home">

        <form id="loginForm"  data-remote="true" accept-charset="UTF-8" method="post" action="employee/login">
          <div class="form-group ">
            <div class="col-md-12">
              <input id="username" class="form-control" type="text" name="username" placeholder="请输入手机号码" required="required" >
            </div>
          </div>
          <div class="form-group ">
            <div class="col-md-12">
              &nbsp
            </div>
          </div>
          <div class="form-group ">
            <div class="col-md-12">
              <input id="password" class="form-control" type="password" name="password" placeholder="请输入密码" required="required"  >
            </div>
          </div>
          <div class="form-group ">
            <div class="col-md-12">
              &nbsp
            </div>
          </div>
          <div class="form-group ">
            <div class="col-md-12">
              <a href="#" >忘记密码? </a>
            </div>
          </div>

          <div class="form-group ">
            <div class="col-md-7">
              <input id="randomcode" class="form-control" type="randomcode" name="randomcode" placeholder="请输入验证码" required="required"  >
            </div>
            <div class="col-md-3">
              <img alt="验证码显示失败"  id = "CreateCheckCode" src = "${pageContext.request.contextPath}/PictureCheckCode">
            </div>
            <div class="col-md-2">
              <a href="#" onclick="reload()">刷新</a>
            </div>
          </div>
          <div class="form-group ">
            <div class="col-md-12" align="center">
              &nbsp<p style="color: #ee0000">${err}</p>
            </div>
          </div>

          <div class="form-group ">
            <div class="col-md-12">
              <input class="btn btn-primary btn-block marginbtn" type="submit" data-disable-with="正在登录..." value="登 录"  >

            </div>
          </div>

        </form>
    </div>

  </div>

</div>


</body>
</html>
