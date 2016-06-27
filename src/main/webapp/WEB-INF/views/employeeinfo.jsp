<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
    <meta name="author" content="Boss.xue">
    <meta name="description" content="个人介绍">
    <title>员工中心</title>
    <link href="css/bootstrap.min.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet"/>
    <link href="css/fullcalendar.css" rel="stylesheet"/>
    <link href="css/fullcalendar.print.css" rel="stylesheet" media='print'/>
    <link rel="stylesheet" href="css/employeeinfo.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script src='js/moment.min.js'></script>
    <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
    <script src='js/fullcalendar.min.js'></script>
</head>
<body  data-spy="scroll" data-target=".scroll-nav" data-offset="150">
<div id="navbarExample" class="scroll-nav navbar  navbar-default navbar-fixed-top" style="background-color: #16987e">
    <div align="center">
            <div class="navbar-header">

                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#navbar"> <span class="sr-only">切换导航</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                <a class="navbar-brand" href="resume.html" style="color: white">员工个人中心</a>
            </div>
            <div class="collapse navbar-collapse" id = "navbar">
                <ul class="nav navbar-nav">
                    <li class=""><a href="#info"  data-toggle="tooltip" data-placement="bottom" title="个人资料"><i class="glyphicon glyphicon-user"></i></a></li>
                    <li class=""><a href="#education"  data-toggle="tooltip" data-placement="bottom" title="签到"><i class="glyphicon glyphicon-pencil"></i></a></li>
                    <li class=""><a href="#skill" data-toggle="tooltip" data-placement="bottom" title="查询工资"><i class="glyphicon glyphicon-search"></i></a></li>
                </ul>
                <div class="navbar-form navbar-right">

                    <button type="button" class="btn btn-default">
                        欢迎你:${sessionScope.loginUser.name}
                        &nbsp;&nbsp;
                        <a href="logout"> 退出 </a>
                    </button>&nbsp;
                </div>
            </div>

    </div>
</div>
<div class="col-sm-12 scroll-div"  id="info">
    <h2 >个人中心</h2>
    <hr/>
    <div class="container text-center">
        <table  class="table table-bordered table-info">
            <caption>个人信息</caption>
            <tr>
                <td>姓名</td>
                <td>${sessionScope.loginUser.name}</td>
            </tr>

            <tr>
                <td>电话</td>
                <td>${sessionScope.loginUser.phone}</td>
            </tr>
            <tr>
                <td>部门</td>
                <td>${sessionScope.loginUser.departmentByDepartmentId.name}</td>
            </tr>
            <tr>
                <td>职位</td>
                <td>${sessionScope.loginUser.positionByPositionId.name}</td>
            </tr>
            <tr>
                <td>银行卡号</td>
                <td>${sessionScope.loginUser.bankNumber}</td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td>${sessionScope.loginUser.joinTime}</td>
            </tr>
        </table>
    </div>

</div>
<div class="col-sm-12 scroll-div" id="education">
    <h2>签到&nbsp;&nbsp;<small>签到时间(工作日 8:00-10:00 )  未签到为旷工 特殊原因请联系管理员</small></h2>
    <hr/>
    <div class="container-fluid">
        <div class="col-md-2">

            <button type="button" id="addsign" class="btn btn-lg btn-success">一键签到</button>
        </div>
        <script>
            $(function(){
                $("#addsign").click(function(){
                    $.getJSON("employee/adddaysign",function(data){
                       alert(data.message);
                    });
                })
            })
        </script>
        <div class="col-md-10">

            <div id='calendar' class="col-md-6" style="max-height: 550px"></div>
        </div>
    </div>
</div>
<div class="col-sm-12 scroll-div" id="skill">
    <h2 >工资查询</h2>
    <hr/>
    <div class="container">
        <div class="row">
            <!-- search star -->
            <!-- search star -->
            <form id="searchform" class="form-horizontal clearfix">
                <div class="col-lg-8 col-sm-8 pl0">
                    <div class="form-group">
                        <div class="col-sm-4">
                            <select name="year" id="year" class="input-sm ">
                            </select>
                            <button type="button"class="btn btn-sm btn-warning"> 年</button>
                            <select style="display: inline" name="month" id="month" class="input-sm ">
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                            </select>
                            <button type="button" class="btn btn-sm btn-warning"> 月</button>
                        </div>
                        <div class="col-sm-5">
                            <button type="button" onclick="search()" class="btn btn-sm btn-success"> 搜索</button>
                        </div>
                        <script>
                            function search(){
                                $.getJSON("employee/getmonthsalary.json",{year:$("#year").val(),month:$("#month").val()},function(data){
                                    $("#userTable").show();
                                    if(data.data != "null"){
                                        data = data.data;
                                        var html ="<tr>";
                                        html += "<td>"+data.base+"</td>";
                                        html += "<td>"+data.housingMoney+"</td>";
                                        html += "<td>"+data.awardMoney+"</td>";
                                        html += "<td>"+data.lateMoney+"</td>";
                                        html += "<td>"+data.leaveMoney+"</td>";
                                        html += "<td>"+data.absentMoney+"</td>";
                                        html += "<td>"+data.overtimeMoney+"</td>";
                                        html += "<td>"+data.salary+"</td>";
                                        html += "</tr>";
                                    }else{
                                        var html = "<tr><td colspan='8'>未查询到结果</td></tr>"
                                    }
                                    $("tbody").html(html);
                                })
                            }

                        </script>
                    </div>
                </div>
            </form>
            <!-- search end -->
        </div>
    </div>
    <hr/>
    <table id="userTable" hidden class="table table-bordered table-hover table-striped table-responsive table-cell " width="100%">
        <thead >
        <tr >
            <th>基本工资</th>
            <th>住房补贴</th>
            <th>月奖金</th>
            <th>迟到扣除</th>
            <th>请假扣除</th>
            <th>旷工扣除</th>
            <th>加班费</th>
            <th>总工资</th>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
</div>
<div class="container text-center" style="margin-top: 20px;margin-bottom: 20px">
    Copyright 2016 by xueaohui
</div>

<script type="text/javascript">
    $(function () {
        $("[data-toggle='tooltip']").tooltip();
        $(".scroll-div").css("height",window.innerHeight-50>600?window.innerHeight-50:600).css("padding-top",50);
        $('#calendar').fullCalendar({
            defaultDate: '2016-05-12',
            editable: true,
            eventLimit: true,
            eventSources: [
                // your event source
                {
                    url: 'employee/getmonthsign.json',
                    type: 'get',
                    dataType:'json',
                    error: function() {
                        alert('there was an error while fetching events!');
                    },
                    color: 'yellow',   // a non-ajax option
                    textColor: 'black' // a non-ajax option
                }

                // any other sources...

            ]
        });
        initTimeInput();

    });

    function initTimeInput(){
        /*$("#timestart").datetimepicker({
            format: "yyyy-mm",
            autoclose:true,
            pickerPosition: "bottom-right"
        });
        */

        var html = "";
        for(var i = -50 ; i < 50 ; i ++ ){
            var years = new Date().getFullYear() + i;
            html += "<option value='"+years+"'>"+years+"</option>";
        }
        $("#month").val(new Date().getMonth()+1);
        $("#year").html(html);
        $("#year").val(new Date().getFullYear());

    }

</script>

</body>
</html>