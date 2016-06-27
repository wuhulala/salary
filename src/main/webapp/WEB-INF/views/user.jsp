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
  <title>表格演示</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dataTables.bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</head>
<body style="margin:0 auto;padding: 0">
<jsp:include page="head.jsp"/>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="sidebar.jsp"/>
    <div class="content col-sm-10" >
      <script type="text/javascript" src="${pageContext.request.contextPath}/js/table.js" ></script>
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-12 bbtest1">
            <h2>管理表格</h2>
          </div>
          <div class="col-sm-12 bbtest2" >
            <table id="userTable" class="table  table-hover table-striped table-responsive table-cell table-bordered dataTables-example dataTable" >
              <thead >
              <tr >
                <th>
                  <input class="cursor-pointer" type="checkbox"/>
                </th>
                <th>序号</th>
                <th>账号</th>
                <th>密码</th>
                <th>登陆状态</th>
                <th>头像</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              </tbody>


            </table>

          </div>

        </div>
      </div>


    </div>
  </div>
</div>

<div class="modal fade " id="infomodal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content ">
      <div class="modal-header modal-header-reverse">
        <a class="close"
                data-dismiss="modal" aria-hidden="true">
          &times;
        </a>
        <h4 class="modal-title">
          管理员详情
        </h4>
      </div>
      <div class="modal-body">

        <table class="table table-bordered ">
          <tr>
            <td>编号</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>账号</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>密码</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>登陆状态</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>头像</td>
            <td><p></p></td>
          </tr>
        </table>
      </div>
    </div><!-- /.modal-content -->
  </div>
</div>

</body>

</html>
