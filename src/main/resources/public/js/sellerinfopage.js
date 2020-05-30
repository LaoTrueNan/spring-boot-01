$(function () {
    var username=$.cookie('username');

    $(":input[name='username']").val(username);
    // $.ajax({
    //     async:false,
    //     type:'post',
    //     url:"/seller/sellerInfoPage",
    //     data:{username:username},
    //     datatype:'json',
    //     success:function (data) {
    //         $(":input[name='sellname']").val(data.name);
    //         $(":input[name='selladdress']").val(data.address);
    //         $(":input[name='openid']").val(data.id);
    //         $("img.img-thumbnail").attr("src",data.avatar);
    //         $(".panelcontent").text(data.bulletin);
    //     }
    //
    // })

    var url="/seller/sellerInfo?username="+username;
    $.get(url,function (data) {
        $(":input[name='openid']").val(data.openid);
    })

    $.post("/seller/sellerInfoPage",{username: username},function (data,success) {
        $(":input[name='sellname']").val(data.name);
        $(":input[name='selladdress']").val(data.address);

        $("img.img-thumbnail").attr("src",data.avatar);
        $(".panelcontent").text(data.bulletin);
    })
})