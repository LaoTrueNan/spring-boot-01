<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="/jquery-3.5.0.min.js"></script>
    <script type="text/javascript" src="/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/category.js"></script>
</head>
<body>

<div id="wrapper" class="toggled">
    <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>类目id</th>
                                <th>类目名称</th>
                                <th>type</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list categoryList as productCategory>
                            <tr>
                                <td>${productCategory.categoryId}</td>
                                <td>${productCategory.categoryName}</td>
                                <td>${productCategory.categoryType}</td>
                                <td><a href="/seller/category/index?categoryId=${productCategory.categoryId}">修改</a></td>
                                <td><a href="/seller/category/del?categoryId=${productCategory.categoryId}">删除</a></td>
                            </tr>
                            </#list>

                            </tbody>
                        </table>
                    </div>


                </div>
            </div>
    </div>

</div>

</body>
</html>