/**
 * Created by Administrator on 2016/3/3.
 */
$(function(){

});

function checkLength0(){
    var data = $("#originPass").val();
    if(!data){
        $("#Message0").css("color","#ee0000").html("旧密码不能为空");
        return false;
    }
    $("#Message0").css("color","#00ee00").html("√");
    return true;
}
function checkLength1(){
    var passRegex = /^.{6,16}$/;
    var data = $("#newPass").val();
    if(!data){
        $("#Message1").css("color","#ee0000").html("新密码不能为空");
        return false;
    }
    if(!passRegex.test(data)){
        $("#Message1").css("color","#ee0000").html("新密码长度应为6-16位");
        return false;
    }
    $("#Message1").css("color","#00ee00").html("√");
    return true;
}
function checkLength2(){
    var data = $("#rePass").val();
    var data2 = $("#newPass").val();
    if(!data){
        $("#Message2").css("color","#ee0000").html("确认密码不能为空");
        return false;
    }
    if(data!=data2){
        $("#Message2").css("color","#ee0000").html("两次密码不一致");
        return false;
    }
    $("#Message2").css("color","#00ee00").html("√");
    return true;
}

function editPass1(){

    if(checkLength0()&&checkLength1()&&checkLength2()) {
        $.ajax({
            url: 'editPass',
            data: $("#passForm").serialize(),
            dataType: 'json',
            type:'post',
            success: function (data) {
                if (data['message'] == "修改成功") {
                    alert("密码修改成功，将返回登录页面");
                    window.location.href = "logout";
                    $("errorMessage").html("");
                } else {
                   showAlert("原密码错误",0);
                }
            },
            error: function (data) {
                showAlert("修改失败");
            }
        })
    }
}