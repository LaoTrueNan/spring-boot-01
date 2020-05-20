package gzq.upc.controller;

import gzq.upc.dataobject.OrderMaster;
import gzq.upc.dataobject.SellerInfo;
import gzq.upc.dto.OrderDTO;
import gzq.upc.enums.ResultEnum;
import gzq.upc.exception.SellException;
import gzq.upc.service.OrderService;
import gzq.upc.service.SellerInfoService;
import gzq.upc.utils.gzqCookie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerInfoService sellerInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String,Object> map,
                             HttpServletRequest httpServletRequest){
        PageRequest request = PageRequest.of(page-1,size);
        SellerInfo sellerInfo= new SellerInfo();

        sellerInfo = sellerInfoService.findByUsername(gzqCookie.getMyCookie(httpServletRequest,"username"));

        Page<OrderDTO> orderDTOPage = orderService.findList(sellerInfo.getId(),request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e) {
            log.error("[卖家端取消订单] 查询不到订单");
            map.put("msg", e.getMessage());
            map.put("url", "/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("msg", "取消成功");
        map.put("url", "/seller/order/list");
        return new ModelAndView("common/success");
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){

        OrderDTO orderDTO = new OrderDTO();
        try {
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("[查询订单失败] 发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error",map);
        }

        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch(SellException e){
            log.error("订单完结失败");
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","订单完结成功！");
        map.put("url","/seller/order/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/distri")
    public ModelAndView distri(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.distribute(orderDTO);
        }catch(SellException e){
            log.error("发布订单失败");
            map.put("msg",e.getMessage());
            map.put("url","/seller/order/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg","订单发布成功，等待接单");
        map.put("url","/seller/order/list");
        return new ModelAndView("common/success",map);
    }

}
