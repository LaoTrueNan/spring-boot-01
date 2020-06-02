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
    <script type="text/javascript" src="/js/product.js"></script>
</head>
<body>

<div id="wrapper" class="toggled">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/seller/product/save" method="post" class="myForm">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" name="productName" value="${(productInfo.productName)!''}" autocomplete="off"/>
                                <span id="message1"></span>
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input id="productprice" type="text" class="form-control" name="productPrice" value="${(productInfo.productPrice)!''}"  autocomplete="off"/>
                                <span id="message2"></span>
                         </div>
                         <div class="form-group">
                                <label>库存</label>
                                <input type="number" class="form-control" name="productStock" value="${(productInfo.productStock)!''}" autocomplete="off"/>
                             <span id="message3"></span>
                         </div>
                         <div class="form-group">
                                <label>图片</label>
                             <input id="productIcon" name="productIcon" type="text" value="${(productInfo.productIcon)!''}" autocomplete="off"/>
                             <span id="message4"></span>
                             <div class="file-loading">
                                 <p class="help-block">请输入新图片链接或覆盖原有链接</p>
                             </div>
                            </div>
                            <div class="checkbox">
                                <label>类目</label>
                                <select name="categoryType" class="form-control">
                                    <#list categoryList as category>
                                        <option value="${category.categoryType}" <#if (productInfo.categoryType)?? && productInfo.categoryType == category.categoryType>
                                                selected
                                        </#if>
                                        >${category.categoryName}
                                        </option>
                                    </#list>
                                </select>
                            </div>
                            <input hidden type="number" name="productStatus" value="${(productInfo.productStatus)!''}">
                            <input hidden type="text" name="productId" value="${(productInfo.productId)!''}">
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