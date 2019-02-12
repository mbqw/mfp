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
//用户头像点击
function addFriends() {
    showMask();
    $("#userDetail").fadeIn(500);
}
//喜欢
function msg_like(id) {

}
//不喜欢
function msg_unlike(id) {

}
//标记为收藏
function msg_star(id,t) {
    if ($(t).children("span").hasClass("glyphicon-star")) {
        $(t).children("span").removeClass("glyphicon-star");
        $(t).children("span").addClass("glyphicon-star-empty");
    }else{
        $(t).children("span").removeClass("glyphicon-star-empty");
        $(t).children("span").addClass("glyphicon-star");
    }

}
//显示更多的信息
function moreContent() {
    if ($("#msg_p").html().length>50) {
        $("#msg_a").html("更多...");
        $("#msg_p").html("I like this picture");
    }else{
        $("#msg_a").html("收起");
        $("#msg_p").html("asddddddddddddddddddddddddddddddddddddddddddasddsad" +
            "saddddddddddddddddddddddddddd" +
            "asdddddddddddddddddddd");
    }

}