/**
 * Created by xueaohui on 2016/1/17.
 */
var table;
$(function() {
    //初始化导航栏
   // initSidebar();
    initSelect()
    initTimeInput();
    $("#myform").validation();

    $("#editActive").click(function(e){
        //第一种提示写法
        if ($("#myform").valid(this,'有未填写的内容')==false){
            return false;
        }

        $.post("save_employee",$("#myform").serialize(),function(data){
            showAlert(data['message']);
            $("#editinfomodal").modal('hide');

        },'json')
    })
} );

function search(){
    if(table!=null) {
        table.destroy();
    }
    createTable();
}

function createTable(){
    // DataTable
    table = $('#userTable').DataTable( {
        dom: 'rt<"table-footer"<"col-md-3 col-sm-4"i><"col-md-5 col-sm-4"l><"col-md-4 col-sm-4"p>>',
        serverSide: true,//设置服务端模式,搜索需要根据传参实现
        ajax: {
            url: "getallmonthsign.json",
            data: {
                timeStart:$("#timestart").val(),
                department:$("#department").val(),
                position:$("#position").val(),
                searchInfo:$("#searchform input[name='searchInfo']").val()==null ? "" : $("#searchform input[name='searchInfo']").val()
            }
        },
        searching:false,
        language: {
            url:'resources/dataTable_ZH.json'
         },
        ordering:  false,
        createdRow:function(row,data, index){
            //单击弹出详情
            $(row).click(function(){
                if(!$("#chk"+index).prop("checked")){
                    $("#chk"+index).prop("checked",true);
                    $(row).css("background-color","#fff9ca");

                }else{
                    $("#chk"+index).prop("checked",false);
                    cancelCheckAll();
                    $(row).css("background-color","white");
                }
            });
            //双击弹出详情
            $(row).dblclick(function(){
                //showinfo(data.id);
            });
        },
        columns: [
            {"data": null},
            {"data": null},
            {"data": "employeeByEmployeeId.name"},
            {"data":"work"},
            {"data":"absent"},
            {"data":"late"},
            {"data":"leave"},
            {"data":"overtime"}

        ],
        columnDefs: [
            {
                targets: 1,

                render: function (data, type, row,   meta ) {


                    var html = meta.row+1;

                    return html;
                }
            },
            {
                targets: 0,

                render: function (data, type, row,   meta ) {


                    var html = "<input id='chk"+meta.row+"' employee_id='"+row.employeeByEmployeeId.id+"' state='"+row.state+"' name='chk' type='checkbox' value='"+row.id+"' class='cursor-pointer'>";

                    return html;
                }
            }

        ],
        lengthMenu: [[10, 20, 50, -1], ["10", "20", "50", "全部"]],//自定义单页显示
        initComplete:function(){
            $('.table').colResizable();
        }
    } );
}




function showinfo(id){

    $.ajax({
        url:'getemployeebyid.json?id='+id,
        type:'get',
        dataType:'json',
        success:function(data){
            $("#infomodal").modal('show');
            data = data.data;
            $("#infomodal table tr:eq(0) td:eq(1) p").html(data['name']);
            $("#infomodal table tr:eq(1) td:eq(1) p").html(data['phone']);
            $("#infomodal table tr:eq(2) td:eq(1) p").html(data['sex']==0?"男":"女");
            $("#infomodal table tr:eq(3) td:eq(1) p").html(data['department']);
            $("#infomodal table tr:eq(4) td:eq(1) p").html(data['position']);
            $("#infomodal table tr:eq(5) td:eq(1) p").html(data['idCard']);
            $("#infomodal table tr:eq(6) td:eq(1) p").html(data['bankNumber']);
            $("#infomodal table tr:eq(7) td:eq(1) p").html(data['joinTime']);
            $("#infomodal table tr:eq(8) td:eq(1) p").html(data['baseSalary']);

        },
        error:function(){

            alert("网络塞车");
        }
    })
}

function checkAll(){
    checkAllCheckBox("tbody")
}

function getAllcheck(){
    return getCheckBoxValues("tbody","chk");
}

function initTimeInput(){
    
    $("#timestart").datetimepicker({
        format: "yyyy-mm",
        startView: 3,
        minView: 3,
        autoclose:true,
        pickerPosition: "bottom-right"
    });
    var month = new Date().getMonth()+1;
    var time = month<10?"0"+month:month;
    $("#timestart").val(new Date().getFullYear()+"-"+time);
}

function showAddModal(){
    $("#myform :input[name='id']").val("");
    $("#editinfomodal").modal('show');
    $("#resetForm").trigger('click');
}

function showEditModal(){
    if(getAllcheck().length != 1){
        showAlert("只能选择一项进行修改",1);
        return false;
    }
    var id = getAllcheck()[0];
    $("#resetForm").trigger('click');
    $.ajax({
        url:'getemployeebyid.json?id='+id,
        type:'get',
        dataType:'json',
        success:function(data){
            $("#editinfomodal").modal('show');
            data = data.data;
            $.each(data, function(i) {
                $("#myform :input[name='"+i+"']").val(data[i]);
            });
        },
        error:function(){
            alert("网络塞车");
        }
    })
}

function delAllCheck(){
    if(confirm("您确认要删除选中员工吗？")){
        $.post("del_employee",getCheckBoxStr("tbody","chk"),function(data){
            showAlert(data.message);
        })
    }
}

function employee(id)
{
    this.id=id;
    this.state0=0;
    this.state1=0;
    this.state2=0;
    this.state3=0;
    this.changeState=changeState;

    function changeState(s0,s1,s2,s3){
        this.state0=s0;
        this.state1=s1;
        this.state2=s2;
        this.state3=s3;
    }
}

function editstate(state){
    var x = $("#tbody input[name='chk']:checked");
    var chk_val = [];
    var employees = new Array();
    var state0 = 0;
    var state1 = 0;
    var state2 = 0;
    var state3 = 0;

    x.each(function () {
        chk_val.push($(this).val());
        var employee_id = $(this).attr("employee_id");
        if($(this).attr("state") == 0){
            state0 ++ ;
        }
        if($(this).attr("state") == 1){
            state1 ++ ;
        }
        if($(this).attr("state") == 2){
            state2 ++ ;
        }
        if($(this).attr("state") == 3){
            state3 ++ ;
        }
        var flag = true;
        for (var i = 0 ; i < employees.length ; i ++ ){
            var e = employees[i];
            if(e.id == employee_id){
                employees.remove(e);
                e.changeState(state0,state1,state2,state3);
                flag = false;
                employees.push(e);
            }
        }
        if(flag){
            var e = new employee(employee_id);
            e.changeState(state0,state1,state2,state3);
            employees.push(e);
        }
    });

    var str = "";
    for(var i = 0 ;i < chk_val.length ; i++ ){
        str += "chk=" + chk_val[i];
        if(i + 1 != chk_val.length){
            str += "&";
        }
    }
    str += "&editinfo=" + JSON.stringify(employees)+"&state="+state+"&year=2016&month=5";
    console.log(JSON.stringify(employees));
    $.post("editdaysignstate",str,function(data){
        showAlert(data.message);
        search();
    },'json')
}