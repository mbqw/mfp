
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
function msg_star(id,t) {
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
            u_id:$("#user_id").val(),
            star:star
        },
        dataType:"JSON",
        url:'/msg/update',
        success:function (data) {
            if (data.success){
                if (star ==1){
                    $(t).children("span").removeClass("glyphicon-star-empty");
                    $(t).children("span").addClass("glyphicon-star");
                    window.parent.$("#star"+id).children("span").removeClass("glyphicon-star-empty");
                    window.parent.$("#star"+id).children("span").addClass("glyphicon-star");
                    layer.tips('已收藏', '#star'+id,{
                        tips: [1, '#00bfff'],
                        time:1000
                    });
                } else{
                    $(t).children("span").removeClass("glyphicon-star");
                    $(t).children("span").addClass("glyphicon-star-empty");
                    window.parent.$("#star"+id).children("span").removeClass("glyphicon-star");
                    window.parent.$("#star"+id).children("span").addClass("glyphicon-star-empty");
                    layer.tips('取消收藏', '#star'+id,{
                        tips: [1, '#00bfff'],
                        time:1000
                    });
                }
            } else{
                layer.msg(data.info, function(){
                });
            }
        }
    });
}
//查看评论
function msg_comment(id) {
    var index = parent.layer.open({
        title:"评论",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['600px','100%'],
        fixed: false, //不固定
        maxmin: true,
        content: '/msg/toComment/'+$("#user_id").val()+"/"+id
    });
    //layer.full(index);
}
//我的评论
function comments() {
    parent.layer.open({
        title:"评论",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['70%','90%'],
        fixed: false, //不固定
        maxmin: true,
        content: '/msg/toCommentList/'+$("#user_id").val()
    });
}
//查看照片
function viewPhoto(id) {
    layer.photos({
        photos: '#view'+id
        ,anim: 5
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
                parent.layer.open({
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
        area: ['70%','90%'],
        fixed: false, //不固定
        maxmin: true,
        content: '/msg/toList/'+$("#user_id").val(),
    });
}
//user详情页
function toDetail(u_id) {
    layer.open({
        type: 1,
        title:"用户信息",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['500px','100%'],
        fixed: false, //不固定
        resize:false,
        maxmin: true,
        content: '/user/toDetail/'+$("#user_id").val()+'/'+u_id,
    });
}
//跳转到换头像页面
function changeAvatar(user_id) {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    parent.layer.open({
        type: 2,
        title:"更改头像",
        scrollbar:false,
        area: ['650px','550px'],
        content: '/user/toAvatar/'+user_id,

    });
}
//主页面头像更改
function parentChangeAvatar(id,src) {
    $(".avatar"+id).each(function () {
        $(this).attr("src",src+"?"+new Date());
    })
    $("#layui-layim-close").children("img").attr("src",src+"?"+new Date());
}
//主页面名称更改
function parentChangeName(id,src) {
    $(".username"+id).each(function () {
        $(this).text(src);
    })
    $(".layui-layim-user").text(src);
}
//修改资料
function toUpdate(u_id) {
    var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
    parent.layer.close(index);
    parent.layer.open({
        type: 2,
        title:"修改资料",
        scrollbar:false,
        anim: 5,
        area: ['400px','550px'],
        fixed: false, //不固定
        content: '/user/toUpdate/'+u_id
    });
}
//通讯面板
function userIM() {
    /*layer.open({
        type: 1,
        title:"好友",
        area: ['420px', '240px'], //宽高
        content: 'html内容'
    });*/
    layer.open({
        title:"好友",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['70%','90%'],
        fixed: false, //不固定
        maxmin: true,
        content: '/im/toList/'+$("#user_id").val(),
        cancel: function(index, layero){
            layer.close(index);
            window.parent.location.reload(true);
        }
    });
}
