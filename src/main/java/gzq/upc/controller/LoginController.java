package gzq.upc.controller;

import gzq.upc.dataobject.SellerInfo;
import gzq.upc.form.SellerForm;
import gzq.upc.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/welcome")
public class LoginController {
    @ResponseBody
    @PostMapping("/login")
    public String login(@Valid SellerForm form,BindingResult bindingResult){
        return "<center><h1>"+form.getUsername()+"\t"+form.getPassword()+"</h1></center>";
    }


//    public ModelAndView(@Valid SellerForm form,BindingResult bindingResult,Map<String,Object> map){
//
//
//        return new ModelAndView("/seller/");
//    }
}
