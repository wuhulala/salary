/**
 * Created by xueaohui on 2016/1/17.
 */
var table;
$(function() {
    //初始化导航栏
   // initSidebar();
    initSelect();
    search();
    $("#myform").validation();
    $("#editActive").click(function(e){
        //第一种提示写法
        if ($("#myform").valid(this,'有未填写的内容')==false){
            return false;
        }

        $.post("save_monthallowance",$("#myform").serialize(),function(data){
            showAlert(data['message'],data['code']);
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
        dom: 'rt<"table-footer"<"col-md-3 col-sm-4"i><"col-md-5 col-sm-4"l><"col-md-4 col-sm-4"p>>',
        serverSide: true,//设置服务端模式,搜索需要根据传参实现
        ajax: {
            url: "getallmonthallowance.json",
            data: {
                department:$("#department").val(),
                position:$("#position").val(),
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
            {"data": "departmentByDepartmentId.name"},
            {"data":"positionByPositionId.name"},
            {"data":"housing"},
            {"data":"award"},
            {"data":"lateMoney"},
            {"data":"leaveMoney"},
            {"data":"absentMoney"},
            {"data":"overtimeMoney"}

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


                    var html = "<input id='chk"+meta.row+"'  name='chk' type='checkbox' value='"+row.id+"' class='cursor-pointer'>";

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



function checkAll(){
    checkAllCheckBox("tbody")
}

function getAllcheck(){
    return getCheckBoxValues("tbody","chk");
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
        url:'getmonthallowancebyid.json?id='+id,
        type:'get',
        dataType:'json',
        success:function(data){
            $("#editinfomodal").modal('show');
            data = data.data;
            $.each(data, function(i) {
                $("#myform :input[name='"+i+"']").val(data[i]);
            });

            $("#myform select[name='deparment']").val(data.departmentByDepartmentId.id);
            $("#myform select[name='position']").val(data.positionByPositionId.id);

        },
        error:function(){
            alert("网络塞车");
        }
    })
}

function delAllCheck(){
    if(confirm("您确认要删除选中补贴吗？")){
        $.post("del_monthallowance",getCheckBoxStr("tbody","chk"),function(data){
            showAlert(data.message);
            search();
        })
    }
}
