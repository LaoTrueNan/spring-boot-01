$(function () {
    $("tr td:nth-child(5) a").bind('click',function () {
        var op = $(this).text();
        var msg="是否确认"+op+"?";
        var url = $(this).attr('href');
        if(confirm(msg)==true){
            $.get(url,function (data) {
                if(data=="/delivery/list"){
                    alert(op+"成功！");
                    location.href=data;
                }else {
                    alert(data);
                }
            })
        }else{
            return false;
        }
        return false;
    })
})