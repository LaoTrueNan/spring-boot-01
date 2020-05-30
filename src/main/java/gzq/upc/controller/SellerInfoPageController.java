package gzq.upc.controller;

import gzq.upc.dataobject.Seller;
import gzq.upc.dataobject.SellerInfo;
import gzq.upc.form.SellerForm;
import gzq.upc.repository.SellerInfoRepository;
import gzq.upc.repository.SellerRepository;
import gzq.upc.service.SellerInfoService;
import gzq.upc.utils.gzqCookie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/seller")
public class SellerInfoPageController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/sellerInfo")
    @ResponseBody
    public SellerInfo get(@RequestParam("username") String username){
        SellerInfo sellerInfo = sellerInfoService.findByUsername(username);
        return sellerInfo;
    }

    @PostMapping("/sellerInfoPage")
    @ResponseBody
    public Seller showInfo(@RequestParam("username") String username) {

        SellerInfo sellerInfo = sellerInfoService.findByUsername(username);
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerInfo.getId());//最好改成另外写service然后调用Jpa
        Seller seller = new Seller();
        if (sellerOptional == null || !sellerOptional.isPresent()) {
            seller.setName("管理员未与超市绑定");
            seller.setAddress("");
            seller.setBulletin("请与超市进行绑定！");
            seller.setAvatar("/img/headicon.jpg");
        }else{
            seller = sellerOptional.get();
        }
        return seller;
    }

    @GetMapping("/changeInfo")
    public ModelAndView changeInfo(@RequestParam("username") String username,
                                   @RequestParam("isBind") Integer isBind,
                                   Map<String,Object> map){
        SellerInfo sellerInfo = sellerInfoService.findByUsername(username);
        List<Seller> sellerList = sellerRepository.findAll();
        if(isBind==0){
            map.put("operation","绑定超市");
        }else{
            map.put("operation","切换绑定");
        }
        map.put("sellerInfo",sellerInfo);
        map.put("sellList",sellerList);
        return new ModelAndView("common/sellerInfoChange",map);
    }

    @Transactional
    @PostMapping("/confirmChange")
    public ModelAndView confirmChange(@Valid SellerForm form, BindingResult result,Map<String,Object> map){
        SellerInfo sellerInfo = sellerInfoService.findByUsername(form.getUsername());
        SellerInfo sellerInfo1 = new SellerInfo();
        BeanUtils.copyProperties(sellerInfo,sellerInfo1);
        sellerInfo1.setId(form.getId());
        sellerInfoService.save(sellerInfo1);
        map.put("msg","修改成功！");
        map.put("url","/seller/sellerInfoPage");
        return new ModelAndView("common/success",map);
    }


}
