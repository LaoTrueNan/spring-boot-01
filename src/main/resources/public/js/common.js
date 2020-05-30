$(function () {
    if($.cookie('username')==null){
        alert("请先登录！");
        setTimeout('location.href="/home.html"',1000);
    }
});