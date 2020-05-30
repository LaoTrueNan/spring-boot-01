package gzq.upc.controller;
/**
 * 郭志强
 */

import gzq.upc.dataobject.SellerInfo;
import gzq.upc.form.SellerForm;
import gzq.upc.repository.SellerInfoRepository;
import gzq.upc.repository.SellerRepository;
import gzq.upc.service.LoginService;
import gzq.upc.service.SellerInfoService;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/welcome")
public class LoginController {

    @Autowired
    private SellerInfoRepository repository;

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public String login(@Valid SellerForm form, BindingResult bindingResult, HttpServletResponse response) {
        String result = loginService.check(form.getUsername(), form.getPassword());
        if(result.equals("success")){
            gzqCookie.setCookie(response, "username", form.getUsername(),"/");
            gzqCookie.setCookie(response, "lastusername", form.getUsername(),"/");//保存上次用户名，确保刷新也可保留
        }
        return result;
//            map.put("url", "/seller/sellerInfoPage?username=" + form.getUsername());
    }


    @PostMapping("/regi")
    public ModelAndView register(@Valid SellerForm form, BindingResult bindingResult, Map<String, Object> map) {

        String result = loginService.regi(form.getUsername(), form.getOpenid());
        map.put("msg", result);
        if (result.equals("Success")) {
            SellerInfo sellerInfo = new SellerInfo();
            //新注册的管理员没有id，这里设置成随机数
            form.setId(IntUtil.getUniqueKey());
            BeanUtils.copyProperties(form, sellerInfo);
            repository.save(sellerInfo);
            map.put("url", "/");
            return new ModelAndView("common/success", map);
        } else {
            map.put("url", "/regi.html");
            return new ModelAndView("common/error", map);
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "home";
    }

    @GetMapping("/all")
    @ResponseBody
    public String testall(@RequestParam(value="username",required = false) String username,
                          @RequestParam(value="openid",required = false) String openid) {
        List<String> usernames = sellerInfoService.findAll().stream().map(SellerInfo::getUsername).collect(Collectors.toList());
        List<String> openids = sellerInfoService.findAll().stream().map(SellerInfo::getOpenid).collect(Collectors.toList());
        if (usernames.indexOf(username) != -1) {
            return "用户名已经存在！";
        }else if(openids.indexOf(openid) != -1){
            return "该openid已被使用！";
        }
        return null;
    }
}