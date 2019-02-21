
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
                    layer.tips('已收藏', '#star'+id,{
                        tips: [1, '#00bfff'],
                        time:1000
                    });
                } else{
                    $(t).children("span").removeClass("glyphicon-star");
                    $(t).children("span").addClass("glyphicon-star-empty");
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

function toCommont(id) {

}

//查看评论
function msg_comment(id) {
    $.ajax({
        type:"POST",
        data:{
            id:id
        },
        dataType:"JSON",
        url:'/msg/getComment',
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
        area: ['800px','600px'],
        fixed: false, //不固定
        maxmin: true,
        content: '/msg/toList/'+$("#user_id").val(),
        cancel: function(index, layero){
            layer.close(index);
            window.location.href="/platform/index/"+$("#user_id").val();
        }
    });
}
//user详情页
function toDetail(u_id) {
    layer.open({
        type: 2,
        title:"用户信息",
        scrollbar:false,
        anim: 5,
        type: 2,
        area: ['500px','100%'],
        fixed: false, //不固定
        resize:false,
        content: '/user/toDetail/'+$("#user_id").val()+'/'+u_id,
        cancel: function(index, layero){
            layer.close(index);
            window.location.href="/platform/index/"+$("#user_id").val();
        }
    });
}
//换头像
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