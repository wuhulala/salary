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
  <title>报表</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/Chart.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/drawchart.js"></script>

</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container-fluid">
  <div class="row ">
      <jsp:include page="sidebar.jsp"/>
    <div class="col-sm-10 content" >

      <h2>基于chart.js</h2>
      <hr/>

      <div class="container-fluid">
        <div class="row">
          <div class="col-md-3 col-sm-12">
            <hr/>
            <p class="text-center">柱状图</p>
            <hr/>
            <canvas class="" style="border: 1px green solid" id="barChart"  width="200px" height="200px"></canvas>

          </div>
          <div class="col-md-3 col-sm-12">
            <hr/>
            <p class="text-center">折线图</p>
            <hr/>
            <canvas class="" id="lineChart" style="border: 1px green solid" width="200px" height="200px"></canvas>
          </div>

          <div class="col-md-3 col-sm-12" >
            <hr/>
            <p class="text-center">雷达图</p>
            <hr/>
            <canvas class="" id="radarChart" style="border: 1px green solid" width="200px" height="200px"></canvas>
          </div>
          <div class="col-md-3 col-sm-12" >
            <hr/>
            <p class="text-center">饼状图</p>
            <hr/>
            <canvas class="" id="pieChart" style="border: 1px green solid" width="200px" height="200px"></canvas>



          </div>
        </div>
        <hr/>
        <div class="row">

          <form class="col-sm-4">
            <div class="form-group">
              <input class="form-control" name="label" id="label" type="text" placeholder="请输入名称">
            </div>
            <div class="form-group">
              <input class="form-control" name="value" id="value" type="text" placeholder="请输入名称">
            </div>
            <div class="form-group">
              <input class="form-control" name="color" id="color" type="color" placeholder="请输入名称">
            </div>
            <div class="form-group">
              <input type="submit" class="form-control btn btn-success" onclick="drawchart()" value="创建">
            </div>
          </form>
          <div class="col-sm-4" >
            <canvas class="" id="pieChart1" style="border: 1px green solid" width="200px" height="200px"></canvas>
          </div>
        </div>
      </div>


    </div>
  </div>

</div>

</body>
</html>
