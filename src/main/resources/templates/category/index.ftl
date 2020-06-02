<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        span{
            color: red;
        }
    </style>
    <script type="text/javascript" src="/jquery-3.5.0.min.js"></script>
    <script type="text/javascript" src="/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/category.js"></script>
</head>
<body>

<div id="wrapper" class="toggled">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/seller/category/save" method="post">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" name="categoryName" value="${(category.categoryName)!''}" autocomplete="off"/>
                                <span id="message1"></span>
                            </div>
                            <div class="form-group">
                                <label>type</label>
                                <input type="number" class="form-control" name="categoryType" value="${(category.categoryType)!''}" autocomplete="off"/>
                                <span id="message2"></span>
                            </div>
                            <div class="form-group" hidden="hidden">
                            <label>type</label>
                            <input type="number" name="categoryId" value="${(category.categoryId)!''}" />

                        </div>
                            <div class="form-group" hidden="hidden">
                                <label>type</label>
                                <input type="number" name="supermarket" value="${(category.supermarket)!''}" />

                            </div>
                            <button type="button" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>