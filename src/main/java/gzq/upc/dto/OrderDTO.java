package gzq.upc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gzq.upc.dataobject.OrderDetail;
import gzq.upc.enums.OrderStatusEnum;
import gzq.upc.enums.PayStatusEnum;
import gzq.upc.utils.EnumUtil;
import lombok.Data;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Data
public class OrderDTO {
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus=OrderStatusEnum.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus=PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetailList;

    private Integer supermarket;

    @JsonIgnore
    public OrderStatusEnum getOrderStatusEnum(){
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }
    @JsonIgnore
    public PayStatusEnum getPayStatusEnum(){
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }
}
