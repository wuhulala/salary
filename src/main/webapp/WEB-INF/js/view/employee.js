/**
 * Created by xueaohui on 2016/1/17.
 */
var table;
$(function() {
    //初始化导航栏
   // initSidebar();
    initSelect();
    search();
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
            search();
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
        dom: 'rt<"table-footer"<"col-md-2 col-sm-4"i><"col-md-6 col-sm-4"l><"col-md-4 col-sm-4"p>>',
        serverSide: true,//设置服务端模式,搜索需要根据传参实现
        ajax: {
            url: "getallemployee.json",
            data: {
                timeStart:$("#timestart").val(),
                timeEnd:$("#timeend").val(),
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
                showinfo(data.id);
            });
        },
        columns: [
            {"data": null},
            {"data": null},
            {"data": "name"},
            {"data":"phone"},
            {"data":"sex"},
            {"data":"departmentByDepartmentId.name"},
            {"data":"positionByPositionId.name"}
        ],
        columnDefs: [
            {
                targets: 4,

                render: function (data, type, row,   meta ) {


                    var html = row.sex == 0 ? "男" : "女";

                    return html;
                }
            },
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


                    var html = "<input id='chk"+meta.row+"' name='chk' type='checkbox' value='"+row.id+"' class='cursor-pointer'>";

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
            $("#infomodal table tr:eq(3) td:eq(1) p").html(data.departmentByDepartmentId.name);
            $("#infomodal table tr:eq(4) td:eq(1) p").html(data.positionByPositionId.name);
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
        format: "yyyy-mm-dd",
        autoclose: true,
        minView: "month",
        maxView: "decade",
        pickerPosition: "bottom-left"
    }).on("click", function (ev) {
        $("#timestart").datetimepicker("setEndDate", $("#timeend").val());

    });

    $("#timeend").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        minView: "month",
        maxView: "decade",
        pickerPosition: "bottom-left"
    }).on("click",function(ev){
        $("#timeend").datetimepicker("setStartDate", $("#timestart").val());

    });

    $("#jointime").datetimepicker({
        format: "yyyy-mm-dd",
        autoclose: true,
        minView: "month",
        maxView: "decade",
        pickerPosition: "top-left"
    })
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
            $("#myform :input[name='department']").val(data.departmentByDepartmentId.id);
            $("#myform :input[name='position']").val(data.positionByPositionId.id);
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
            search();
        })
    }
}