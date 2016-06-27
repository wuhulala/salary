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
    <title>图片管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fastadmin.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/picture.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/uploadJudge.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>

</head>
<body>
<jsp:include page="head.jsp"/>
<div class="container-fluid">
    <div class="row ">

            <jsp:include page="sidebar.jsp"/>

        <div class="col-sm-10 content " >



            <div id="alert-info" hidden class="alert alert-success alert-right">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <p>删除成功</p>
            </div>

            <div class="container-fluid">
                <hr/>

                <div style="float: right">
                    <button class="btn btn-default" onclick="showPicModal()"><i class="glyphicon glyphicon-plus"></i>上传图片</button>
                    <a class="btn btn-default" onclick="checkAll()"><i class="glyphicon glyphicon-check"></i>&nbsp;全选</a>
                    <a class="btn btn-default" onclick="delCheck()"><i class="glyphicon glyphicon-remove"></i>&nbsp;删除</a>

                </div>
                <br/>
                <div class="row">
                    <div class="pic-view col-xs-12 ">



                    </div>
                </div>
            </div>
            <hr/>


        </div>
    </div>

</div>

<div class="modal fade " id="viewmodal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog ">
        <div class="modal-content ">
            <div class="modal-header modal-header-reverse">
                <a class="close"
                   data-dismiss="modal" aria-hidden="true">
                    &times;
                </a>
                <h4 class="modal-title">
                    图片
                </h4>
            </div>
            <div class="modal-body" style="background-color: white">
                <input  type="file" id="pic" onchange="previewPic(this)" accept="image/png,image/jpeg" class="hidden">
                <img width="100%"  id="preview"   src="images/preview.png">
                <hr/>
                <div id="progress" class="progress progress-bar-success progress-bar-striped"  style="width: 0%;">0%</div>
                <hr/>

                <button class="btn btn-default" onclick="addPic()"><i class="glyphicon glyphicon-plus"></i>选择图片</button>
                <button class="btn btn-default" onclick="uploadPic()"><i class="glyphicon glyphicon-upload"></i>上传图片</button>
            </div>
        </div><!-- /.modal-content -->
    </div>
</div>


</body>
</html>
