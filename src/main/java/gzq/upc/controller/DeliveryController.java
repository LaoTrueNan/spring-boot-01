package gzq.upc.controller;

import gzq.upc.dataobject.Deliverer;
import gzq.upc.dataobject.Seller;
import gzq.upc.dataobject.Tasks;
import gzq.upc.exception.SellException;
import gzq.upc.repository.SellerRepository;
import gzq.upc.service.DeliveryService;
import gzq.upc.service.SellerInfoService;
import gzq.upc.service.TasksService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/delivery")
@Slf4j
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private TasksService tasksService;

    @GetMapping("/list")
    public ModelAndView deliveryList(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "5") Integer size,
                                     Map<String,Object> map,
                                     HttpServletRequest httpServletRequest){
        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        Integer status = sellerInfoService.findByUsername(currentUser).getId();
        //将现有的超市id读出
        List<Integer> sellIds = sellerRepository.findAll().stream().map(Seller::getId).collect(Collectors.toList());
        if(sellIds.indexOf(status) == -1){
            map.put("msg","您还未与超市绑定！请前去绑定");
            map.put("url","/seller/sellerInfoPage");
            return new ModelAndView("common/error",map);
        }
        PageRequest request = PageRequest.of(page-1,size);
        Page<Deliverer> delivererPage = deliveryService.findList(status,request);
        map.put("deliveryPage",delivererPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/delivery/list",map);
    }

    @GetMapping("/application")
    public ModelAndView application(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                     @RequestParam(value = "size", defaultValue = "5") Integer size,
                                     Map<String,Object> map,
                                     HttpServletRequest httpServletRequest){
        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        Integer status = sellerInfoService.findByUsername(currentUser).getId();
        //将现有的超市id读出
        List<Integer> sellIds = sellerRepository.findAll().stream().map(Seller::getId).collect(Collectors.toList());
        if(sellIds.indexOf(status) == -1){
            map.put("msg","您还未与超市绑定！请前去绑定");
            map.put("url","/seller/sellerInfoPage");
            return new ModelAndView("common/error",map);
        }
        PageRequest request = PageRequest.of(page-1,size);
        Page<Deliverer> delivererPage = deliveryService.findList(0,request);
        map.put("deliveryPage",delivererPage);
        map.put("currentPage",page);
        map.put("size",size);
        return new ModelAndView("/delivery/application",map);
    }

    @GetMapping("/hire")
    public ModelAndView hire(@RequestParam("idNum") String idNum,
                             HttpServletRequest httpServletRequest,
                             Map<String,Object> map){

        String currentUser = gzqCookie.getMyCookie(httpServletRequest,"username");
        Integer sellId = sellerInfoService.findByUsername(currentUser).getId();

        try {
            Deliverer updateResult = deliveryService.update(idNum, sellId);
        }catch(SellException e){
            log.error("[通过申请] 通过申请失败 idNum={}",idNum);
            map.put("msg",e.getMessage());
            map.put("url","/delivery/application");
            return new ModelAndView("common/error",map);
        }

        map.put("msg","成功接收配送员申请，现在Ta已经属于您的超市了");
        map.put("url","/delivery/list");
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/fire")
    public ModelAndView fire(@RequestParam("idNum") String idNum,
                             Map<String,Object> map){
        try{
            Deliverer deliverer = deliveryService.fire(idNum);
        }catch (SellException e){
            log.error("[解雇配送员] 解雇操作失败, idNum={}",idNum);
            map.put("msg",e.getMessage());
            map.put("url","/delivery/list");
            return new ModelAndView("common/error",map);
        }

        map.put("msg","解雇成功");
        map.put("url","/delivery/list");
        return new ModelAndView("common/success",map);

    }

    @GetMapping("/detail")
    @ResponseBody
    public String detail(@RequestParam("orderId") String orderId){
        Tasks tasks = tasksService.findOne(orderId);
        Deliverer deliverer = deliveryService.findByDeliveryId(tasks.getDeliveryId());
        String result = "单号 "+orderId+" 已被 "+deliverer.getName()+" 接单，正在配送中，联系电话 "+deliverer.getPhone();
        return result;
    }
}
