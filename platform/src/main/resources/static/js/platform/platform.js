$(function () {

})
//遮罩层
$("#mask").click(function () {
    $("#mask").fadeOut(500);
    $(".myiframe").fadeOut(500);
    $("#userDetail").fadeOut(500);
    $(document).unbind("scroll");
});
//遮罩层
function showMask() {
    var tops = $(document).scrollTop();//当页面滚动时，把当前距离赋值给页面，这样保持页面滚动条不动
    $(document).bind("scroll",function (){$(document).scrollTop(tops); });
    $("#mask").css("height","100%");
    $("#mask").css("width","100%");
    $("#mask").fadeIn(500);
}
//用户详情
function showUserDetail(id) {
    $(".myiframe").attr("src","/platform/detail/"+id);
    showMask();
    $(".myiframe").fadeIn(500);
}
//喜欢
function msg_like(id) {
    $.ajax({
        type:"POST",
        data:{
            id:id,
            ylike:1
        },
        dataType:"JSON",
        url:'/msg/update',
        success:function (data) {
            if (data.success){
                layer.tips('+1', '#like'+id,{
                    tips: [1, '#00bfff'],
                    time:1000
                });
            } else{
                layer.msg(data.info, function(){
                });
            }
        }
    });
}
//不喜欢
function msg_unlike(id) {
    $.ajax({
        type:"POST",
        data:{
            id:id,
            unlike:1
        },
        dataType:"JSON",
        url:'/msg/update',
        success:function (data) {
            if (data.success){
                layer.tips('-1', '#like'+id,{
                    tips: [3, '#00bfff'],
                    time:1000
                });
            } else{
                layer.msg(data.info, function(){
                });
            }
        }
    });
}
//标记为收藏
function msg_star(id,u_id,t) {
    var star=0;
    if ($(t).children("span").hasClass("glyphicon-star")) {
        star =-1;
    }else{
        star =1;
    }
    $.ajax({
        type:"POST",
        data:{
            id:id,
            u_id:u_id,
            star:star
        },
        dataType:"JSON",
        url:'/msg/update',
        success:function (data) {
            if (data.success){
                if (star ==1){
                    $(t).children("span").removeClass("glyphicon-star-empty");
                    $(t).children("span").addClass("glyphicon-star");
                } else{
                    $(t).children("span").removeClass("glyphicon-star");
                    $(t).children("span").addClass("glyphicon-star-empty");
                }
            } else{
                layer.msg(data.info, function(){
                });
            }
        }
    });
}

//查看照片
function viewPhoto(id) {
    layer.photos({
        photos: '#view'+id
        ,anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
    });
}
//显示更多的信息
function moreContent(id) {
    $.ajax({
        type:"POST",
        data:{
            id:id
        },
        dataType:"JSON",
        url:'/msg/getContent',
        success:function (data) {
            if (data.success){
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 0,
                    shadeClose: true,
                    skin: 'contentHtml',
                    content: "<div style='padding: 20px'>"+data.info+"</div>"
                });
            } else{
                layer.msg(data.info, function(){
                });
            }
        }
    });
}
//弹出发布信息框
function addMsg() {
    layer.open({
        title:"发布",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['600px','500px'],
        fixed: false, //不固定
        maxmin: true,
        content: '/msg/toAdd/'+$("#user_id").val()
    });
}
//弹出用户的收藏
function userStars() {
    layer.open({
        title:"收藏",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['800px','600px'],
        fixed: false, //不固定
        maxmin: true,
        content: '/msg/list/'+$("#user_id").val()
    });
}