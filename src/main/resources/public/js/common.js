$(function () {
    if($.cookie('username')==null){
        alert("请先登录！");
        setTimeout('location.href="/home.html"',1000);
    }else if($.cookie('isBind')==0){
        alert("请先与超市绑定！");
        setTimeout('location.href="/common/sellerinfopage.html"',200);
    }
});