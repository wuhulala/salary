/**
 * Created by xueaohui on 2016/1/23.
 * des:  获取本页面的一些属性
 */

function initSelect(){
    $.ajax({
        url:"getallposition.json",
        type:'get',
        dataType:'json',
        async:false,
        success:function(data){
            var html1 = "<option value='-1'>职位</option>";
            var html ="";
            var info = data.all;
            for(var i = 0 ; i < info.length ; i ++ ){
                html += "<option value='"+info[i].id+"'>"+info[i].name+"</option>";
            }
            $("#searchform select[name='position']").html(html1 + html);
            $("#myform select[name='position']").html(html);

        }
    });

    $.ajax({
        url:"getalldepartment.json",
        type:'get',
        dataType:'json',
        async:false,
        success:function(data){
            var html1 = "<option value='-1'>部门</option>";
            var info = data.all;
            var html = "";
            for(var i = 0 ; i < info.length ; i ++ ){
                html += "<option value='"+info[i].id+"'>"+info[i].name+"</option>";
            }
            $("#searchform select[name='department']").html(html1+html);
            $("#myform select[name='department']").html(html);

        }
    })
}

function getPageId(){

    var x = window.location.pathname;
    var array = x.split("/");
    var array2 = array[array.length-1].split(".");
    var id = array2[0];
    //alert(data);
    return id;
}


/**
 * 返回日期
 * @param longTypeDate  时间的long类型的数职
 * @returns {string} YYYY-MM-DD
 */
function longToTime(longTypeDate){
    var dateTypeDate = "";
    var date = new Date();
    date.setTime(longTypeDate);
    dateTypeDate += date.getFullYear();   //年
    dateTypeDate += "-" + getMonth(date); //月
    dateTypeDate += "-" + getDay(date);   //日

    return dateTypeDate;
}

/**
 * 返回日期时间
 * @param longTypeDate 时间的long类型的数职
 * @returns {string} YYYY-MM-DD HH:mm
 *
 */
function longtoDateTime(longTypeDate){
    var dateTypeDate = "";
    var date = new Date();
    date.setTime(longTypeDate);
    dateTypeDate += date.getFullYear();   //年
    dateTypeDate += "-" + getMonth(date); //月
    dateTypeDate += "-" + getDay(date);   //日
    dateTypeDate += " " + add0(date.getHours());
    dateTypeDate += ":" + add0(date.getMinutes());
    return dateTypeDate;
}

/**
 * 返回日期时间
 * @param longTypeDate 时间的long类型的数职
 * @returns {string} YYYY-MM-DD HH:mm:ss
 *
 */
function longtoDatePickTime(longTypeDate){
    var dateTypeDate = "";
    var date = new Date();
    date.setTime(longTypeDate);
    dateTypeDate += date.getFullYear();   //年
    dateTypeDate += "-" + getMonth(date); //月
    dateTypeDate += "-" + getDay(date);   //日
    dateTypeDate += " " + add0(date.getHours());
    dateTypeDate += ":" + add0(date.getMinutes());
    dateTypeDate += ":" + add0(date.getMilliseconds());
    return dateTypeDate;
}

//返回 01-12 的月份值
function getMonth(date){
    var month = "";
    month = date.getMonth() + 1; //getMonth()得到的月份是0-11
    if(month<10){
        month = "0" + month;
    }
    return month;
}

//返回01-30的日期
function getDay(date){
    var day = "";
    day = date.getDate();
    if(day<10){
        day = "0" + day;
    }
    return day;
}
function add0(data){
    if(data<10){
        return "0"+data ;
    }
    return data;
}

/**
 * Created by Administrator on 2015-08-26.
 */
function getRootPath()
{
    var pathName = window.location.pathname.substring(1);
    var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
    return window.location.protocol + '//' + window.location.host + '/'+ webName + '/';
}

function showAlert(data){
    var arg = arguments;

    $("#alert-info").removeClass("alert-info").removeClass('alert-danger');

    if(arg&&arg.length==2&&arg[1]==0){
        $("#alert-info").addClass('alert-danger');
    }else{
        $("#alert-info").addClass("alert-success");
    }
    $("#alert-info p").html(data)
    $("#alert-info").fadeIn();
    setTimeout('hideAlert()',1000);
}
function hideAlert(){
    $("#alert-info").fadeOut();
}

