<%--
  Created by IntelliJ IDEA.
  User: xueaohui
  Date: 2016/1/16
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>月津贴管理</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dataTables.bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datetimepicker.min.css">
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/dataTables.bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/table/colResizable-1.5.min.js" ></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-datetimepicker.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap3-validation.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/view/monthallowance.js" ></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/html2canvas.min.js" ></script>

</head>
<body >
<jsp:include page="head.jsp"/>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="sidebar.jsp"/>

    <div class="content " id="content">

      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-12 ">
            <h2>月津贴-扣除列表</h2>
            <hr/>
          </div>
          <div class="col-sm-12">
            <div class="col-sm-12">
              <!-- search star -->
              <form id="searchform" class="form-horizontal clearfix">
                <div class="col-lg-12 col-sm-12">
                  <div class="form-group">
                    <div class="col-lg-12 col-sm-12 input-group">
                      <div class="col-sm-4">
                        <select name="department" id="department" class="input-sm form-control input-sm inline">
                          <option  value="-1">部门</option>
                          <option>人事部</option>
                          <option>销售部</option>
                          <option>开发部</option>
                          <option>后勤部</option>
                          <option>财务部</option>
                        </select>
                      </div>
                      <div class="col-sm-4">
                        <select name="position" id="position" class="input-sm form-control input-sm inline">
                          <option value="-1">职位</option>
                          <option>总监</option>
                          <option>经理</option>
                          <option>副经理</option>
                          <option>高级员工</option>
                          <option>初级员工</option>
                        </select>
                      </div>
                      <button type="button" onclick="search()" class="btn btn-sm btn-success"><i class="icon-search"></i> 搜索</button>&nbsp;&nbsp;
                      &nbsp;&nbsp;
                      <button type="button" onclick="printTable()" class="btn btn-sm btn-default"> 打印</button>&nbsp;&nbsp;
                      <button type="button" onclick="showAddModal()" class="btn btn-sm btn-primary"><i class="icon-plus-sign"></i>&nbsp;录入</button>&nbsp;&nbsp;
                      <button type="button" onclick="showEditModal()" class="btn btn-sm btn-warning"><i class="icon-edit-sign"></i>&nbsp;修改</button>&nbsp;&nbsp;
                      <button type="button" onclick="delAllCheck()" class="btn btn-sm btn-danger"><i class="icon-minus-sign"></i>&nbsp;删除</button>&nbsp;&nbsp;
                    </div>

                  </div>
                </div>
              </form>
              <!-- search end -->
            </div>

          </div>

          <div class="col-sm-12 " >

            <table id="userTable" class="table table-bordered table-hover table-striped table-responsive table-cell " width="100%" >
              <thead >
              <tr >
                <th>
                  <input class="cursor-pointer" id="checkallbtn" onclick="checkAll()" type="checkbox"/>
                </th>
                <th>序号</th>
                <th>部门</th>
                <th>职位</th>
                <th>住房补贴</th>
                <th>月奖金</th>
                <th>迟到扣除</th>
                <th>请假扣除</th>
                <th>旷工扣除</th>
                <th>加班费</th>
              </tr>
              </thead>
              <tbody id="tbody">
              </tbody>
            </table>

          </div>

        </div>
      </div>


    </div>
  </div>
</div>

<div class="modal fade " id="editinfomodal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content ">
      <div class="modal-header modal-header-reverse" >
        <a class="close"
           data-dismiss="modal" aria-hidden="true" style="color: white">
          &times;
        </a>
        <h4 class="modal-title">
          津贴详情
        </h4>
      </div>
      <div class="modal-body">
        <!-- 水平排列的表单 -->

        <form id="myform" class="form-horizontal" action="#" role="form">
          <input   class="form-control" type="hidden"  name="id">
          <div class="form-group">
            <label class="col-sm-2 control-label">部门</label>
            <div class="col-sm-6">
              <select class=" form-control"  name="department">
              </select>
            </div>

          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" >职位</label>
            <div class="col-sm-6">
              <select class=" form-control"  name="position" >

              </select>
            </div>
          </div>
          <div class="form-group">

            <label  class="col-sm-2 control-label">住房补贴</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="housing" check-type="required " required-message="请填写住房补贴。">
            </div>

          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">月奖金</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="award" check-type="required " required-message="请填写月奖金。">
            </div>
          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">迟到扣除</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="lateMoney" check-type="required " required-message="请填写迟到扣除。">
            </div>
          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">请假扣除	</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="leaveMoney" check-type="required " required-message="请填写请假扣除。">
            </div>
          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">旷工扣除</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="absentMoney" check-type="required " required-message="请填写旷工扣除。">
            </div>
          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">加班费</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="overtimeMoney" check-type="required " required-message="请填写加班费。">
            </div>
          </div>
          <div class="form-group">
            <button type="button" class="btn btn-primary col-sm-offset-1 col-sm-4" id="editActive"  >修改</button>
            <button type="reset" id="resetForm" class="btn btn-primary  col-sm-offset-1 col-sm-2">重置</button>
          </div>

        </form>
      </div>
    </div><!-- /.modal-content -->
  </div>
</div>

</body>


</html>
