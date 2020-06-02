$(function(){
	$("#infoIcon").click(function(){
		$(this).next().slideToggle();
	})
	
	$(".toggledown").click(function(){
		$(this).next().slideToggle();
	})

	function showRight(e) {
		e.bind('click',function () {
			var page=e.attr("href");
			$("iframe").attr("src",page);
        })
    }

    showRight($(".sidebar-nav li:nth-child(2)"));
    showRight($(".info_op ul li:nth-child(1)"));

    $(".info_op ul li:nth-child(3)").bind('click',function () {
    	var msg="确认退出？"
		if(confirm(msg)==true){
		location.href=$(this).attr("href");

		}else{
			return false;
		}
    })

	function freemarker(e) {
		e.bind('click',function () {
            $("iframe").attr("src",$(this).attr('href'));
        })
    }
    freemarker($(".sidebar-nav li:nth-child(3)"));
    freemarker($(".sidebar-nav li:nth-child(4)"));
    freemarker($("ul.hide li"));

    var username = $.cookie("username");
    $.ajax({
		async:false,
		type:'post',
		url:'/seller/sellerInfoPage',
		data:{username: username},
		datatype:'json',
		success:function (data) {
    	if(data.address==""){
            $("#infoIcon").attr("src","/img/headicon.jpg");
		}else{
        	$("#infoIcon").attr("src",data.avatar);
		}

        }
	})


})

