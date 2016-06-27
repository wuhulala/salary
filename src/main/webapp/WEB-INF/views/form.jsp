<%--
  Created by IntelliJ IDEA.
  User: xueaohui
  Date: 2016/1/22
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title></title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/form.js"></script>
</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container-fluid">
  <div class="row ">
      <jsp:include page="sidebar.jsp"/>
    <div class="col-sm-10 content" >

      <div class="col-sm-5" style="border: 1px solid orangered">
        <h2>普通表单（form）</h2>
        <form class="form">
          <div class="form-group">
            <input class="form-control" type="text" placeholder="请输入用户名">
          </div>
          <div class="form-group">
            <input class="form-control" type="password" placeholder="请输入密码">
          </div>
          <div class="form-group">
            <input type="checkbox">
            <span>自动登录</span>
          </div>
          <div class="form-group">
            <input class="form-control btn btn-primary" type="button" value="登录">
          </div>
        </form>
      </div>
      <div class="col-sm-7" style="border: 1px solid orangered">
        <h2>搜索表单</h2>
        <form class="form-inline">
          <div class="form-group">
            <input class="form-control" type="text" placeholder="请输入名称">
          </div>
          <div class="form-group">
            <input class="form-control" type="text" placeholder="please input some ..">
          </div>

          <div class="form-group">
            <input class="form-control btn btn-primary" type="button" value="search">
          </div>
        </form>
      </div>
      <div class="col-sm-5" style="border: 1px solid orangered">
        <h2>复杂表单</h2>
        <form class="form container-fluid">
          <div class="form-group">
            <input class="form-control" data-toggle="tooltip" title="点击选取日期" id="form_datepicker" readonly type="text" placeholder="请选取日期">
          </div>
          <div class="form-group col-sm-6">
              <input class="form-control " data-toggle="tooltip" title="点击选取开始日期" id="timestart" readonly type="text" placeholder="请选取开始日期">
          </div>

          <div class="form-group col-sm-6">
            <input class="form-control " data-toggle="tooltip" title="点击选取结束日期" id="timeend" readonly type="text" placeholder="请选取结束日期">
          </div>
          <div class="form-group">
            <div class="input-group">
              <span class="input-group-addon">http://</span>
              <input class="form-control" type="text" placeholder="请输入网址">
            </div>
          </div>

          <div class="form-group">
            <input class=" btn btn-success" type="file">
          </div>
          <div class="form-group">
            <div class="progress">
              <div class=" progress-bar progress-bar-info progress-striped" style="width: 60%">
                60%
              </div>
            </div>
          </div>
          <div class="form-group">
            <input class="form-control " type="color" placeholder="请选择颜色">
          </div>
          <div class="form-group">
            <input  type="range" >
          </div>


          <div class="form-group">
            <input class="form-control btn btn-primary" type="button" value="submit">
          </div>




        </form>
      </div>
      <div class="col-sm-5" style="border: 1px solid orangered">
        <h2>上传</h2>
        <form class="form container-fluid" id="uploadform">


          <div class="form-group">
            <input class=" btn btn-success" type="file" onchange="clearProgress()" id="file1">
          </div>
          <div class="form-group">
            <input class=" btn btn-success" type="file" onchange="clearProgress()" id="file2">
          </div>
          <div class="form-group">
            <input class=" btn btn-success" type="file" onchange="clearProgress()" id="file3">
          </div>
          <div class="form-group">
            <div class="progress">
              <div id="progress" class=" progress-bar progress-bar-success progress-striped"  style="width: 0%">
                0%
              </div>
            </div>
          </div>

          <div class="form-group">
            <input class="form-control btn btn-primary" id="uploadbtn" onclick="testupload()" type="button" value="上传">
          </div>

        </form>
      </div>

    </div>
    </div>





  </div>

</div>

</body>
</html>
