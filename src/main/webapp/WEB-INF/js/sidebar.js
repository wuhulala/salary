/**
 * Created by xueaohui on 2016/1/23.
 */

$(function(){
    $("#sidebar").css("background-color","#1d2939").css("width","200").css("height",window.outerHeight);
    $("#content").css("width",window.outerWidth - 260).css("position","absolute").css("left","250");
    initSidebar();
    $('#main-nav li').on('hide.bs.collapse', function () {
        var i = $(this).children('.first-menu').children('span').children('i');
        i.removeClass('glyphicon-minus').addClass('glyphicon-plus');
    });
    $('#main-nav li').on('show.bs.collapse', function () {
        var i = $(this).children('.first-menu').children('span').children('i');
        i.removeClass('glyphicon-plus').addClass('glyphicon-minus');
        clear($(this).index());
        $(this).addClass('active');
    });
});

function initSidebar(){
    var id = getPageId();
    id = id + "Menu";
    $('#'+id).addClass("active");
    $('#'+id).parent().parent().collapse('show');
    $('#'+id).parent().parent().parent().addClass("active");
    $('#'+id).parent().parent().parent().children('.first-menu').children('span').children('i').removeClass('glyphicon-plus').addClass('glyphicon-minus');
}

function clear(data){
    $("#main-nav li").each(function(){
        if($(this).index() != data){
            $(this).children('.first-menu').children('span').html('<i  class="pull-right glyphicon glyphicon-plus"></i>');
            $(this).children('.collapse').collapse('hide');
            $(this).removeClass('active');
        }
    })
}

function hideSideBar(){
    $("#main-nav").hide();
    $("#show-sidebar").show();
    $("#sidebar").css("width","50");
    $("#content").css("width",window.outerWidth - 60).css("position","absolute").css("left","50");
}

function showSideBar(){
    $("#main-nav").show();
    $("#show-sidebar").hide();
    $("#sidebar").css("width","200");
    $("#content").css("width",window.outerWidth - 260).css("position","absolute").css("left","250");
}
