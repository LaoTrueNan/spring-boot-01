<html>
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">
<#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="container">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <form role="form" action="/sell/seller/product/save" method="post">
                            <div class="form-group">
                                <label>名称</label>
                                <input type="text" class="form-control" name="productName" value="${(productInfo.productName)!''}" autocomplete="off"/>
                            </div>
                            <div class="form-group">
                                <label>价格</label>
                                <input type="text" class="form-control" name="productPrice" value="${(productInfo.productPrice)!''}"  autocomplete="off"/>
                         </div>
                         <div class="form-group">
                                <label>库存</label>
                                <input type="number" class="form-control" name="productStock" value="${(productInfo.productStock)!''}" autocomplete="off"/>
                         </div>
                         <div class="form-group">
                                <label>图片</label>
                             <input id="productIcon" name="productIcon" type="text" value="${(productInfo.productIcon)!''}" autocomplete="off"/>

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
                            <button type="submit" class="btn btn-default">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
</html>