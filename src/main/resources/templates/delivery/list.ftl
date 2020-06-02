<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/jquery-3.5.0.min.js"></script>
    <script type="text/javascript" src="/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/delivery.js"></script>
</head>
<body>

<div id="wrapper" class="toggled">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="alert alert-dismissable alert-info">
                        <a href="/delivery/application" class="alert-link">查看申请列表</a>
                    </div>
                    <table class="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>姓名</th>
                            <th>身份证号码 </th>
                            <th>手机号</th>
                            <th>工作ID</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#if deliveryPage.getTotalPages()==0>
                        <tr><td colspan="5">所在超市没有配送员！</td></tr>
                        <#else>
                        <#list deliveryPage.content as deliverer>
                        <tr>
                            <td>${deliverer.name}</td>
                            <td>${deliverer.idNum}</td>
                            <td>${deliverer.phone}</td>
                            <td>${deliverer.deliveryId}</td>
                            <td><a href="/delivery/fire?idNum=${deliverer.idNum}">解雇</a></td>
                        </tr>
                        </#list>

                        </#if>
                        </tbody>
                    </table>
                </div>

                <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                    <#if currentPage<2>
                        <li class="disabled">
                            <a>上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/delivery/list?page=${currentPage-1}&size=${size}">上一页</a>
                        </li>
                    </#if>
                    <#if deliveryPage.getTotalPages()==0>
                        <li hidden></li>
                    <#else>
                        <#list 1..deliveryPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li>
                                    <a href="/delivery/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>

                        </#list>
                    </#if>
                    <#if currentPage == deliveryPage.getTotalPages()||deliveryPage.getTotalPages()==0>
                        <li class="disabled">
                            <a>下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/delivery/list?page=${currentPage+1}&size=${size}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>