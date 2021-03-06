package gzq.upc.controller;

import gzq.upc.dataobject.OrderMaster;
import gzq.upc.dataobject.Seller;
import gzq.upc.dataobject.SellerInfo;
import gzq.upc.dto.OrderDTO;
import gzq.upc.enums.ResultEnum;
import gzq.upc.exception.SellException;
import gzq.upc.repository.SellerRepository;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private SellerInfoService sellerInfoService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "5") Integer size,
                             Map<String,Object> map,
                             HttpServletRequest httpServletRequest){
        PageRequest request = PageRequest.of(page-1,size);
        SellerInfo sellerInfo= new SellerInfo();

        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        sellerInfo = sellerInfoService.findByUsername(currentUser);
        Integer status = sellerInfo.getId();
        //将现有的超市id读出
        List<Integer> sellIds = sellerRepository.findAll().stream().map(Seller::getId).collect(Collectors.toList());

        Page<OrderDTO> orderDTOPage = orderService.findList(sellerInfo.getId(),request);
        map.put("orderDTOPage",orderDTOPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("order/list", map);
    }

    @GetMapping("/cancel")
    @ResponseBody
    public String cancel(@RequestParam("orderId") String orderId){
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e) {
            log.error("[卖家端取消订单] 查询不到订单");
            return e.getMessage();
        }

        return "/seller/order/list";
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){

        OrderDTO orderDTO = new OrderDTO();
        orderDTO = orderService.findOne(orderId);
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    @ResponseBody
    public String finish(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch(SellException e){
            log.error("订单完结失败");
            return e.getMessage();
        }
        return "/seller/order/list";
    }

    @GetMapping("/distri")
    @ResponseBody
    public String distri(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.distribute(orderDTO);
        }catch(SellException e){
            log.error("发布订单失败");
            return e.getMessage();
        }

        return "/seller/order/list";
    }

}
