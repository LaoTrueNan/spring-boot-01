$(function () {
    $("a[name='distri']").bind('click',function () {
        var msg="您确认要"+$(this).text()+"该订单?";
        if(confirm(msg)==true){
            var url = $(this).attr('href');
            $.get(url,function (data) {
                if(data=="/seller/order/list"){
                    alert($("a[name='distri']:first").text()+"成功！");
                    location.href=data;
                }else{
                    alert(data);
                }
            })
        }else{
            return false;
        }
        return false;
    })
    $("a[type='button']").bind('click',function () {
        var msg="您确认要"+$(this).text()+"?";
        if(confirm(msg)==true){
            var url = $(this).attr('href');
            $.get(url,function (data) {
                if(data=="/seller/order/list"){
                    alert($("a[type='button']").text()+"成功！");
                    location.href=data;
                }else{
                    alert(data);
                }
            })
        }else{
            return false;
        }
        return false;
    })
})