package gzq.upc.controller;
/**
 * 郭志强
 */

import gzq.upc.dataobject.SellerInfo;
import gzq.upc.form.SellerForm;
import gzq.upc.repository.SellerInfoRepository;
import gzq.upc.repository.SellerRepository;
import gzq.upc.service.LoginService;
import gzq.upc.utils.IntUtil;
import gzq.upc.utils.gzqCookie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/welcome")
public class LoginController {

    @Autowired
    private SellerInfoRepository repository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ModelAndView login(@Valid SellerForm form, BindingResult bindingResult, Map<String,Object> map, HttpServletResponse response){
        String result = loginService.check(form.getUsername(),form.getPassword());
        map.put("msg",result);
        if(result.equals("success")) {
            map.put("url", "/seller/sellerInfoPage?username="+form.getUsername());
            gzqCookie.setCookie(response,"username",form.getUsername());
            return new ModelAndView("common/success",map);
        }else{
            map.put("url","/");
            return new ModelAndView("common/error",map);
        }
    }


    @PostMapping("/regi")
    public ModelAndView register(@Valid SellerForm form,BindingResult bindingResult,Map<String,Object> map){

        String result = loginService.regi(form.getUsername(),form.getOpenid());
        map.put("msg",result);
        if(result.equals("Success")) {
            SellerInfo sellerInfo = new SellerInfo();
            //新注册的管理员没有id，这里设置成随机数
            form.setId(IntUtil.getUniqueKey());
            BeanUtils.copyProperties(form, sellerInfo);
            repository.save(sellerInfo);
            map.put("url", "/");
            return new ModelAndView("common/success",map);
        }else{
            map.put("url","/regi.html");
            return new ModelAndView("common/error",map);
        }
    }

    @GetMapping("/logout")
    public String logout(){
        return "index";
    }
}
