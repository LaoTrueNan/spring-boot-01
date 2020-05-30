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
		location.href=$(this).attr("href");
    })
	function freemarker(e) {
		e.bind('click',function () {
            $("iframe").attr("src",$(this).attr('href'));
        })
    }
    freemarker($(".sidebar-nav li:nth-child(3)"));
    freemarker($(".sidebar-nav li:nth-child(4)"));
    freemarker($(".hide li"));
})

