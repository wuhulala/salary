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
  <title>员工管理</title>
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
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/view/employee.js" ></script>
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
            <h2>员工列表</h2>
            <hr/>
          </div>
          <div class="col-sm-12">
            <div class="col-sm-3" >
              <button type="button" onclick="showAddModal()" class="btn btn-sm btn-primary"><i class="icon-plus-sign"></i>&nbsp;录入员工</button>
              <button type="button" onclick="showEditModal()" class="btn btn-sm btn-warning"><i class="icon-edit-sign"></i>&nbsp;修改</button>
              <button type="button" onclick="delAllCheck()" class="btn btn-sm btn-danger"><i class="icon-minus-sign"></i>&nbsp;删除</button>
            </div>
            <div class="col-sm-9" >
              <!-- search star -->
              <form id="searchform" class="form-horizontal clearfix">
                <div class="col-lg-3 col-sm-6">
                  <div class="form-group">
                    <div class="col-sm-6">
                      <select name="department" id="department" class="input-sm form-control input-sm inline">
                      </select>
                    </div>
                    <div class="col-sm-6">
                      <select name="position" id="position" class="input-sm form-control input-sm inline">
                      </select>
                    </div>
                  </div>
                </div>
                <div class="col-lg-6 col-sm-4 hidden-md">
                  <div class="form-group">
                    <label class="col-lg-2  col-sm-2 control-label">入职</label>
                    <div class="col-lg-9 col-sm-9 input-group date">
                      <span class="input-group-addon"><i class="icon-calendar"></i></span>
                      <input class="form-control input-sm " data-toggle="tooltip" title="点击选取开始日期" value="2000-01-01" id="timestart" name="timeStart"  readonly type="text">
                      <span class="input-group-addon">到</span>
                      <input class="form-control  input-sm" data-toggle="tooltip" title="点击选取结束日期" value="2050-01-01" id="timeend"  name="timeEnd" readonly type="text" >
                    </div>
                  </div>
                </div>
                <div class="col-lg-3 col-sm-2">
                  <div class="form-group">
                    <div class="col-lg-12 col-sm-12 input-group">
                      <input type="text" placeholder="姓名/电话/身份证" name="searchInfo" class="input-sm form-control">
		                                 <span class="input-group-btn">
                                        <button type="button" onclick="search()" class="btn btn-sm btn-success"> 搜索</button>
                                          &nbsp;&nbsp;
                                         <button type="button" onclick="printTable()" class="btn btn-sm btn-default"> 打印</button></span>
                    </div>
                  </div>
                </div>
              </form>
              <!-- search end -->
            </div>

          </div>

          <div class="col-sm-12 " >

            <table id="userTable" class="table table-bordered table-hover table-striped table-responsive table-cell " width="100%">
              <thead >
              <tr >
                <th>
                  <input class="cursor-pointer" id="checkallbtn" onclick="checkAll()" type="checkbox"/>
                </th>
                <th>序号</th>
                <th>姓名</th>
                <th>电话</th>
                <th>性别</th>
                <th>部门</th>
                <th>职位</th>
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

<div class="modal fade " id="infomodal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog ">
    <div class="modal-content ">
      <div class="modal-header modal-header-reverse" >
        <a class="close"
           data-dismiss="modal" aria-hidden="true" style="color: white">
          &times;
        </a>
        <h4 class="modal-title">
          员工详情
        </h4>
      </div>
      <div class="modal-body">

        <table class="table table-bordered ">
          <tr>
            <td>姓名</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>联系方式</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>性别</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>部门</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>职位</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>身份证号</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>银行卡号</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>入职日期</td>
            <td><p></p></td>
          </tr>
          <tr>
            <td>基本工资</td>
            <td><p></p></td>
          </tr>
        </table>
      </div>
    </div><!-- /.modal-content -->
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
          员工详情
        </h4>
      </div>
      <div class="modal-body">
        <!-- 水平排列的表单 -->

        <form id="myform" class="form-horizontal" action="#" role="form">
          <input   class="form-control" type="hidden"  name="id">
          <div class="form-group">
            <label  class="col-sm-2 control-label">姓名</label>
            <div class="col-sm-6">
              <input type="text" class="form-control" name="name" check-type="required" required-message="请填写员工名称。">
            </div>
          </div>
          <div class="form-group">
            <label class="col-sm-2 control-label" >联系方式</label>
            <div class="col-sm-6">
              <input class="form-control" type="text"  name="phone" check-type="required " required-message="请填写员工联系方式。">
            </div>
          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">性别</label>
            <div class="col-sm-6">
              <label class="checkbox-inline">
                <input type="radio" name="sex"
                       value="0" checked> 男
              </label>
              <label class="checkbox-inline">
                <input type="radio" name="sex"
                       value="1"> 女
              </label>
            </div>

          </div>
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
            <label  class="col-sm-2 control-label">身份证</label>
            <div class="col-sm-6">
              <input class="form-control" type="text"  name="idCard" check-type="required " required-message="请填写员工身份证信息。">
            </div>

          </div>
          <div class="form-group">
            <label  class="col-sm-2 control-label">银行卡号</label>
            <div class="col-sm-6">
              <input class="form-control" type="text"  name="bankNumber" check-type="required " required-message="请填写银行卡号。" >
            </div>

          </div>

          <div class="form-group">
            <label  class="col-sm-2 control-label">入职时间</label>
            <div class="col-sm-6">
              <input  type="text"  class="col-xs-5 form-control" id="jointime" name="joinTime"  readonly  placeholder="点击选取开始日期" check-type="required ">
            </div>

          </div>

          <div class="form-group">

            <label  class="col-sm-2 control-label">基本工资</label>
            <div class="col-sm-6">
              <input class="form-control" type="number"  name="baseSalary" check-type="required " required-message="请填写基本工资。">
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
