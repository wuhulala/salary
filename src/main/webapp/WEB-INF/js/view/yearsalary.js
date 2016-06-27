/**
 * Created by xueaohui on 2016/1/17.
 */
var table;
$(function() {
    //初始化导航栏
   // initSidebar();
    initSelect();
    initTimeInput();
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
    });

} );

function format ( d ) {

    var html = '<table class="table table-bordered" cellspacing="0" border="0" style="width: 100%"><thead><tr > <th>1月</th> <th>2月</th> <th>3月</th> <th>4月</th> <th>5月</th> <th>6月</th> <th>7月</th> <th>8月</th> <th>9月</th> <th>10月</th> <th>11月</th> <th>12月</th> </tr></thead><tbdoy><tr>';
    var info = d.everyMonthSalary;
    var l = info.split(",");
    for(var i = 0 ; i < l.length ; i ++ ){
        html += "<td>"+l[i]+"</td>"
    }
    for(var i = l.length ; i < 12 ; i ++){
        html += "<td>"+0+"</td>"
    }
    html += '</tr></tbody></table>';
    // `d` is the original data object for the row
    return html;
}

function search(){
    if(table!=null) {
        table.draw();
    }else{
        createTable();
    }
}

function initTimeInput(){

    $("#timestart").datetimepicker({
        format: "yyyy",
        startView: 4,
        minView: 4,
        autoclose:true,
        pickerPosition: "bottom-right"
    });
    $("#timestart").val(new Date().getFullYear());
}
function createTable(){
    // DataTable
    table = $('#userTable').DataTable( {
        dom: 'rt<"table-footer"<"col-md-3 col-sm-4"i><"col-md-5 col-sm-4"l><"col-md-4 col-sm-4"p>>',
        serverSide: true,//设置服务端模式,搜索需要根据传参实现
        ajax: {
            url: "getallyearsalary.json",
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

        },
        columns: [
             {
                "class":          'details-control',
                "orderable":      false,
                "data":           null,
                "defaultContent": ''
            },
            {"data": null},
            {"data": "employeeByEmployeeId.name"},
            {"data": "employeeByEmployeeId.departmentByDepartmentId.name"},
            {"data":"employeeByEmployeeId.positionByPositionId.name"},
            {"data":"totalSalary"}
        ],
        columnDefs: [ {
                targets: 1,

                render: function (data, type, row,   meta ) {


                    var html = meta.row+1;

                    return html;
                }
            },


        ],
        lengthMenu: [[10, 20, 50, -1], ["10", "20", "50", "全部"]],//自定义单页显示
        initComplete:function(){

        }
    } );

    $('#userTable tbody').on('click', 'td.details-control', function () {
        var tr1 = $(this).closest('tr');
        var row = table.row( tr1 );
        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr1.removeClass('shown');
        }
        else {
            // Open this row
            row.child( format(row.data()) ).show();
            tr1.addClass('shown');
        }
    } );
}