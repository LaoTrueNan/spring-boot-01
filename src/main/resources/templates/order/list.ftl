<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
        .gzqheader{
            margin-top: 35px;
            margin-bottom: 10px;
            height: 35px;
            width: 100%;
            background: #f1f1f1;
        }
        .gzqheader h2{
            display: inline;
            margin-top: 0;
        }
        .gzqheader img{
            vertical-align: bottom;
        }
    </style>
    <script type="text/javascript" src="/jquery-3.5.0.min.js"></script>
    <script type="text/javascript" src="/jquery.cookie.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/order.js"></script>
</head>
<body>

<div id="wrapper" class="toggled">
    <div id="page-content-wrapper">
        <div class="gzqheader"><img src="/img/list2.png" height="30px"><h2>订单列表</h2></div>
            <div class="container-fluid">
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-striped table-hover">
                            <thead>
                            <tr>
                                <th>订单id</th>
                                <th>姓名</th>
                                <th>手机号</th>
                                <th>地址 </th>
                                <th>金额</th>
                                <th>订单状态</th>
                                <th>支付状态</th>
                                <th>创建时间</th>
                                <th colspan="2">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <#list orderDTOPage.content as orderDTO>
                            <tr>
                                <td>${orderDTO.orderId}</td>
                                <td>${orderDTO.buyerName}</td>
                                <td>${orderDTO.buyerPhone}</td>
                                <td>${orderDTO.buyerAddress}</td>
                                <td>${orderDTO.orderAmount}</td>
                                <td>${orderDTO.getOrderStatusEnum().message}</td>
                                <td>${orderDTO.getPayStatusEnum().message}</td>
                                <td>${orderDTO.createTime}</td>
                                <td><a href="/seller/order/detail?orderId=${orderDTO.orderId}">详情</a></td>

                                <td>
                                    <#if orderDTO.getOrderStatus()==0><a name="distri" href="/seller/order/distri?orderId=${orderDTO.orderId}">发布</a>
                                    <#else><span disabled="true" style="color: #CCCCCC">不可用</span>
                                    </#if>
                                </td>

                            </tr>
                            </#list>

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
                                <a href="/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
                            </li>
                        </#if>
                            <#if orderDTOPage.getTotalPages()==0>
                                <li hidden></li>
                            <#else>
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#if currentPage == index>
                                <li class="disabled"><a>${index}</a></li>
                            <#else>
                                <li>
                                    <a href="/seller/order/list?page=${index}&size=${size}">${index}</a>
                                </li>
                            </#if>

                        </#list>
                            </#if>
                        <#if currentPage == orderDTOPage.getTotalPages()||orderDTOPage.getTotalPages()==0>
                            <li class="disabled">
                                <a>下一页</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a>
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