/**
 * 获取选中的框
 * @param id
 * @param name
 * @returns {string}
 */
function getCheckBoxStr(id,name){
    var x = $("#"+id+" input[name='"+name+"']:checked");
    var chk_val = [];
    x.each(function () {
        chk_val.push($(this).val());
    });
    var str = "";
    for(var i = 0 ;i < chk_val.length ; i++ ){
        str += "chk=" + chk_val[i];
        if(i + 1 != chk_val.length){
            str += "&";
        }
    }
    return str;
}


function getCheckBoxValues(id,name){
    var x = $("#"+id+" input[name='"+name+"']:checked");
    var chk_val = [];
    x.each(function () {
        chk_val.push($(this).val());
    });

    return chk_val;
}
/**
 * 选中所有
 * @returns {boolean}
 */
function checkAllCheckBox(id){
    var x = $("#"+id+" input:checkbox");
    if($("#checkallbtn").prop("checked")==true){
        for(var j in x){
            x[j].checked=true;
        }
        return false;
    }
    for(var i in x){
        if(x[i].checked==false){
            for(var j in x){
                x[j].checked=true;
            }
            return true;
        }
    }
    for(var j in x){
        x[j].checked=false;
    }
}

/**
 * 取消全选按钮
 */
function cancelCheckAll(){
    $("#checkallbtn").prop("checked",false);
}
/**
 * 添加array的remove方法
 * @param obj
 */
Array.prototype.remove=function(obj){
    for(var i =0;i <this.length;i++){
        var temp = this[i];
        if(!isNaN(obj)){
            temp=i;
        }
        if(temp == obj){
            for(var j = i;j <this.length;j++){
                this[j]=this[j+1];
            }
            this.length = this.length-1;
        }
    }
}

/**
 * 报表打印
 */
function printTable() {
    var oldDocumentBody;
    oldDocumentBody = document.body.innerHTML;
    var item = $("#userTable")[0];

    html2canvas(item, {
        onrendered: function (canvas_source) {
            var numImage = 0;

            var pasWidth = canvas_source.width;
            var pasHeight = canvas_source.height;

            // Reinitialisation de la page courante
            document.body.innerHTML = "<html><head><title></title></head><body></body>";

            // Creation d'un canvas tampon
            var canvas_tampon = document.createElement("canvas");
            canvas_tampon.height = pasHeight;
            canvas_tampon.width = pasWidth;
            var canvas_tampon_context = canvas_tampon.getContext('2d');

            // Parcours du canvas_source dans la hauteur
            var heightRestantAImprimer = canvas_source.height;
            var numLigne = 1;
            while (heightRestantAImprimer > 0) {

                // Parcours du canvas_source dans la largeur
                var widthRestantAImprimer = canvas_source.width;
                var numColonne = 1;
                while (widthRestantAImprimer > 0) {
                    numImage++;

                    // Export d'une partie du canvas_source dans le canvas_tampon
                    canvas_tampon_context.drawImage(canvas_source, ((numColonne - 1) * pasWidth), ((numLigne - 1) * pasHeight), pasWidth, pasHeight, 0, 0, pasWidth, pasHeight);

                    // Creation d'une image afin de contenir la découpe
                    var img = new Image;
                    img.id = 'tempPrintImage' + numImage;
                    img.src = canvas_tampon.toDataURL();

                    // Ajout de l'image à la page courante
                    document.body.appendChild(img);

                    // Passage à la colonne suivante
                    widthRestantAImprimer = widthRestantAImprimer - pasWidth;
                    numColonne++;
                }

                // Passage à la ligne suivante
                heightRestantAImprimer = heightRestantAImprimer - pasHeight;
                numLigne++;
            }

            // Print Document
            window.print();

            // Restore document
            document.body.innerHTML = oldDocumentBody;

            // Clean memory
            oldDocumentBody = "";
        }
    });
}
